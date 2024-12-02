package com.hanghae.pre.study.board.persistence.service

import com.hanghae.pre.study.board.persistence.entity.User
import com.hanghae.pre.study.board.persistence.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.Optional


@Service
class UserDomainService(
    private val userRepository: UserRepository
) {

    fun saveUser(user: User): User {
        return userRepository.save(user)
    }

    fun getUser(id: Long): Optional<User> {
        return userRepository.findById(id)
    }

    fun getUserByName(name: String): Optional<User> {
        return userRepository.findByName(name)
    }

    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }
}