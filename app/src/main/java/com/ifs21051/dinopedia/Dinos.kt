package com.ifs21051.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Dinos(
    val nama: String,
    val gambar: Int,
    val deskripsi: String,
    val kelompok: String,
    val habitat: String,
    val makanan: String,
    val panjang: String,
    val tinggi: String,
    val bobot: String,
    val kelemahan: String
): Parcelable
