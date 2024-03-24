package com.ifs21051.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Famili(
    var nama_famili: String,
    var icon_famili: Int,
    var deskripsi_famili: String,
    var periode_famili:String,
    var karakter_famili: String,
    var habitat_famili: String,
    var perilaku_famili: String,
    var klasifikasi_famili:String,
    var startDino: Int,
    var endDino: Int
//    var nama_detail: String,
//    var icon_detail: Int,
//    var deskripsi_detail: String,
//    var karakter_detail: String,
//    var kelompok_detail: String,
//    var habitat_detail: String,
//    var makanan_detail: String,
//    var panjang_detail: String,
//    var tinggi_detail: String,
//    var bobot_detail: String,
//    var kelemahan_detail: String
) : Parcelable
