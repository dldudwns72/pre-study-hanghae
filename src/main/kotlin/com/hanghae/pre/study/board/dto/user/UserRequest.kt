package com.hanghae.pre.study.board.dto.user

import com.hanghae.pre.study.board.persistence.entity.User
import java.lang.IllegalArgumentException

data class UserRequest(
    val name: String,
    val password: String
) {
    init {
        userValidate()
    }

    companion object {
        const val MIN_NAME_LENGTH = 4
        const val MAX_NAME_LENGTH = 10
        const val MIN_PASSWORD_LENGTH = 8
        const val MAX_PASSWORD_LENGTH = 15

    }

    private fun userValidate() {
        if (name.length !in MIN_NAME_LENGTH..MAX_NAME_LENGTH) {
            throw IllegalArgumentException("${MIN_NAME_LENGTH}자 이상, ${MAX_NAME_LENGTH}자 이하만 입력 가능합니다.")
        }
        if (password.length !in MIN_PASSWORD_LENGTH..MAX_PASSWORD_LENGTH) {
            throw IllegalArgumentException("${MIN_PASSWORD_LENGTH}자 이상, ${MAX_PASSWORD_LENGTH}자 이하만 입력 가능합니다.")
        }
        val regex = "^[a-z0-9]+$".toRegex()
        if (!regex.matches(name)) {
            throw IllegalArgumentException("이름은 알파벳 소문자(a~z), 숫자(0~9)만 입력 가능합니다.")
        }
        if (!regex.matches(password)) {
            throw IllegalArgumentException("비밀번호는 알파벳 소문자(a~z), 숫자(0~9)만 입력 가능합니다.")
        }
    }
}

fun UserRequest.toEntity(encodePassword: String): User {
    return User(name, encodePassword)
}

