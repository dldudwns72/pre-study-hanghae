package com.hanghae.pre.study.board.dto.board

import com.hanghae.pre.study.board.dto.user.UserRequest
import com.hanghae.pre.study.board.persistence.entity.Board
import com.hanghae.pre.study.board.persistence.entity.User

data class BoardRequest(
    var title: String,
    var body: String,
    var password: String,
    var username: String
)


fun BoardRequest.toEntity(): Board {
    return Board(title, body, password, username)
}