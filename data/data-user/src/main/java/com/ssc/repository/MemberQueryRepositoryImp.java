package com.ssc.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssc.entity.MemberEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepositoryImp implements MemberQueryRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public MemberEntity findOne(BooleanExpression... expressions) {
        return null;
    }

    @Override
    public List<MemberEntity> findAll(BooleanExpression... expressions) {
        return null;
    }
}
