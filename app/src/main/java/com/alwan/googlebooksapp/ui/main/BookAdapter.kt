package com.alwan.googlebooksapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alwan.googlebooksapp.data.source.local.entity.BookEntity
import com.alwan.googlebooksapp.databinding.ItemBookBinding
import com.alwan.googlebooksapp.utils.loadImage

class BookAdapter(private val callback: BookCallback) :
    RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    private val listBook = ArrayList<BookEntity>()

    fun setBook(bookEntities: ArrayList<BookEntity>?) {
        listBook.clear()
        bookEntities?.let { listBook.addAll(it) }
        notifyDataSetChanged()
    }

    interface BookCallback {
        fun onBookClick(book: BookEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemBookBinding =
            ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(itemBookBinding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(listBook[position])
    }

    override fun getItemCount(): Int = listBook.size

    inner class BookViewHolder(private val binding: ItemBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: BookEntity) {
            with(binding) {
                tvTitleBook.text = book.title
                tvAuthorBook.text = book.authors
                tvCategories.text = book.categories
                imgBook.loadImage(book.thumbnailLinks)
                root.setOnClickListener { callback.onBookClick(book) }
            }
        }
    }
}