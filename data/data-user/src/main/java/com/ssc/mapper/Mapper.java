package com.ssc.mapper;

import com.ssc.dto.Member;
import com.ssc.entity.MemberEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Mapper {

    public MemberEntity from(Member dto) {
        if (dto == null) {
            return null;
        }
        return MemberEntity.builder()
            .id(dto.getId())
            .build();
    }

    public Member to(MemberEntity entity) {
        return Member.builder()
            .id(entity.getId())
            .build();
    }

}
