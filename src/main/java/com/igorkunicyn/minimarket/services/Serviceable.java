package com.igorkunicyn.minimarket.services;

import java.util.List;

public interface Serviceable <T> {
    boolean save(T o);
    boolean delete(long id);
    T getById(long id);
    List<T> getList();

}
