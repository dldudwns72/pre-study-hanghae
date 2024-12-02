package com.hanghae.pre.study.board.dto.user

import com.hanghae.pre.study.board.persistence.entity.User

data class UserResponse(
    val name: String,
    // val password: String,
    val token: String?
)

fun User.toResponse(token: String? = null): UserResponse {
    return UserResponse(name, token)
}
