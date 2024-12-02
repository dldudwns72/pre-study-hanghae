package com.hanghae.pre.study.board.auth

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "jwt")
class JwtProperty(
    var secret: Secret = Secret(),
    var expiration: Expiration = Expiration()
) {
    data class Secret(var key: String = "")
    data class Expiration(var time: Long = 3600000)
}