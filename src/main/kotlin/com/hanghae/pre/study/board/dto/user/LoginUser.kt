package com.hanghae.pre.study.board.dto.user

data class LoginUser(
    val name: String,
    val accessToken: String,
    val refreshToken: String? = null
)