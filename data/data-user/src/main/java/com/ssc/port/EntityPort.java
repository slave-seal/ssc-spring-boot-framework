package com.ssc.port;

import com.querydsl.core.types.dsl.BooleanExpression;
import java.util.List;
import java.util.Optional;

public interface EntityPort<T, S> {

    Optional<T> findById(S id);

    Optional<T> findOne(BooleanExpression... expressions);

    List<T> findAll(BooleanExpression... expressions);

    T create(T createTarget);

    T update(T updateTarget);

    void delete(S id);

}
