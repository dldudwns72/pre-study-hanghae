package com.hanghae.pre.study.board.controller

import com.hanghae.pre.study.board.dto.CommonResponse
import com.hanghae.pre.study.board.dto.user.LoginUser
import com.hanghae.pre.study.board.dto.user.UserRequest
import com.hanghae.pre.study.board.dto.user.UserResponse
import com.hanghae.pre.study.board.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService
) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: UserRequest): CommonResponse<Boolean> {
        return userService.signUp(request)
    }

    @GetMapping("/sign-in")
    fun signIn(@RequestBody request: UserRequest): CommonResponse<LoginUser> {
        return userService.signIn(request)
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody request: UserRequest
    ): UserResponse {
        return userService.updateUser(id, request)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(
        @PathVariable id: Long
    ) {
        userService.deleteUser(id)
    }
}