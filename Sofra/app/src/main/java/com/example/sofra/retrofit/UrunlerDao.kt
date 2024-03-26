package com.example.sofra.retrofit

import com.example.sofra.data.entity.CRUDCevap
import com.example.sofra.data.entity.SepetCevap
import com.example.sofra.data.entity.UrunlerCevap
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded

interface UrunlerDao {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun yukle() : UrunlerCevap


    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun sepeteEkle(@Field("yemek_adi") yemek_adi:String,
                           @Field("yemek_resim_adi") yemek_resim_adi:String,
                           @Field("yemek_fiyat") yemek_fiyat:Int,
                           @Field("yemek_siparis_adet") yemek_siparis_adet:Int,
                           @Field("kullanici_adi") kullanici_adi:String) : CRUDCevap

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun sepetGetir(@Field("kullanici_adi") kullanici_adi: String) : SepetCevap

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun favoriGetir(@Field("kullanici_adi") kullanici_adi: String) : SepetCevap

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun sepetSil(@Field("sepet_yemek_id") sepet_yemek_id: Int,
                         @Field("kullanici_adi")  kullanici_adi: String) : CRUDCevap

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun favorilereKaydet(@Field("yemek_adi") yemek_adi:String,
                           @Field("yemek_resim_adi") yemek_resim_adi:String,
                           @Field("yemek_fiyat") yemek_fiyat:Int,
                           @Field("yemek_siparis_adet") yemek_siparis_adet:Int,
                           @Field("kullanici_adi") kullanici_adi:String) : CRUDCevap

}