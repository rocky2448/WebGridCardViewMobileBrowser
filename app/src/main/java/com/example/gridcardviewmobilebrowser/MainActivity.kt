package com.example.gridcardviewmobilebrowser

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var toolbarMain: Toolbar
    private lateinit var gridViewGV: GridView
    private var list = mutableListOf(
        GridViewModal("Яндекс картинки", R.drawable.image, "https://ya.ru/images"),
        GridViewModal("Яндекс почта", R.drawable.mail, "https://mail.yandex.ru"),
        GridViewModal("Яндекс видео", R.drawable.video, "https://ya.ru/video"),
        GridViewModal("Яндекс карты", R.drawable.map, "https://yandex.ru/maps"),
        GridViewModal("Яндекс переводчик", R.drawable.translate, "https://translate.yandex.ru"),
        GridViewModal("Авто.ру", R.drawable.auto, "https://auto.ru"),
        GridViewModal("Яндекс диск", R.drawable.disk, "https://disk.yandex.ru"),
        GridViewModal("Яндекс игры", R.drawable.games, "https://yandex.ru/games/")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarMain = findViewById(R.id.toolbarMain)
        gridViewGV = findViewById(R.id.gridViewGV)

        setSupportActionBar(toolbarMain)
        title = "Мобильный браузер"
        toolbarMain.subtitle = "by Rocky"
        toolbarMain.setLogo(R.drawable.ic_mobile_browser_48dp)

        val adapter = GridViewAdapter(list = list, this@MainActivity)
        gridViewGV.adapter = adapter

        gridViewGV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            Toast.makeText(
                applicationContext,
                "Выбран сайт: ${list[position].adres}",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(list[position].adres)))

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuMain -> finishAffinity()
        }
        return super.onOptionsItemSelected(item)
    }
}