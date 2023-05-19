package com.auth.jwt.controller;

import com.auth.jwt.auth.JwtTokenProvider;
import com.auth.jwt.entity.Member;
import com.auth.jwt.repository.MemberRepository;
import com.auth.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth/token")
public class AuthController {
    private final MemberRepository memberRepository;
    private final AuthService authService;

    @PostMapping(value = "/issue", produces = "application/json")
    public ResponseEntity<String> getToken(@RequestBody Member memberDto) {
        Optional<Member> member = memberRepository.findByNameAndPassword(memberDto.getName(), memberDto.getPassword());
        if (!member.isPresent()) {
            return ResponseEntity.status(404).build();
        }
        String token = authService.login(member);
        return new ResponseEntity<>("{\"token\" : \""+token+"\"}", HttpStatus.OK);
    }

    @PostMapping(value = "/check", produces = "application/json")
    public ResponseEntity<String> checkToken(String token) {
        if(authService.check(token)) return new ResponseEntity<>("{\"token\" : \""+token+"\"}", HttpStatus.OK);
        else return new ResponseEntity<>("{\"token\" : \""+token+"\"}", HttpStatus.CONFLICT);
    }
}
