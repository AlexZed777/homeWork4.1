package ru.netology.homework3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.netology.homework3.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 0L,
            author = getString(R.string.author_name),
            content = getString(R.string.text),
            date = getString(R.string.date),
            likes = 500,
            shares = 800,
            views = 900
        )

        binding.render(post)

        binding.likes.setOnClickListener {
            post.myLikes = !post.myLikes
            val imageResId = if (post.myLikes) R.drawable.ic_liked_24 else R.drawable.ic_likes_24dp
            binding.likes.setImageResource(imageResId)
            if (post.myLikes) {
                post.likes++
            } else {
                post.likes--
            }

            binding.render(post)
        }

        binding.share.setOnClickListener {
            post.shares++
            binding.render(post)
        }

        binding.view.setOnClickListener {
            post.views++
            binding.render(post)
        }
    }

    private fun ActivityMainBinding.render(post: Post) {
        authorName.text = post.author
        text.text = post.content
        date.text = post.date
        countLikes.text = post.likes.toString()
        countShares.text = post.shares.toString()
        countViews.text = post.views.toString()


    }

}



