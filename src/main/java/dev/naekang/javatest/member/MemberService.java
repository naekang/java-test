package dev.naekang.javatest.member;

import dev.naekang.javatest.domain.Member;

import java.util.Optional;

public interface MemberService {
    Optional<Member> findById(Long memberId) throws MemberNotFoundException;

    void validate(Long memberId) throws InvalidMemberException;
}
