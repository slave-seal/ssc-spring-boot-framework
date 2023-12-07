package com.ssc.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.ssc.entity.MemberEntity;
import java.util.List;

public interface MemberQueryRepository {

    MemberEntity findOne(BooleanExpression... expressions);

    List<MemberEntity> findAll(BooleanExpression... expressions);

}
