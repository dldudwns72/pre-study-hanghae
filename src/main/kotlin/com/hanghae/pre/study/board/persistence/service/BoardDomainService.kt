package com.hanghae.pre.study.board.persistence.service

import com.hanghae.pre.study.board.persistence.entity.Board
import com.hanghae.pre.study.board.persistence.entity.User
import com.hanghae.pre.study.board.persistence.repository.BoardRepository
import com.hanghae.pre.study.board.persistence.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.Optional


@Service
class BoardDomainService(
    private val boardRepository: BoardRepository
) {


    fun saveBoard(board: Board) {
        boardRepository.save(board)
    }

    fun updateBoard(board: Board) {
        boardRepository.save(board)
    }

    fun getBoard(id: Long): Optional<Board> {
        return boardRepository.findById(id)
    }

    fun getBoards(): List<Board> {
        return boardRepository.findAll()
    }

    fun deleteBoard(id: Long) {
        boardRepository.deleteById(id)
    }


}