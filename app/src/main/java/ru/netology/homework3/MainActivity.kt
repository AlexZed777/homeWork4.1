package ru.netology.homework3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.DrawableRes
import ru.netology.homework3.databinding.ActivityMainBinding
import java.math.RoundingMode
import java.text.DecimalFormat


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
            likes = 999,
            shares = 999,
            views = 1099
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
        likes.setImageResource(getLikeIcon(post.myLikes))
        countLikes.text = getBeautifulDisplay(post.likes)
        countShares.text = getBeautifulDisplay(post.shares)
        countViews.text = getBeautifulDisplay(post.views)


    }

    @DrawableRes
    private fun getLikeIcon(liked: Boolean) =
        if (liked) R.drawable.ic_liked_24 else {
            R.drawable.ic_likes_24dp
        }


    private fun getBeautifulDisplay(counts: Int): String {
        if (counts in 1000..1099) {
            val result = counts / 1000
            return "$result K"
        } else if (counts in 10000..999_999) {
            val count = counts / 1000.toFloat()
            val dec = DecimalFormat("#")
            dec.setRoundingMode(RoundingMode.DOWN)
            val result = dec.format(count)
            return "$result K"
        } else if (counts in 1100..9_999) {
            val count = counts / 1000.toFloat()
            val dec = DecimalFormat("#.#")
            dec.setRoundingMode(RoundingMode.DOWN)
            val result = dec.format(count)
            return "$result K"
        } else if (counts >= 1_000_000) {
            val count = counts / 1_000_000.toFloat()
            val dec = DecimalFormat("#.#")
            dec.setRoundingMode(RoundingMode.DOWN)
            val result = dec.format(count)
            return "$result M"
        }
        return "$counts"
    }

}



