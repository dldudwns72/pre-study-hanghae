package com.hanghae.pre.study.board.persistence.repository

import com.hanghae.pre.study.board.persistence.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByName(name: String): Optional<User>

}