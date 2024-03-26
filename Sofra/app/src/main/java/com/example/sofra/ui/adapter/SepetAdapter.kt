package com.example.sofra.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sofra.data.entity.SepetYemek
import com.example.sofra.databinding.SepetCardTasarimBinding
import com.example.sofra.ui.viewmodel.SepetViewModel
import com.google.android.material.snackbar.Snackbar

class SepetAdapter(var mContext: Context,
                   var sepetListesi: List<SepetYemek>,
                   var viewModel: SepetViewModel)
    : RecyclerView.Adapter<SepetAdapter.CardTasarimTutucu>() {
        inner class CardTasarimTutucu(var tasarim2: SepetCardTasarimBinding) : RecyclerView.ViewHolder(tasarim2.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
            val binding = SepetCardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
            return CardTasarimTutucu(binding)
        }

        override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
            val sepet = sepetListesi.get(position)
            val t2 = holder.tasarim2
            val url = "http://kasimadalan.pe.hu/yemekler/resimler/${sepet.yemek_resim_adi}"

            t2.textViewSepetUrunAd.text = sepet.yemek_adi

            t2.textViewSepetUrunFiyat.text = "${sepet.yemek_fiyat} ₺"

            Glide.with(mContext).load(url).into(t2.imageViewSepetResim)


            t2.textViewAdet.text = "Adet : ${sepet.yemek_siparis_adet}"

            t2.imageButtonClose.setOnClickListener {
                Snackbar.make(it,"${sepet.yemek_adi} silinsin mi?",Snackbar.LENGTH_INDEFINITE)
                    .setAction("EVET"){
                    viewModel.sil(sepet.sepet_yemek_id,sepet.kullanici_adi)
                }.show()
            }


            val toplam = sepet.yemek_fiyat*sepet.yemek_siparis_adet


            t2.textViewSepetUrunToplam.text = "${toplam} ₺"
            t2.textViewAdet.text = "Adet: ${sepet.yemek_siparis_adet}"



        }


    override fun getItemCount(): Int {
        return sepetListesi.size
    }




}
