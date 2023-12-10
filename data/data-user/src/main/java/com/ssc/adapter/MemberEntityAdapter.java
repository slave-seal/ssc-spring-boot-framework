package com.ssc.adapter;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.ssc.dto.Member;
import com.ssc.entity.MemberEntity;
import com.ssc.mapper.Mapper;
import com.ssc.port.UserDataPort;
import com.ssc.repository.MemberRepository;
import com.ssc.repository.QueryRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@org.springframework.stereotype.Repository
@RequiredArgsConstructor
public class MemberEntityAdapter implements UserDataPort {

    private final MemberRepository memberRepository;
    private final QueryRepository<MemberEntity> queryRepository;

    @Override
    public Member findById(UUID id) {
        MemberEntity entity = memberRepository.findById(id).orElseThrow();
        return Mapper.to(entity);
    }

    @Override
    public Member findOne(BooleanExpression... expressions) {
        MemberEntity entity = queryRepository.queryOne(expressions).orElseThrow();
        return Mapper.to(entity);
    }

    @Override
    public List<Member> findAll(BooleanExpression... expressions) {
        return queryRepository.findAll(expressions)
            .stream()
            .map(Mapper::to)
            .collect(Collectors.toList());
    }

    @Override
    public Member create(Member createTarget) {
        return Mapper.to(memberRepository.save(Mapper.from(createTarget)));
    }

    @Override
    public Member update(Member updateTarget) {
        return Mapper.to(memberRepository.save(Mapper.from(updateTarget)));
    }

    @Override
    public void delete(UUID id) {
        memberRepository.deleteById(id);
    }

    @Override
    public void delete(Member deleteTarget) {

    }
}
