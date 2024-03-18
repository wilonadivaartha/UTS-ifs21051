package com.ifs21051.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21051.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataDinos = ArrayList<Dinos>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvDinos.setHasFixedSize(false)
        dataDinos.addAll(getListDinos())
        showRecyclerList()
    }


    @SuppressLint("Recycle")
    private fun getListDinos(): ArrayList<Dinos> {
        val dataGambar =
            resources.obtainTypedArray(R.array.gambar_dino)
        val dataNama =
            resources.getStringArray(R.array.nama_dino)
        val dataDeskripsi =
            resources.getStringArray(R.array.deskripsi_dino)
        val dataPeriode =
            resources.getStringArray(R.array.periode_dino)
        val dataKarakter =
            resources.getStringArray(R.array.karakter_dino)
        val dataHabitat =
            resources.getStringArray(R.array.habitat_dino)
        val dataPerilaku =
            resources.getStringArray(R.array.perilaku_dino)
        val dataKlasifikasi =
            resources.getStringArray(R.array.klasifikasi_dino)

        val listDinos = ArrayList<Dinos>()
        for (i in dataNama.indices) {
            val dinos = Dinos(
                dataGambar.getResourceId(i, -1),
                dataNama[i],
                dataDeskripsi[i],
                dataPeriode[i],
                dataKarakter[i],
                dataHabitat[i],
                dataPerilaku[i],
                dataKlasifikasi[i])
            listDinos.add(dinos)
        }
        return listDinos
    }
    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvDinos.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvDinos.layoutManager =
                LinearLayoutManager(this)
        }
        val listDinosAdapter = ListDinosAdapter(dataDinos)
        binding.rvDinos.adapter = listDinosAdapter
        listDinosAdapter.setOnItemClickCallback(object :
            ListDinosAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dinos) {
                showSelectedDinos(data)
            }
        })
    }
    private fun showSelectedDinos(dinos: Dinos) {
        val intentWithData = Intent(this@MainActivity,
            DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_DINOS, dinos)
        startActivity(intentWithData)
    }
}