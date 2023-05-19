package com.auth.jwt.service;

import com.auth.jwt.auth.JwtTokenProvider;
import com.auth.jwt.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public String login(Optional<Member> member) {
        // username, password를 파라미터로 받고 이를 이용해 UsernamePasswordAuthenticationToken을 생성
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(member.get().getName(), member.get().getPassword());

        // authenticationToken을 이용해서 Authenticaiton 객체를 생성하려고 authenticate 메소드가 실행될 때
        // CustomUserDetailsService에서 override한 loadUserByUsername 메소드가 실행된다.
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // authentication 을 기준으로 jwt token 생성
        String jwt = jwtTokenProvider.createToken(member);
        return jwt;
    }

    public boolean check(String token) {
        return jwtTokenProvider.validateToken(token);
    }
}
