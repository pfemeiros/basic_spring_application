package com.example.demo.user.service

import com.example.demo.model.User

interface UserService {

    fun getByName(name: String): User
    fun getAll(): List<User>
    fun save(name: String): User
    fun update(name: String, newName: String): User
    fun deleteByName(name: String)

}