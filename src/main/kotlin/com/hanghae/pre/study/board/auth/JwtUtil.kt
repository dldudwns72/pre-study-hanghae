package com.hanghae.pre.study.board.auth

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.SignatureException
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtil(
    private val jwtProperty: JwtProperty
) { // JWT 생성 및 검증을 처리하는 클래스입니다.

    fun getSecretKey(): SecretKey {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256)
    }

    private val expirationMs = jwtProperty.expiration.time // 1시간


    // token 만 생성함
    fun generateToken(username: String): String {
        // payload 넣기
        val claims = mapOf("username" to username)

        return Jwts.builder()
            .setSubject(username)
            .setClaims(claims)
            .setIssuedAt(Date()) // 발급 시간
            .setExpiration(Date(System.currentTimeMillis() + expirationMs))  // 만료 시간 설정
            .signWith(getSecretKey() , SignatureAlgorithm.HS256)
            .compact()// 생성
    }

    fun validateToken(token: String): Boolean {
        return try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }

    fun extractUsername(token: String): String? {
        return try {
            val claims: Claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .body
            claims["username"] as String
            // claims.body["username"] as String
        } catch (e: SignatureException) {
            println("유효하지 않은 서명입니다: ${e.message}")
            null
        } catch (e: Exception) {
            println("토큰 파싱 중 오류가 발생 했습 니다: ${e.message}")
            null
        }
    }
}
