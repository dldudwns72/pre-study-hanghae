package com.hanghae.pre.study.board

import org.springframework.data.auditing.DateTimeProvider
import java.time.Instant
import java.time.temporal.TemporalAccessor
import java.util.*

class UnixTimeDateTimeProvider : DateTimeProvider {
    override fun getNow(): Optional<TemporalAccessor> {
        // 현재 시간을 Unix Time 형태로 Temporal 반환 (Instant 사용)
        return Optional.of(Instant.now())
    }
}
