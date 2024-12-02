package com.hanghae.pre.study.board.auth

import com.hanghae.pre.study.board.persistence.repository.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserDetailServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserDetailsService {


    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByName(username)
            .orElseThrow { IllegalStateException("유저 정보를 찾을 수 없습니다. 유저명: $username") }

        return User.builder()
            .username(user.name)
            .password(passwordEncoder.encode(user.password))
            .roles("USER")
            .build()
    }

}