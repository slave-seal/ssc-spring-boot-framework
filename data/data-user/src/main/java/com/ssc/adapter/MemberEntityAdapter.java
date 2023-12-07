package com.ssc.adapter;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.ssc.dto.Member;
import com.ssc.mapper.Mapper;
import com.ssc.port.EntityPort;
import com.ssc.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberEntityAdapter implements EntityPort<Member, UUID> {

    private final MemberRepository memberRepository;

    @Override
    public Optional<Member> findById(UUID id) {
        return Optional.ofNullable(Mapper.to(memberRepository.findById(id).orElse(null)));
    }

    @Override
    public Optional<Member> findOne(BooleanExpression... expressions) {
        return Optional.ofNullable(Mapper.to(memberRepository.findOne()));
    }

    @Override
    public List<Member> findAll(BooleanExpression... expressions) {
        return memberRepository.findAll()
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
}
