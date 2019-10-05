package com.example.demo.user.service

import com.example.demo.model.User
import com.example.demo.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class UserServiceImpl @Autowired constructor(
        private val userRepository: UserRepository
) : UserService {

    override fun getByName(name: String): User {
        val foundUser = userRepository.findByName(name)
        foundUser?.let {
            return it
        }

        throw ResponseStatusException(HttpStatus.NOT_FOUND, "user.not.found")
    }

    override fun getAll(): List<User> = userRepository.findAll()

    override fun save(name: String): User = userRepository.save(User(0, name))

    override fun update(name: String, newName: String): User {
        val foundUser = userRepository.findByName(name)
        foundUser?.let {
            val updatedUser = User(it.id, newName)
            userRepository.save(updatedUser)
            return updatedUser
        }

        throw ResponseStatusException(HttpStatus.NOT_FOUND, "user.not.found")
    }

    override fun deleteByName(name: String) {
        val foundUser = userRepository.findByName(name)
        foundUser?.let {
            userRepository.deleteByName(name)
        }

        throw ResponseStatusException(HttpStatus.NOT_FOUND, "user.not.found")
    }

}