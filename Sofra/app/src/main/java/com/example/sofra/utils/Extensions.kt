package com.example.sofra.utils

import android.view.View
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.sofra.data.entity.SepetYemek

fun Navigation.gecis(it: View, id:Int){
    findNavController(it).navigate(id)
}

fun Navigation.gecis(it: View, id: NavDirections){
    findNavController(it).navigate(id)
}




