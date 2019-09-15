package com.example.demo.user.service

import com.example.demo.model.User
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserServiceImpl : UserService {

    private var userList = arrayListOf<User>()

    override fun getByName(name: String): User {
        val foundUser = userList.firstOrNull { it.name == name }
        foundUser?.let {
            return it
        }

        throw ResponseStatusException(HttpStatus.NOT_FOUND, "user.not.found")
    }

    override fun getAll(): List<User> = userList

    override fun save(name: String): User {
        val user = User(name)
        userList.add(user)
        return user
    }

    override fun update(name: String, newName: String): User {
        val foundUser = userList.firstOrNull { it.name == name }
        foundUser?.let {
            userList.remove(foundUser)
            userList.add(User(newName))
            return User(newName)
        }

        throw ResponseStatusException(HttpStatus.NOT_FOUND, "user.not.found")
    }

    override fun deleteByName(name: String) {
        userList.remove(User(name))
    }

}