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
    private val dataDinos = ArrayList<Famili>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menampilkan tombol kembali jika diperlukan

        binding.rvFamili.setHasFixedSize(false)
        dataDinos.addAll(getListFamili())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_wilo -> {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    @SuppressLint("Recycle")
    private fun getListFamili(): ArrayList<Famili> {
        val dataNama = resources.getStringArray(R.array.nama_dino)
        val dataGambar = resources.obtainTypedArray(R.array.gambar_dino)
        val dataDeskripsi = resources.getStringArray(R.array.deskripsi_dino)
        val dataPeriode = resources.getStringArray(R.array.periode_dino)
        val dataKarakter = resources.getStringArray(R.array.karakter_dino)
        val dataHabitat = resources.getStringArray(R.array.habitat_dino)
        val dataPerilaku = resources.getStringArray(R.array.perilaku_dino)
        val dataKlasifikasi = resources.getStringArray(R.array.klasifikasi_dino)
        val dataIndexStart = resources.getStringArray(R.array.start_dino)
        val dataIndexEnd = resources.getStringArray(R.array.end_dino)

        val listDinos = ArrayList<Famili>()
        for (i in dataNama.indices) {
            val dinos = Famili(
                dataNama[i],
                dataGambar.getResourceId(i, -1),
                dataDeskripsi[i],
                dataPeriode[i],
                dataKarakter[i],
                dataHabitat[i],
                dataPerilaku[i],
                dataKlasifikasi[i],
                dataIndexStart[i].toInt(),
                dataIndexEnd[i].toInt()
            )
            listDinos.add(dinos)
        }
        return listDinos
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvFamili.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvFamili.layoutManager = LinearLayoutManager(this)
        }
        val listDinosAdapter = ListFamiliAdapter(dataDinos)
        binding.rvFamili.adapter = listDinosAdapter
        listDinosAdapter.setOnItemClickCallback(object : ListFamiliAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Famili) {
                showSelectedDinos(data)
            }
        })
    }

    private fun showSelectedDinos(dinos: Famili) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_FAMILI, dinos)
        startActivity(intent)
    }
}
