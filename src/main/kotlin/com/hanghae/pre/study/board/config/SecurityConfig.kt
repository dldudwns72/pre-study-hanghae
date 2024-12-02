package com.hanghae.pre.study.board.config

import com.hanghae.pre.study.board.auth.JwtFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwfFilter: JwtFilter

) {
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    // 사용자 인증 로직 처리
    // 사용자 정보를 UserDetailService 에서 로드 하고 password 검증
    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    // boot 가 올라갈 때 설정 됨
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/users/**").permitAll() // 공개 엔드포인트 -> auth 로 시작하는 api 허용
                    .requestMatchers("/h2-console/**").permitAll() // 공개 엔드포인트 -> auth 로 시작하는 api 허용
                    .anyRequest().authenticated() // 나머지 인증 필요
            }.headers { headers ->
                headers.frameOptions { it.disable() } // 이거 설정 안해주면 못찾음 h2-console
            }.formLogin { login -> // custom 로그인 페이지
                login
                    .loginPage("/login")
                    .permitAll()
            }
            .logout { logout -> // 로그아웃 처리
                logout
                    .logoutUrl("/logout")
                    .permitAll()
            }.addFilterBefore(jwfFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

}

