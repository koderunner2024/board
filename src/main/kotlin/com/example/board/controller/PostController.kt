
package com.example.board.controller

import com.example.board.model.Post
import com.example.board.repository.PostRepository
import jakarta.persistence.EntityManager
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
class PostController(
    private val postRepository: PostRepository,
    private val entityManager: EntityManager
) {

    @PostMapping
    fun createPost(@RequestBody post: Post): Post {
        return postRepository.save(post)
    }

    @GetMapping("/search")
    fun searchPosts(@RequestParam title: String): List<Post> {
        val query = "SELECT * FROM Post WHERE title = '$title'"
        return entityManager.createNativeQuery(query, Post::class.java).resultList as List<Post>
    }

    @GetMapping
    fun getAllPosts(): List<Post> {
        return postRepository.findAll()
    }
}
