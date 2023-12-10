package com.ssc.service;

import com.ssc.port.UserDataPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final UserDataPort userDataPort;

}
