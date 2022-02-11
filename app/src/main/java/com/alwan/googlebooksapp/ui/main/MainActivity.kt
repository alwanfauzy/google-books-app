package com.alwan.googlebooksapp.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alwan.googlebooksapp.data.source.local.entity.BookEntity
import com.alwan.googlebooksapp.databinding.ActivityMainBinding
import com.alwan.googlebooksapp.ui.DetailActivity
import com.alwan.googlebooksapp.utils.MarginItemDecoration
import com.alwan.googlebooksapp.utils.ViewModelFactory

class MainActivity : AppCompatActivity(), TextView.OnEditorActionListener,
    BookAdapter.BookCallback {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    private var adapterBook = BookAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRv()
        setupViewModel()

        binding.editSearchMain.setOnEditorActionListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            val query = binding.editSearchMain.text.toString()
            setupAdapterBook(query)
            closeKeyboard()
        }
        return true
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance()
        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    private fun setupAdapterBook(query: String) {
        showLoading(true)
        showEmpty(false)
        mainViewModel.getBooks(query).observe(this) {
            adapterBook.setBook(it)
            if(it == null){
                showEmpty(true)
            }
            showLoading(false)
        }
    }

    private fun setupRv() {
        with(binding.rvBooksMain) {
            setHasFixedSize(true)
            addItemDecoration(MarginItemDecoration(16))
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterBook
        }
    }

    private fun showLoading(state: Boolean) {
        binding.loadingMain.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun showEmpty(state: Boolean) {
        with(binding) {
            tvEmptyMain.visibility = if (state) View.VISIBLE else View.GONE
            imgEmptyMain.visibility = if (state) View.VISIBLE else View.GONE
            rvBooksMain.visibility = if (state) View.GONE else View.VISIBLE
        }
    }

    private fun closeKeyboard() {
        val view: View? = this.currentFocus
        if (view != null) {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onBookClick(book: BookEntity) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_BOOK, book)
        startActivity(intent)
    }
}