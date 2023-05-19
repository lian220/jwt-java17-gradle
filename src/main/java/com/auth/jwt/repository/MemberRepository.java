package com.auth.jwt.repository;

import com.auth.jwt.entity.Member;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface MemberRepository extends Repository<Member, Long> {
    Optional<Member> findByNameAndPassword(String name, String password);
}
