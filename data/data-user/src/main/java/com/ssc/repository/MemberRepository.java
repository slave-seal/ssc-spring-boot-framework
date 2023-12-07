package com.ssc.repository;

import com.ssc.entity.MemberEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, UUID>, MemberQueryRepository {

}
