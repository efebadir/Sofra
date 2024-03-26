package com.example.sofra.data.datasource

import com.example.sofra.data.entity.SepetYemek
import com.example.sofra.data.entity.Urunler
import com.example.sofra.retrofit.UrunlerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UrunlerDataSource(var udao:UrunlerDao) {


    suspend fun sil(sepet_yemek_id:Int,kullanici_adi:String) = udao.sepetSil(sepet_yemek_id,kullanici_adi)

    suspend fun sepeteEkle(yemek_adi:String,
                           yemek_resim_adi:String,
                           yemek_fiyat:Int,
                           yemek_siparis_adet:Int,
                           kullanici_adi:String)
        = udao.sepeteEkle(yemek_adi,
            yemek_resim_adi,
            yemek_fiyat,
            yemek_siparis_adet,
            kullanici_adi)


    suspend fun urunleriYukle() : List<Urunler> = withContext(Dispatchers.IO){


        return@withContext udao.yukle().yemekler
    }

    suspend fun sepetiYukle(kullanici_adi:String) : List<SepetYemek> = withContext(Dispatchers.IO){
        return@withContext udao.sepetGetir(kullanici_adi).sepet_yemekler
    }

    suspend fun favoriYukle(kullanici_adi:String) : List<SepetYemek> = withContext(Dispatchers.IO){
        return@withContext udao.favoriGetir(kullanici_adi).sepet_yemekler
    }

    suspend fun favorilereKaydet(yemek_adi:String,
                           yemek_resim_adi:String,
                           yemek_fiyat:Int,
                           yemek_siparis_adet:Int,
                           kullanici_adi:String)
            = udao.favorilereKaydet(yemek_adi,
        yemek_resim_adi,
        yemek_fiyat,
        yemek_siparis_adet,
        kullanici_adi)
}