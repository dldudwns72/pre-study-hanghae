package com.hanghae.pre.study.board.exception

import com.hanghae.pre.study.board.ResponseStatus
import com.hanghae.pre.study.board.dto.CommonResponse
import com.hanghae.pre.study.board.dto.ErrorData
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.resource.NoResourceFoundException

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @ExceptionHandler(Exception::class)
    fun handleCoreException(e: Exception): CommonResponse<ErrorData> {
        logger.info(e.javaClass.toString())
        logger.info(e.stackTraceToString())
        logger.info(e.message)
        return CommonResponse(
            result = ResponseStatus.FAIL,
            ErrorData(code = "500", message = e.message ?: "에러가 발생하였습니다.")
        )
    }

    @ExceptionHandler(NoResourceFoundException::class)
    fun handleCoreException(nrfe: NoResourceFoundException): CommonResponse<ErrorData> {
        logger.info(nrfe.javaClass.toString())
        logger.info(nrfe.stackTraceToString())
        logger.info(nrfe.message)
        return CommonResponse(
            result = ResponseStatus.FAIL,
            ErrorData(code = "404", message = nrfe.message ?: "[${nrfe.javaClass}] 자원을 찾을 수 없습니다.")
        )
    }
}