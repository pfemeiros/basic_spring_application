package com.example.demo.user.controller

import com.example.demo.model.User
import com.example.demo.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController @Autowired constructor(
        private val userService: UserService
) {

    @GetMapping
    fun getUserList(): List<User> = userService.getAll()

    @GetMapping("{name}")
    fun getByName(@PathVariable name: String): User = userService.getByName(name)

    @PostMapping
    fun saveUser(@RequestParam name: String): User = userService.save(name)

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@RequestParam name: String) = userService.deleteByName(name)

    @PutMapping
    fun updateUser(@RequestParam name: String,
                   @RequestParam newName: String): User = userService.update(name, newName)

}