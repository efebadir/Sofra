package com.example.sofra.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sofra.databinding.FragmentSepetBinding
import com.example.sofra.ui.adapter.SepetAdapter
import com.example.sofra.ui.viewmodel.SepetViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetFragment : Fragment() {
    private lateinit var binding: FragmentSepetBinding
    private lateinit var viewModel: SepetViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSepetBinding.inflate(inflater, container, false)


        viewModel.sepetListesi.observe(viewLifecycleOwner) { sepetListesi ->

            val sepetAdapter = SepetAdapter(requireContext(), sepetListesi, viewModel)
            binding.sepetRV.adapter = sepetAdapter
            if (sepetListesi.isEmpty()) {
                binding.sepetRV.visibility = View.GONE
                binding.emptyTextView.visibility = View.VISIBLE
                binding.emptyTextView2.visibility = View.VISIBLE
                binding.emptyTextView3.visibility = View.VISIBLE
                binding.animeSepet.visibility = View.VISIBLE
            } else {
                binding.sepetRV.visibility = View.VISIBLE
                binding.emptyTextView.visibility = View.GONE
                binding.emptyTextView2.visibility = View.GONE
                binding.emptyTextView3.visibility = View.GONE
                binding.animeSepet.visibility = View.GONE
            }
            binding.textViewSubtotalAmount.text = "${viewModel.hesaplaToplamTutar()} ₺"
            binding.textViewToplamUrunAdet.text = "${viewModel.hesaplaToplamAdet()}"
            var gonderimUcreti = 5
            if (viewModel.hesaplaToplamTutar() in 1..99) {
                var toplamTutar = viewModel.hesaplaToplamTutar() + gonderimUcreti
                binding.textViewTotalAmount.text = "${toplamTutar} ₺"
                binding.textViewKuryeUcretTutar.text = "${gonderimUcreti} ₺"
            } else {
                var toplamTutar = viewModel.hesaplaToplamTutar()
                binding.textViewTotalAmount.text = "${toplamTutar} ₺"
                binding.textViewKuryeUcretTutar.text = "0 ₺"
            }

            binding.sepetRV.layoutManager = LinearLayoutManager(requireContext())
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: SepetViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.sepetGetir()


    }
}






