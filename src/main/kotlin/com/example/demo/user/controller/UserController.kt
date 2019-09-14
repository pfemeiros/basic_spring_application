package com.example.demo.user.controller

import com.example.demo.model.User
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/users")
class UserController {

    private var userList = arrayListOf<User>()

    @GetMapping
    fun getUserList(): List<User> = userList

    @GetMapping("{name}")
    fun getByName(@PathVariable name: String): User {
        val foundUser = userList.firstOrNull { it.name == name }
        foundUser?.let {
            return it
        }

        throw ResponseStatusException(HttpStatus.NOT_FOUND, "user.not.found")
    }

    @PostMapping
    fun saveUser(@RequestParam name: String): User {
        val user = User(name)
        userList.add(user)
        return user
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@RequestParam name: String) {
        userList.remove(User(name))
    }

    @PutMapping
    fun updateUser(@RequestParam name: String,
                   @RequestParam newName: String): User {
        val foundUser = userList.firstOrNull { it.name == name }
        foundUser?.let {
            userList.remove(foundUser)
            userList.add(User(newName))
            return User(newName)
        }

        throw ResponseStatusException(HttpStatus.NOT_FOUND, "user.not.found")
    }

}