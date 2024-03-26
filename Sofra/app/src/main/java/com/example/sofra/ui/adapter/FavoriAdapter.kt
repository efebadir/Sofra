package com.example.sofra.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sofra.data.entity.SepetYemek
import com.example.sofra.databinding.FavoriCardTasarimBinding
import com.example.sofra.ui.viewmodel.FavoriViewModel
import com.google.android.material.snackbar.Snackbar


class FavoriAdapter(var mContext: Context,
                    var favoriListesi: List<SepetYemek>,
                    var viewModel: FavoriViewModel
)
    : RecyclerView.Adapter<FavoriAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(var tasarimFavori: FavoriCardTasarimBinding) : RecyclerView.ViewHolder(tasarimFavori.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val binding = FavoriCardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val favori = favoriListesi.get(position)
        val tf = holder.tasarimFavori
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${favori.yemek_resim_adi}"

        Glide.with(mContext).load(url).override(300,300).into(tf.imageViewFavoriUrun)

        tf.textViewFavoriUrunAd.text = favori.yemek_adi

        tf.textViewFavoriUrunFiyat.text = "${favori.yemek_fiyat} ₺"

        tf.imageViewFavori.setOnClickListener {
            Snackbar.make(it,"${favori.yemek_adi} favorilerden kaldırılsın mı?", Snackbar.LENGTH_INDEFINITE)
                .setAction("EVET"){
                    viewModel.sil(favori.sepet_yemek_id,favori.kullanici_adi)
                }.show()
        }

        tf.cardViewUrun.setOnClickListener {
            // val gecis = FavoriFragmentDirections.favoriDetayGecis
        }

    }


    override fun getItemCount(): Int {
        return favoriListesi.size
    }


}