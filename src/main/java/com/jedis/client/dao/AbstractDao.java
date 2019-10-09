package com.jedis.client.dao;

import java.util.List;

public interface AbstractDao<T> {

    void save(T entry);

    T getById(Long id);

    List<T> getAll();

    Long deleteById(Long id);
}
