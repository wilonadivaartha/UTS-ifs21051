package com.ifs21051.dinopedia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ifs21051.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var famili: Famili? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        famili = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(
                EXTRA_FAMILI,
                Famili::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILI)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (famili != null) {
            supportActionBar?.title = " ${famili!!.nama_famili}"
            loadData(famili!!)
        } else {
            finish()
        }

        val dinoBtn = findViewById<TextView>(R.id.btnDinos)
        dinoBtn.setOnClickListener({
            val intent = Intent(this, DinoActivity::class.java)
            startActivity(intent)
        })

        binding.btnDinos.setOnClickListener{
            val intentWithData = Intent(this@DetailActivity, DinoActivity::class.java)
            intentWithData.putExtra(DinoActivity.EXTRA_FAMILI, famili!!)
            startActivity(intentWithData)
        }
    }
    private fun loadData(dinos: Famili) {
        binding.ivDetailIcon.setImageResource(dinos.icon_famili)
        binding.tvFamiliName.text = dinos.nama_famili
        binding.tvDetailDeskripsi.text = dinos.deskripsi_famili
        binding.tvDetailPeriode.text = dinos.periode_famili
        binding.tvDetailKarakteristik.text = dinos.deskripsi_famili
        binding.tvDetailHabitat.text = dinos.habitat_famili
        binding.tvDetailPerilaku.text = dinos.perilaku_famili
        binding.tvDetailKlasifikasi.text = dinos.klasifikasi_famili
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
        const val EXTRA_FAMILI = "extra_famili"
    }
}
