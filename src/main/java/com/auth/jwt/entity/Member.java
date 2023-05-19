package com.auth.jwt.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Table
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    private Long memberId;

    private String name;

    private Integer birth;

    private String password;

    private Role role;

    @Builder
    public Member(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public Member(String subject, String password, List<SimpleGrantedAuthority> authorities) {
    }
}
