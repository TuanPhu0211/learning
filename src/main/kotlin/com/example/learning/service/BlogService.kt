package com.example.learning.service

import com.example.learning.model.Blog
import com.example.learning.model.User

interface BlogService {
    fun findByTitle(title: String): Blog?
    fun findByAuthor(user: User): List<Blog>
    fun findBySlug(slug: String): Blog?
    fun save(blog: Blog): Blog
    fun update(blog: Blog): Blog
    fun delete(blog: Blog): Blog
}