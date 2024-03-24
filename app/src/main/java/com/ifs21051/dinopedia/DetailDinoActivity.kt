package com.ifs21051.dinopedia

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21051.dinopedia.databinding.ActivityDetailDinoBinding

class DetailDinoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDinoBinding
    private var dino: Dinos? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDinoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dino = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(
                EXTRA_DINO,
                Dinos::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DINO)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dino != null) {
            supportActionBar?.title = " ${dino!!.nama}"
            loadData(dino!!)
        } else {
            finish()
        }
    }
    private fun loadData(dino: Dinos) {
        binding.tvDetailNamaDino.text = dino.nama
        binding.ivDetailGambarDino.setImageResource(dino.gambar)
        binding.tvDetailKarakteristikDino.text = dino.deskripsi
        binding.tvDetailKelompokDino.text = dino.kelompok
        binding.tvDetailHabitatDino.text = dino.habitat
        binding.tvDetailMakananDino.text = dino.makanan
        binding.tvJDetailPanjangDino.text = dino.panjang
        binding.tvJDetailTinggiDino.text = dino.tinggi
        binding.tvDetailBobotDino.text = dino.bobot
        binding.tvDetailKelemahanDino.text = dino.kelemahan
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    companion object {
        const val EXTRA_DINO = "extra_dino"
    }
}
