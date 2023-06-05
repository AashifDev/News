package com.example.news

import android.app.Notification.Action
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.news.database.NewsEntity
import com.example.news.database.NewsViewModel
import com.example.news.databinding.ActivityReadNewsactivityBinding


class ReadNewsactivity : AppCompatActivity() {
    lateinit var binding: ActivityReadNewsactivityBinding
    private lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadNewsactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        supportActionBar?.title = null

        val intent = intent.extras!!.get("url")

        binding.back.setOnClickListener {
            startActivity(Intent(this,ReadLaterActivity::class.java))
            finish()
        }

        binding.readNews.webViewClient = WebViewClient()
        binding.readNews.loadUrl(intent as String)
        binding.readNews.settings.javaScriptEnabled = true
        binding.readNews.settings.setSupportZoom(true)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.savedNews->{
                val intent = Intent(this,ReadLaterActivity::class.java)
                startActivity(intent)
                finish()
            }

            R.id.share->{
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}