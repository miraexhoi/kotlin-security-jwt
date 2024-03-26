package com.example.kotlinsecurityjwt.common.authority

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.util.Date

const val EXPIRATION_MILLISECONDS: Long = 1000 * 60 * 30

@Component
class JwtTokenProvider {
    @Value("\${jwt.secret}")
    lateinit var secretKey: String

    private val key by lazy { Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey)) }

    /** token 생성 */
    fun createToken(authentication: Authentication): TokenInfo {
        val authorites: String = authentication
            .authorities
            .joinToString(",", transform = GrantedAuthority::getAuthority)
        val now = Date()
        val accessExpiration = Date(now.time + EXPIRATION_MILLISECONDS)

        // access token
        val accessToken = Jwts
            .builder()
            .setSubject(authentication.name)
            .claim("auth", authorites)
            .setIssuedAt(now)
            .setExpiration(accessExpiration)
            .signWith(key,SignatureAlgorithm.HS256)
            .compact()

        return TokenInfo("Bearer", accessToken)
    }
}