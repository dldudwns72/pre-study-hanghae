package com.hanghae.pre.study.board.controller

import com.hanghae.pre.study.board.auth.AuthenticationUser
import com.hanghae.pre.study.board.dto.CommonResponse
import com.hanghae.pre.study.board.dto.board.BoardRequest
import com.hanghae.pre.study.board.dto.board.BoardResponse
import com.hanghae.pre.study.board.dto.user.LoginUser
import com.hanghae.pre.study.board.service.BoardService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/board")
class BoardController(
    private val boardService: BoardService
) {
    @GetMapping
    fun getBoards(): CommonResponse<List<BoardResponse>> {
        return boardService.getBoards()
    }

    @GetMapping("/{id}")
    fun getBoard(
        @AuthenticationUser loginUser: LoginUser,
        @PathVariable id: Long
    ): CommonResponse<BoardResponse> {
        return boardService.getBoard(id)
    }

    @PostMapping
    fun createBoard(
        @AuthenticationUser loginUser: LoginUser,
        @RequestBody request: BoardRequest
    ): CommonResponse<Any> {
        return boardService.createBoard(request)
    }
//
//    @PutMapping("/{id}")
//    fun updateUser(
//        @PathVariable id: Long,
//        @RequestBody request: UserRequest
//    ): UserResponse {
//        return boardService.modifyBoard(id, request)
//    }
//
//    @DeleteMapping("/{id}")
//    fun deleteUser(
//        @PathVariable id: Long
//    ) {
//        boardService.deleteUser(id)
//    }
}