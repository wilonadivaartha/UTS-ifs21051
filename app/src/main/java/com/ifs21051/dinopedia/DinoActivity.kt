package com.ifs21051.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21051.dinopedia.databinding.ActivityDinoBinding

class DinoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDinoBinding
    private val dataDinos = ArrayList<Dinos>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvDinos.setHasFixedSize(true)
        dataDinos.addAll(getListDinos())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListDinos(): ArrayList<Dinos> {

        val family = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(
                EXTRA_FAMILI,
                Famili::class.java
            )
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILI)
        }

        val dataNama = resources.getStringArray(R.array.nama_detail_dino)
        val dataGambar = resources.obtainTypedArray(R.array.gambar_detail_dino)
        val dataDeskripsi = resources.getStringArray(R.array.deskripsi_detail_dino)
        val dataKarakteristik = resources.getStringArray(R.array.karakteristik_detail_dino)
        val dataKelompok = resources.getStringArray(R.array.kelompok_detail_dino)
        val dataHabitat = resources.getStringArray(R.array.habitat_detail_dino)
        val dataMakanan = resources.getStringArray(R.array.makanan_detail_dino)
        val dataPanjang = resources.getStringArray(R.array.panjang_detail_dino)
        val dataTinggi = resources.getStringArray(R.array.tinggi_detail_dino)
        val dataBobot = resources.getStringArray(R.array.bobot_detail_dino)
        val dataKelemahan = resources.getStringArray(R.array.kelemahan_detail_dino)

        val startIndex = family?.startDino
        val endIndex = family?.endDino

        val dinoList = ArrayList<Dinos>()
        for (i in startIndex!!..endIndex!!) {
            val dino = Dinos(
                dataNama[i],
                dataGambar.getResourceId(i, -1),
                dataDeskripsi[i],
                dataKelompok[i],
                dataHabitat[i],
                dataMakanan[i],
                dataPanjang[i],
                dataTinggi[i],
                dataBobot[i],
                dataKelemahan[i])
            dinoList.add(dino)
        }
        return dinoList
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvDinos.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvDinos.layoutManager = LinearLayoutManager(this)
        }
        val listDinosAdapter = ListDinoAdapter(dataDinos)
        binding.rvDinos.adapter = listDinosAdapter

        listDinosAdapter.setOnItemClickCallback(object : ListDinoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dinos) {
                showSelectedDinos(data)
            }
        })
    }

    private fun showSelectedDinos(dinos: Dinos) {
        val intent = Intent(this@DinoActivity, DetailDinoActivity::class.java)
        intent.putExtra(DetailDinoActivity.EXTRA_DINO, dinos)
        startActivity(intent)
    }

    companion object {
        const val EXTRA_FAMILI = "extra_famili"
    }
}
