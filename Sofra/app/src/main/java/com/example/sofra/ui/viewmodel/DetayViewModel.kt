package com.example.sofra.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.sofra.data.entity.SepetYemek
import com.example.sofra.data.repo.UrunlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetayViewModel @Inject constructor(var urepo:UrunlerRepository) : ViewModel() {

    fun sepeteKaydet(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            var nesne: SepetYemek? = null
            try {
                val sepetListesi = urepo.sepetGetir(kullanici_adi)
                for (it in sepetListesi) {
                    if (it.yemek_adi == yemek_adi) {
                        nesne = it
                        break
                    }
                }
                if (nesne != null) {
                    Log.e("Log", "Sepetten silindi çünkü mevcut")
                    val yeniAdet = nesne.yemek_siparis_adet + yemek_siparis_adet
                    urepo.sil(nesne.sepet_yemek_id, kullanici_adi)
                    urepo.sepeteEkle(
                        yemek_adi,
                        yemek_resim_adi,
                        yemek_fiyat,
                        yeniAdet,
                        kullanici_adi
                    )
                } else {
                    Log.e("Log", "Sepete farklı 2. bir ürün girdi")
                    urepo.sepeteEkle(
                        yemek_adi,
                        yemek_resim_adi,
                        yemek_fiyat,
                        yemek_siparis_adet,
                        kullanici_adi
                    )
                }
            } catch (e: Exception) {
                Log.e("Log", "Ürün hiç yok")
                urepo.sepeteEkle(
                    yemek_adi,
                    yemek_resim_adi,
                    yemek_fiyat,
                    yemek_siparis_adet,
                    kullanici_adi
                )
            }

        }

    }

    fun favorilereKaydet(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            var nesne: SepetYemek? = null
            try {
                val sepetListesi = urepo.favoriGetir(kullanici_adi)
                for (it in sepetListesi) {
                    if (it.yemek_adi == yemek_adi) {
                        nesne = it
                        break
                    }
                }
                if (nesne != null) {
                    urepo.sil(nesne.sepet_yemek_id, kullanici_adi)
                    Log.e("Log", "Favorilerde silindi çünkü mevcut")

                } else {
                    Log.e("Log", "Favorilere 2. ürün girdi")
                    urepo.favorilereKaydet(
                        yemek_adi,
                        yemek_resim_adi,
                        yemek_fiyat,
                        yemek_siparis_adet,
                        kullanici_adi
                    )
                }
            } catch (e: Exception) {
                Log.e("Log", "Hiç Yok")
                urepo.favorilereKaydet(
                    yemek_adi,
                    yemek_resim_adi,
                    yemek_fiyat,
                    yemek_siparis_adet,
                    kullanici_adi
                )
            }

        }
    }
}

