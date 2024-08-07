package com.example.kotlinsecurityjwt.member.controller

import com.example.kotlinsecurityjwt.common.authority.TokenInfo
import com.example.kotlinsecurityjwt.common.dto.BaseResponse
import com.example.kotlinsecurityjwt.member.dto.LoginDto
import com.example.kotlinsecurityjwt.member.dto.MemberDtoRequest
import com.example.kotlinsecurityjwt.member.service.MemberService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/member")
@RestController
class MemberController (
    private val memberService: MemberService
) {
    /** 회원가입 */
    @RequestMapping("/signup")
    fun signUp(@RequestBody @Valid memberDtoRequest: MemberDtoRequest): BaseResponse<Unit> {
        val resultMsg: String = memberService.signUp(memberDtoRequest)
        return BaseResponse(message = resultMsg)
    }

    /** 로그인 */
    @RequestMapping("/login")
    fun login(@RequestBody @Valid loginDto: LoginDto): BaseResponse<TokenInfo> {
        val tokenInfo = memberService.login(loginDto)
        return BaseResponse(data = tokenInfo)
    }
}