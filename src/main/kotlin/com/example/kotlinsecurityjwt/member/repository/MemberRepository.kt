package com.example.kotlinsecurityjwt.member.repository

import com.example.kotlinsecurityjwt.member.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<MemberEntity, Long> {
    fun findByLoginId(loginId: String): MemberEntity?
}