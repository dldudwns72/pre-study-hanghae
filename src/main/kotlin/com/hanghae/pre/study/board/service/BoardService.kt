package com.hanghae.pre.study.board.service

import com.hanghae.pre.study.board.dto.CommonResponse
import com.hanghae.pre.study.board.dto.board.BoardRequest
import com.hanghae.pre.study.board.dto.board.BoardResponse
import com.hanghae.pre.study.board.dto.board.toEntity
import com.hanghae.pre.study.board.persistence.entity.Board
import com.hanghae.pre.study.board.persistence.entity.toResponse
import com.hanghae.pre.study.board.persistence.service.BoardDomainService
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

@Service
class BoardService(
    private val boardDomainService: BoardDomainService
) {

    fun createBoard(request: BoardRequest): CommonResponse<Any> {
        boardDomainService.saveBoard(request.toEntity())
        return CommonResponse(data = true)
    }

    fun getBoards(): CommonResponse<List<BoardResponse>> {
        return try {
            CommonResponse(
                data = boardDomainService.getBoards().map {
                    it.toResponse()
                })
        } catch (e: Exception) {
            CommonResponse(
                data = listOf(BoardResponse("e", "e", "e", "e", "e"))
            )
        }

    }

    fun getBoard(id: Long): CommonResponse<BoardResponse> {
        return CommonResponse(
            data = boardDomainService.getBoard(id).getOrNull()?.toResponse()
                ?: throw IllegalArgumentException("dd")
        )
    }

    fun modifyBoard(request: BoardRequest) {
        boardDomainService.saveBoard(request.toEntity())
    }

    fun deleteBoard(id: Long) {
        boardDomainService.deleteBoard(id)
    }


}