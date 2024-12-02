package com.hanghae.pre.study.board.dto

import com.hanghae.pre.study.board.ResponseStatus

data class CommonResponse<T>(
    val result: ResponseStatus = ResponseStatus.SUCCESS,
    val data: T
)