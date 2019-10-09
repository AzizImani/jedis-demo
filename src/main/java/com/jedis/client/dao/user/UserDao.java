package com.jedis.client.dao.user;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.jedis.client.dao.AbstractDao;
import com.jedis.client.dao.JedisSupplier;
import com.jedis.client.models.User;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

@Repository
public class UserDao implements AbstractDao<User> {

    private static final String USER_ID_KEY = "userId";
    private static final String USERS_KEY = "user:";

    private final Jedis jedis = JedisSupplier.getJedis();
    private final UserConverter userConverter = new UserConverter();

    @Override
    public void save(User user) {
        if (user.getId() == null) {
            user.setId(getNextId());
        }
        Map<String, String> userEntry = userConverter.toMap(user);
        jedis.hmset(getKey(user.getId()), userEntry);
    }

    @Override
    public User getById(Long id) {
        Map<String, String> userEntry = jedis.hgetAll(getKey(id));
        return userConverter.convert(userEntry);
    }

    @Override
    public List<User> getAll() {
        Set<String> keys = jedis.keys(USERS_KEY + "*");
        Pipeline pipeline = jedis.pipelined();
        List<Response<Map<String, String>>> responses = keys.stream()
            .map(pipeline::hgetAll)
            .collect(Collectors.toList());
        pipeline.sync();
        List<Map<String, String>> usersEntries = responses.stream()
            .map(Response::get)
            .collect(Collectors.toList());
        return userConverter.convertAll(usersEntries);
    }

    @Override
    public Long deleteById(Long id) {
        return jedis.del(getKey(id));
    }

    private String getKey(Long id) {
        return USERS_KEY + id;
    }

    private Long getNextId() {
        return jedis.incr(USER_ID_KEY);
    }
}
