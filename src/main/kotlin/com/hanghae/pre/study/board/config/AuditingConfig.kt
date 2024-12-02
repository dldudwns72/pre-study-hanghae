package com.hanghae.pre.study.board.config

import com.hanghae.pre.study.board.UnixTimeDateTimeProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.auditing.DateTimeProvider
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "unixTimeProvider")
class AuditingConfig {
    @Bean
    fun unixTimeProvider(): DateTimeProvider {
        return UnixTimeDateTimeProvider()
    }
}
