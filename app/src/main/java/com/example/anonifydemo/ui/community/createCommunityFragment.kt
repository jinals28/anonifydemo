package com.example.anonifydemo.ui.community

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.example.anonifydemo.R
import com.example.anonifydemo.databinding.FragmentCreateCommunityBinding
import com.example.anonifydemo.databinding.FragmentOnboardBinding

class createCommunityFragment : Fragment() {

    private var _binding : FragmentCreateCommunityBinding? = null

    private val binding get() = _binding

    //private lateinit var viewModel: CreateCommunityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateCommunityBinding.inflate(layoutInflater, container, false)

        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val items = arrayOf("#entertainment", "#mentalhealth", "#bodyshaming")
        val adapter = customAdapter(requireContext(), items)
        binding!!.spinner.adapter = adapter

        binding!!.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = items[position]
                // Handle item selection
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle no selection
            }
        }
    }
        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
}