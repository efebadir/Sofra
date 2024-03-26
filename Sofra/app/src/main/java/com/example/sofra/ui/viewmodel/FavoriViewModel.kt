package com.example.sofra.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sofra.data.entity.SepetYemek
import com.example.sofra.data.repo.UrunlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriViewModel  @Inject constructor(var urepo: UrunlerRepository) : ViewModel() {
    var favoriListesi = MutableLiveData<List<SepetYemek>>()

    fun sil(sepet_yemek_id:Int,kullanici_adi:String){
        CoroutineScope(Dispatchers.Main).launch {
            if (favoriListesi.value?.size == 1){
                urepo.sil(sepet_yemek_id,kullanici_adi)
                favoriListesi.value = emptyList()
            }else{
                urepo.sil(sepet_yemek_id,kullanici_adi)
            }
            favoriGetir(kullanici_adi)
        }
    }

    fun favoriGetir(kullanici_adi:String){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                favoriListesi.value = urepo.favoriGetir(kullanici_adi)
            }catch (e: Exception){

            }
        }
    }
}