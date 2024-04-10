package com.example.kotlinsecurityjwt.member.repository

import com.example.kotlinsecurityjwt.member.entity.MemberEntity
import com.example.kotlinsecurityjwt.member.entity.MemberRole
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<MemberEntity, Long> {
    fun findByLoginId(loginId: String): MemberEntity?
}

interface MemberRoleRepository : JpaRepository<MemberRole, Long>