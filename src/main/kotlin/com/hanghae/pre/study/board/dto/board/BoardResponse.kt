package com.hanghae.pre.study.board.dto.board

data class BoardResponse(
    var title: String,
    var body: String,
    var username: String,
    var createdAt: String,
    var updatedAt: String
)