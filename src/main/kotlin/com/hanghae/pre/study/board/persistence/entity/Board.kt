package com.hanghae.pre.study.board.persistence.entity

import com.hanghae.pre.study.board.dto.board.BoardResponse
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.text.SimpleDateFormat
import java.util.*

@Entity
@EntityListeners(AuditingEntityListener::class) // Auditing 활성
class Board(
    val title: String,
    val body: String,
    val password: String,
    val username: String
) {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0

    @CreatedDate
    @Column(updatable = false)
    var createdAt: Long = 0L

    @LastModifiedDate
    var updatedAt: Long = 0L
}

fun Board.toResponse(): BoardResponse {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return BoardResponse(
        title = title,
        body = body,
        username = username,
        createdAt = sdf.format((Date(createdAt * 1000))),
        updatedAt = sdf.format((Date(updatedAt * 1000)))
    )
}