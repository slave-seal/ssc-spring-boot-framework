package com.ssc.port;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.ssc.dto.Member;
import java.util.List;
import java.util.UUID;

public interface UserDataPort {

    Member findById(UUID id);

    Member findOne(BooleanExpression... expressions);

    List<Member> findAll(BooleanExpression... expressions);

    Member create(Member createTarget);

    Member update(Member updateTarget);

    void delete(UUID id);
    void delete(Member deleteTarget);

}
