package com.example.sofra.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.sofra.R
import com.example.sofra.databinding.FragmentDetayBinding
import com.example.sofra.ui.viewmodel.DetayViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class DetayFragment : Fragment() {
    private lateinit var binding: FragmentDetayBinding
    private lateinit var viewModel: DetayViewModel
    private var isToogled = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetayBinding.inflate(inflater, container, false)

        val bundle: DetayFragmentArgs by navArgs()
        val gelenUrun = bundle.urun
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenUrun.yemek_resim_adi}"



        binding.twUrunAd.text = gelenUrun.yemek_adi
        binding.twUrunFiyat.text = "${gelenUrun.yemek_fiyat} ₺"


        Glide.with(this)
            .load(url)
            .override(700, 700)
            .into(binding.ivUrunResim)

        var value = binding.editTextSepetNumberAdet.text.toString().toInt()

        binding.imageButtonArttir.setOnClickListener {
            value++
            binding.editTextSepetNumberAdet.setText(value.toString())
        }

        binding.imageButtonAzalt.setOnClickListener {
            if (value > 0) {
                value--
                binding.editTextSepetNumberAdet.setText(value.toString())
            }
        }

        binding.imageButtonBack.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.anasayfaFragment)
        }

        binding.buttonSepet.setOnClickListener {
            if (binding.editTextSepetNumberAdet.text.toString().toInt() != 0) {
                val yemek_siparis_adet = binding.editTextSepetNumberAdet.text.toString().toInt()
                binding.animeAdd.playAnimation()
                viewModel.sepeteKaydet(
                    gelenUrun.yemek_adi,
                    gelenUrun.yemek_resim_adi,
                    gelenUrun.yemek_fiyat,
                    yemek_siparis_adet,
                    "efe_badir"
                )
            } else {
                Snackbar.make(
                    binding.ivUrunResim,
                    "Lütfen Adet Sayısı Girin",
                    Snackbar.LENGTH_SHORT
                )
                    .show()
            }
        }

        binding.buttonFavEkle.setOnClickListener {
            if (isToogled){
                binding.checkbox.speed = -1f
                binding.checkbox.playAnimation()
                isToogled = false
                binding.buttonFavEkle.setText("Favorilere\nKaydet")

            } else {
                binding.checkbox.speed = 1f
                binding.checkbox.playAnimation()
                isToogled = true
                binding.buttonFavEkle.setText("Favorilerden\nÇıkar")
            }
            viewModel.favorilereKaydet(
                gelenUrun.yemek_adi,
                gelenUrun.yemek_resim_adi,
                gelenUrun.yemek_fiyat,
                0,
                "efe_badir_fav"
            )

        }



        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetayViewModel by viewModels()
        viewModel = tempViewModel


    }
}

