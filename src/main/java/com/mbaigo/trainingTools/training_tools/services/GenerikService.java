package com.mbaigo.trainingTools.training_tools.services;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface GenerikService<T, ID extends Serializable> {

    T create(T entity);

    T update(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    List<T> findAllBySorted(String sortProperty, boolean ascending);

    void delete(T entity);

    void deleteById(ID id);

    long count();


}
