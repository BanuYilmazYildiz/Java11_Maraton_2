package com.banu.utility;

import java.util.List;
import java.util.Optional;

public interface ICrud <T,ID>{

    T save (T entity);
    T update(T entity);
    Iterable<T> saveAll(Iterable<T> entites); // liste dısında baska arraylist, set.. kullanabilmek icin iterabke

    void delete(T entity);
    void deleteById (ID id);

    Optional<T> findById (ID id);


    boolean existById(ID id);

    List<T> findALl();

    List<T> findByEntity(T entity);

    List<T> findByColumnNameAndValue(String columnName, String value); // root.get(id)-> colon adı - velue deger
//    List<T> findByColumnNameAndValue(String columnName, Long value);
//    List<T> findByColumnNameAndValue(String columnName, BigDecimal value);





}
