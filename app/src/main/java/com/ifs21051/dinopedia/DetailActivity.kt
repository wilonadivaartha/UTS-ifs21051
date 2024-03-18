
package com.ifs21051.dinopedia

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21051.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var dinos: Dinos? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dinos = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(
                EXTRA_DINOS,
                Dinos::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DINOS)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dinos != null) {
            supportActionBar?.title = "${dinos!!.nama}"
            loadData(dinos!!)
        } else {
            finish()
        }
    }
    private fun loadData(dinos: Dinos) {
        binding.ivDetailIcon.setImageResource(dinos.icon)
        binding.tvDetailName.text = dinos.nama
        binding.tvDetailDeskripsi.text = dinos.deskripsi
        binding.tvDetailPeriode.text = dinos.periode
        binding.tvDetailKarakter.text = dinos.karakter
        binding.tvDetailHabitat.text = dinos.habitat
        binding.tvDetailPerilaku.text = dinos.perilaku
        binding.tvDetailKlasifikasi.text = dinos.klasifikasi
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
        const val EXTRA_DINOS = "extra_dinos"
    }
}
