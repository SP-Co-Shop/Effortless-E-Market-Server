package com.example.coshop.controller;

import com.example.coshop.Entity.Member;
import com.example.coshop.dto.member.request.MemberRequest;
import com.example.coshop.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/create")
    public ResponseEntity createMember(@RequestBody @Valid MemberRequest request){

        return memberService.createMember(request);
    }
}
