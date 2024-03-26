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
class SepetViewModel @Inject constructor(var urepo: UrunlerRepository) : ViewModel() {
    var sepetListesi = MutableLiveData<List<SepetYemek>>()


    fun sil(sepet_yemek_id:Int,kullanici_adi:String){
        CoroutineScope(Dispatchers.Main).launch {
            if (sepetListesi.value?.size == 1){
                urepo.sil(sepet_yemek_id,kullanici_adi)
                sepetListesi.value = emptyList()
            }else{
                urepo.sil(sepet_yemek_id,kullanici_adi)
            }
            sepetGetir()
        }
    }

    fun sepetGetir(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                sepetListesi.value = urepo.sepetGetir("efe_badir")
            }catch (e: Exception){
            }

        }
    }

    fun hesaplaToplamTutar(): Int {
        var toplamTutar = 0
        sepetListesi.value?.forEach { sepetYemek ->
            toplamTutar += sepetYemek.yemek_fiyat * sepetYemek.yemek_siparis_adet
        }
        return toplamTutar
    }

    fun hesaplaToplamAdet(): Int {
        var toplamAdet = 0
        sepetListesi.value?.forEach { sepetYemek ->
            toplamAdet += sepetYemek.yemek_siparis_adet
        }
        return toplamAdet
        }
    }

