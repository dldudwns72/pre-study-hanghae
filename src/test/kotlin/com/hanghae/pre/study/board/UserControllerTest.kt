package com.hanghae.pre.study.board

import com.hanghae.pre.study.board.service.UserService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.Called

class UserControllerTest(
    @MockkBean private val userService: UserService,
) {


}