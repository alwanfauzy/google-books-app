package com.alwan.googlebooksapp.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.alwan.googlebooksapp.data.source.local.entity.BookEntity
import com.alwan.googlebooksapp.databinding.ActivityDetailBinding
import com.alwan.googlebooksapp.utils.loadImage

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val book = intent.getParcelableExtra<BookEntity>(EXTRA_BOOK)

        if (book != null) {
            populateDetail(book)
        }
        binding.imgBackDetail.setOnClickListener(this)
        binding.imgBookmarkDetail.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.imgBackDetail -> finish()
            binding.imgBookmarkDetail -> Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun populateDetail(detail: BookEntity) {
        with(binding) {
            imgBookDetail.loadImage(detail.thumbnailLinks)
            tvTitleDetail.text = detail.title
            tvAuthorDetail.text = detail.authors
            tvCategoriesDetail.text = detail.categories
            tvDescriptionDetail.text = detail.description
            btnPreviewDetail.setOnClickListener {
                val uri = Uri.parse(detail.previewLink)
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
    }

    companion object {
        const val EXTRA_BOOK = "extra_book"
    }
}