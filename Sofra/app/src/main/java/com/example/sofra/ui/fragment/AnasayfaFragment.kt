package com.example.sofra.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sofra.databinding.FragmentAnasayfaBinding
import com.example.sofra.ui.adapter.UrunlerAdapter
import com.example.sofra.ui.viewmodel.AnasayfaViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var viewModel: AnasayfaViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false)

        viewModel.urunlerListesi.observe(viewLifecycleOwner){
            val urunlerAdapter = UrunlerAdapter(requireContext(),it,viewModel)
            binding.urunlerRV.adapter = urunlerAdapter
        }

        binding.urunlerRV.layoutManager =
            GridLayoutManager(requireContext(),2)


        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: AnasayfaViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.yukle()
    }
}