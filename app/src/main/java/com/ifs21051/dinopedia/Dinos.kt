package com.ifs21051.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Dinos(
    var icon: Int,
    var nama: String,
    var deskripsi: String,
    var periode: String,
    var karakter: String,
    var habitat: String,
    var perilaku: String,
    var klasifikasi: String,
) : Parcelable
