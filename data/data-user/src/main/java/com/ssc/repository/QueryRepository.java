package com.ssc.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.ssc.entity.MemberEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepository<T> {

    Optional<MemberEntity> queryOne(BooleanExpression... expressions);

    List<T> findAll(BooleanExpression... expressions);

}
