package com.example.sofra.data.repo

import com.example.sofra.data.datasource.UrunlerDataSource
import com.example.sofra.data.entity.SepetYemek
import com.example.sofra.data.entity.Urunler

class UrunlerRepository(var uds:UrunlerDataSource) {

    suspend fun sepetGetir(kullanici_adi:String) : List<SepetYemek> = uds.sepetiYukle(kullanici_adi)

    suspend fun favoriGetir(kullanici_adi:String) : List<SepetYemek> = uds.favoriYukle(kullanici_adi)

    suspend fun sil(sepet_yemek_id:Int,kullanici_adi:String) = uds.sil(sepet_yemek_id,kullanici_adi)

    suspend fun yukle() : List<Urunler> = uds.urunleriYukle()

    suspend fun sepeteEkle(yemek_adi:String,
                           yemek_resim_adi:String,
                           yemek_fiyat:Int,
                           yemek_siparis_adet:Int,
                           kullanici_adi:String) =
        uds.sepeteEkle(yemek_adi,
        yemek_resim_adi,
        yemek_fiyat,
        yemek_siparis_adet,
        kullanici_adi)

    suspend fun favorilereKaydet(yemek_adi:String,
                           yemek_resim_adi:String,
                           yemek_fiyat:Int,
                           yemek_siparis_adet:Int,
                           kullanici_adi:String) =
        uds.favorilereKaydet(yemek_adi,
            yemek_resim_adi,
            yemek_fiyat,
            yemek_siparis_adet,
            kullanici_adi)


}