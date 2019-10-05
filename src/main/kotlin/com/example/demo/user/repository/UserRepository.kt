package com.example.demo.user.repository

import com.example.demo.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Int> {

    fun findByName(name: String): User?
    fun deleteByName(name: String)

}