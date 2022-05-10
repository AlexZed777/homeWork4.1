package ru.netology.homework3

import java.util.*

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val date: String,
    var likes: Int = 0,
    var myLikes: Boolean = false,
    var shares: Int = 0,
    var views: Int = 0
)