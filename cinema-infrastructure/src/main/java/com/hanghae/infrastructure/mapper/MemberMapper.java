package com.hanghae.infrastructure.mapper;

import com.hanghae.domain.model.Member;
import com.hanghae.infrastructure.entity.MemberEntity;

public class MemberMapper {
    public static Member toDomain(MemberEntity memberEntity) {
        return new Member(
                memberEntity.getId(),
                memberEntity.getBirthDate(),
                memberEntity.getCreatedBy(),
                memberEntity.getCreatedAt(),
                memberEntity.getUpdatedBy(),
                memberEntity.getCreatedAt()
        );
    }

    public static MemberEntity toEntity(Member member) {
        return MemberEntity.builder()
                .id(member.getMemberId())
                .birthDate(member.getBirthDate())
                .createdBy(member.getCreatedBy())
                .updatedBy(member.getUpdatedBy())
                .build();
    }
}
