package com.hanghae.pre.study.board.service

import com.hanghae.pre.study.board.auth.JwtUtil
import com.hanghae.pre.study.board.dto.CommonResponse
import com.hanghae.pre.study.board.dto.user.*
import com.hanghae.pre.study.board.persistence.service.UserDomainService
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import kotlin.jvm.optionals.getOrNull

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder

@Service
class UserService(
    private val userDomainService: UserDomainService,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil
) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    // 유저 DB 생성
    fun signUp(request: UserRequest): CommonResponse<Boolean> {
        return try {
            val encodePassword = passwordEncoder.encode(request.password)
            userDomainService.getUserByName(request.name).getOrNull()?.let {
                throw IllegalStateException("이미 존재하는 유저입니다.")
            }
            userDomainService.saveUser(request.toEntity(encodePassword))
            CommonResponse(data = true)
        } catch (ie: IllegalStateException) {
            logger.error(ie.message)
            throw ie
        }
    }

    fun signIn(request: UserRequest): CommonResponse<LoginUser> {
        val user = userDomainService.getUserByName(request.name).getOrNull()
            ?: throw IllegalArgumentException("유저를 찾을 수 없습니다.")
        val token = jwtUtil.generateToken(user.name)
        val loginUser = LoginUser(
            name = request.name,
            accessToken = token
        )
        return CommonResponse(data = loginUser)
    }

    fun updateUser(id: Long, request: UserRequest): UserResponse {
        val user = userDomainService.getUser(id).getOrNull()
            ?: throw IllegalArgumentException("유저를 찾을 수 없습니다.")
        user.setUser(request.name, request.password)
        return userDomainService.saveUser(user).toResponse()
    }

    fun deleteUser(id: Long) {
        userDomainService.deleteUser(id)
    }
}