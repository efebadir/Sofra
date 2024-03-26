package com.example.sofra.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sofra.databinding.FragmentFavoriBinding
import com.example.sofra.ui.adapter.FavoriAdapter
import com.example.sofra.ui.viewmodel.FavoriViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriFragment : Fragment() {
    private lateinit var binding: FragmentFavoriBinding
    private lateinit var viewModel: FavoriViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriBinding.inflate(inflater, container, false)

        viewModel.favoriListesi.observe(viewLifecycleOwner){ favoriListesi ->
            val favoriAdapter = FavoriAdapter(requireContext(),favoriListesi,viewModel)
            binding.favoriRV.adapter = favoriAdapter
            if (favoriListesi.isEmpty()){
                binding.emptyTextViewFav.visibility = View.VISIBLE
                binding.favoriRV.visibility = View.GONE
                binding.animeFood.visibility = View.VISIBLE
            } else {
                binding.emptyTextViewFav.visibility = View.GONE
                binding.favoriRV.visibility = View.VISIBLE
                binding.animeFood.visibility = View.GONE
            }
            binding.favoriRV.layoutManager =
                GridLayoutManager(requireContext(),2)
        }

        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FavoriViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.favoriGetir("efe_badir_fav")
    }
}