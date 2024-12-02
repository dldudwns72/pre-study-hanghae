package com.hanghae.pre.study.board.persistence.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.lang.IllegalArgumentException


@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener::class) // Auditing 활성
data class User(
    var name: String,
    var password: String
) {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0

    @CreatedDate
    @Column(updatable = false) // 생성일은 수정되지 않도록 설정
    var createdAt: Long = 0L // val로 선언 시 @CreatedDate 설정 불가능

    @LastModifiedDate // @LastModifiedBy 과의 차이 확인 필요
    var updatedAt: Long = 0L

    fun setUser(name: String, password: String) {
        this.name = name
        this.password = password
    }

}