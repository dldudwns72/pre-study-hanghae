package com.hanghae.pre.study.board

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

class GameApplication

fun main(args: Array<String>) {
    // runApplication<GameApplication>(*args)
    println("메뉴를 입력해주세요.")
    println("1. 게임 시작하기")
    println("2. 게임 끝내기")

    val selectedMenu: Int? = readLine()?.toInt()
    var isGameProgress = true
    while (isGameProgress) {
        isGameProgress = try {
            when (selectedMenu) {
                1 -> startGame()
                2 -> endGame()
                else -> throw IllegalStateException("1,2번 메뉴를 입력해주세요.")
            }
        } catch (ise: IllegalStateException) {
            println(ise.message)
            endGame()
        }
    }

}

fun setCharacter() {
    var isAddCharacter = "Y"
    val result = mutableListOf<Character>()
    try {

        while (isAddCharacter == "Y") {
            println("캐릭터의 이름을 입력해주세요.")
            val inputName: String = readlnOrNull() ?: throw IllegalStateException("이름을 입력해주세요")
            println("캐릭터의 직업을 입력해주세요.")
            val inputJob: String = readlnOrNull() ?: throw IllegalStateException("직업을 입력해주세요")
            result.add(Character(inputName, inputJob))
            println("더 추가하시겠습니까? Y OR N")
            val isMoreAdd = readlnOrNull() ?: throw IllegalStateException("Y OR N 을 입력해주세요")
            isAddCharacter = when (isMoreAdd) {
                "Y" -> "Y"
                "N" -> "N"
                else -> throw IllegalStateException("Y OR N 을 입력해주세요")
            }
        }
    } catch (ise: IllegalStateException) {
        println(ise.message)
    }
    result.forEach {
        println("name: ${it.name}, job: ${it.job}")
    }
}

fun startGame(): Boolean {
    println("게임이 시작되었습니다.")
    setCharacter()
    println("게임을 계속 하시겠습니까? Y OR N")
    val isGameMore = readlnOrNull()
    return when (isGameMore) {
        "Y" -> true
        "N" -> endGame()
        else -> throw IllegalStateException("Y OR N 을 입력해주세요")
    }
}

fun endGame(): Boolean {
    println("게임이 종료되었습니다.")
    return false
}
