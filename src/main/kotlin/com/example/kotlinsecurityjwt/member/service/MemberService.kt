package com.example.kotlinsecurityjwt.member.service

import com.example.kotlinsecurityjwt.common.exception.InvalidInputException
import com.example.kotlinsecurityjwt.member.dto.MemberDtoRequest
import com.example.kotlinsecurityjwt.member.entity.MemberEntity
import com.example.kotlinsecurityjwt.member.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


@Transactional
@Service
class MemberService (
    private val memberRepository: MemberRepository
) {
    /** 회원가입 */
    fun signUp(memberDtoRequest: MemberDtoRequest): String {
        // ID 중복 검사
        var member: MemberEntity? = memberRepository.findByLoginId(memberDtoRequest.loginId)
        if (member != null) {
            throw InvalidInputException("loginId", "이미 등록된 ID 입니다.")
        }

        member = memberDtoRequest.toEntity()

        memberRepository.save(member)

        return "회원가입이 완료 되었습니다."
    }
}