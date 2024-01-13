package com.example.coshop.service;

import com.example.coshop.entity.Member;
import com.example.coshop.dto.member.request.MemberRequest;
import com.example.coshop.repository.member.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final static Logger log = LoggerFactory.getLogger("dc-logger");

    /* 회원 생성 */
    @Transactional
    public ResponseEntity createMember(MemberRequest request){
        Member member = memberBuilder(request);
        memberRepository.save(member);
        MDC.put("memberId",member.getId().toString());
        MDC.put("memberName",member.getName());
        log.info("   ");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* 로그인 */

    /**/


    /* 회원 생성 - Builder */
    private Member memberBuilder(MemberRequest request) {
        return new Member(
                request.getEmail(),
                request.getPassword(),
                request.getName(),
                request.getGender(),
                request.getPhoneNumber(),
                request.getAddress());
    }

}
