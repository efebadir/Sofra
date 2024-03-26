package com.example.sofra.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sofra.data.entity.Urunler
import com.example.sofra.data.repo.UrunlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(var urepo:UrunlerRepository)  : ViewModel() {
    var urunlerListesi = MutableLiveData<List<Urunler>>()


    fun yukle(){
        CoroutineScope(Dispatchers.Main).launch {
            urunlerListesi.value = urepo.yukle()
        }
    }
}
