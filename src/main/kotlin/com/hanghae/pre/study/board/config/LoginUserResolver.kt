package com.hanghae.pre.study.board.config

import com.hanghae.pre.study.board.auth.AuthenticationUser
import com.hanghae.pre.study.board.dto.user.LoginUser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class LoginUserResolver() : HandlerMethodArgumentResolver {

    // 어떤 parameter 를 처리 할건지
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == LoginUser::class.java ||
                parameter.hasParameterAnnotation(AuthenticationUser::class.java)
    }

    // 어떤 값을 만들 건지
    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val httpRequest = webRequest.nativeRequest as HttpServletRequest
        val authorizationHeader = httpRequest.getHeader(HttpHeaders.AUTHORIZATION)

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw IllegalArgumentException("Authorization header is missing or invalid")
        }

        val token = authorizationHeader.substring(7).trim()  // "Bearer " 이후의 부분이 토큰입니다.

        // JWT 토큰을 검증하고 Claims 추출
        val claims = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor("thisissecretkey@12345678".toByteArray()))  // 여기에 비밀 키를 넣어야 합니다
            .build()
            .parseClaimsJws(token)
            .body

        // Claims에서 사용자 정보를 추출 (예: username)
        val username = claims["username"] as String

        // LoginUser 객체 반환
        return LoginUser(name = username, accessToken = token)
    }
}