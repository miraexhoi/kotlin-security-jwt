package com.example.kotlinsecurityjwt.common.authority

data class TokenInfo (
    val grantType: String,
    val accessToken: String
)