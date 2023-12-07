package com.ssc.service;

import com.ssc.dto.Member;
import com.ssc.port.EntityPort;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final EntityPort<Member, UUID> entityPort;

}
