package dev.naekang.javatest.member;

import dev.naekang.javatest.domain.Member;

import java.util.Optional;

public interface MemberService {
    void validate(Long memberId) throws InvalidMemberException;

    Optional<Member> findById(Long memberId) throws MemberNotFoundException;
}
