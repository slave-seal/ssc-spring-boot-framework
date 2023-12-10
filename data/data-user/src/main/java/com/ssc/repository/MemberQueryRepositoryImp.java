package com.ssc.repository;

import static com.ssc.entity.QMemberEntity.memberEntity;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssc.entity.MemberEntity;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepositoryImp implements QueryRepository<MemberEntity> {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<MemberEntity> queryOne(BooleanExpression... expressions) {
//        if (QueryUtil.isNotValid(expressions)) {
//            return Optional.empty();
//        }
        return Optional.ofNullable(jpaQueryFactory.selectFrom(memberEntity)
            .where(expressions)
            .fetchOne()
        );
    }

    @Override
    public List<MemberEntity> findAll(BooleanExpression... expressions) {
        return jpaQueryFactory.selectFrom(memberEntity)
            .where(expressions)
            .fetch();
    }
}
