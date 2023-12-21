package com.example.coshop.test.member;

import com.example.coshop.dto.member.request.MemberRequest;
import com.example.coshop.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class MemberTest {

    private final MemberService memberService;

    @Autowired
    public MemberTest(MemberService memberService) {
        this.memberService = memberService;
    }

    @Test
    @Rollback(value = false)
    public void createMember(){

        MemberRequest request = new MemberRequest(
                "AAAA@gmail.com",
                "password",
                "MemberA",
                "M",
                "010-0000-0000",
                "local"
        );
        try {
            memberService.createMember(request);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
