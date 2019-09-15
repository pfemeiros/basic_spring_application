package com.example.demo.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder
import org.apache.tomcat.jni.User.username
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails



@Configuration
@EnableWebSecurity
class WebSecurityConfig: WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
    }

    override fun userDetailsService(): UserDetailsService {
        val user = withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build()

        return InMemoryUserDetailsManager(user)
    }

}