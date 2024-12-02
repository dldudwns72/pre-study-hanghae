package com.hanghae.pre.study.board.dto

import com.hanghae.pre.study.board.dto.user.UserRequest
import com.hanghae.pre.study.board.persistence.entity.User
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

class UserTest : BehaviorSpec({
    Given("이름 설정") {// 어떤 상태에서 시작하며
        val name = "youngjun"
        val password = "1234qwer"
        When("유효한 유저 생성") {// 변화를 가했을 때
            val user = UserRequest(name, password)
            Then("이름: JUN, 비밀번호: 1234qwer 을 가진 User 객체 생성") {// 기대하는바
                user.name shouldBe "youngjun"
                user shouldBe UserRequest("youngjun", "1234qwer")
            }
        }
        When("특수문자가 포함된 비밀번호가 설정될 때") {// 변화를 가했을 때
            val wrongPassword = "@0123456789"
            Then("IllegalArgumentException 발생") {
                val result = shouldThrowExactly<IllegalArgumentException> {
                    UserRequest(name, wrongPassword)
                }
                result.message shouldBe "비밀번호는 알파벳 소문자(a~z), 숫자(0~9)만 입력 가능합니다."
            }
        }
        When("8글자 이하 비밀번호가 설정될 때") {// 변화를 가했을 때
            val underMinLength = "jun"
            Then("IllegalArgumentException 예외 발생") {
                val result = shouldThrowExactly<IllegalArgumentException> {
                    UserRequest(name, underMinLength)
                }
                result.message shouldBe "8자 이상, 15자 이하만 입력 가능합니다."
            }
        }
        When("15글자 초과 비밀번호가 설정될 때") {// 변화를 가했을 때
            val overMinLength = "123456789abcdefghijklmn"
            Then("IllegalArgumentException 예외 발생") {
                val result = shouldThrowExactly<IllegalArgumentException> {
                    UserRequest(name, overMinLength)
                }
                result.message shouldBe "8자 이상, 15자 이하만 입력 가능합니다."
            }
        }
    }

    Given("패스워드 설정") {// 어떤 상태에서 시작하며
        val name = "youngjun"
        val password = "1234qwer"
        When("정상적인 입력에 의한 유저 생성") {// 변화를 가했을 때
            val user = User(name, password)
            Then("유효한 User 객체 생성") {// 기대하는바
                user.password shouldBe "1234qwer"
                user shouldBe User("youngjun", "1234qwer")
            }
        }
        When("특수문자가 포함된 이름이 설정될 때") {// 변화를 가했을 때
            val wrongName = "@abc"
            Then("IllegalArgumentException 발생") {
                val result = shouldThrowExactly<IllegalArgumentException> {
                    UserRequest(wrongName, password)
                }
                result.message shouldBe "이름은 알파벳 소문자(a~z), 숫자(0~9)만 입력 가능합니다."
            }
        }
        When("4글자 이하 이름 설정 될 때") {// 변화를 가했을 때
            val underMinLength = "jun"
            val overMinLength = "123456789abcdef"
            Then("IllegalArgumentException 예외 발생") {
                val result = shouldThrowExactly<IllegalArgumentException> {
                    UserRequest(underMinLength, password)
                }
                result.message shouldBe "4자 이상, 10자 이하만 입력 가능합니다."
            }
        }
        When("12글자 초과 이름 설정 될 때") {// 변화를 가했을 때
            val overMinLength = "123456789abcdef"
            Then("IllegalArgumentException 예외 발생") {
                val result = shouldThrowExactly<IllegalArgumentException> {
                    UserRequest(overMinLength, password)
                }
                result.message shouldBe "4자 이상, 10자 이하만 입력 가능합니다."
            }
        }
    }
})
