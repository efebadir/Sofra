package com.example.sofra.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sofra.data.entity.Urunler
import com.example.sofra.databinding.CardTasarimBinding
import com.example.sofra.ui.fragment.AnasayfaFragmentDirections
import com.example.sofra.ui.viewmodel.AnasayfaViewModel
import com.example.sofra.utils.gecis


class UrunlerAdapter(var mContext: Context,
                     var urunlerListesi:List<Urunler>,
                     var viewModel: AnasayfaViewModel)
    : RecyclerView.Adapter<UrunlerAdapter.CardTasarimHolder>() {
    inner class CardTasarimHolder(var tasarim:CardTasarimBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimHolder {
        val binding = CardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardTasarimHolder(binding)
    }

    override fun onBindViewHolder(holder: CardTasarimHolder, position: Int) {
        val urun = urunlerListesi.get(position)
        val t = holder.tasarim
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${urun.yemek_resim_adi}"

        t.textViewDetayUrunAd.text = urun.yemek_adi

        t.textViewDetayUrunFiyat.text = "${urun.yemek_fiyat} ₺"

        Glide.with(mContext).load(url).override(300,300).into(t.imageViewDetayUrun)

        t.cardViewUrun.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.detayGecis(urun = urun)
            Navigation.gecis(it,gecis)
        }

        t.imageButtonDetayEkle.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.detayGecis(urun = urun)
            Navigation.gecis(it,gecis)
        }

    }


    override fun getItemCount(): Int {
        return urunlerListesi.size
    }


}