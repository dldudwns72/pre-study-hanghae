package com.hanghae.pre.study.board.persistence.repository

import com.hanghae.pre.study.board.persistence.entity.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository: JpaRepository<Board, Long> {
}