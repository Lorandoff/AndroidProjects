package com.example.myapplication.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.WelcomeBlank2Binding

class WelcomeFragment : Fragment() {
    private lateinit var _binding : WelcomeBlank2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = WelcomeBlank2Binding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.buttonUnderstand.setOnClickListener {
            launchChooseLevelFragment()
        }
    }
    private fun launchChooseLevelFragment(){
    findNavController().navigate(R.id.action_welcomeFragment_to_chooseLevelFragment2)
    //   requireActivity().supportFragmentManager.beginTransaction()
      //      .replace(R.id.MainContainerView, ChooseLevelFragment.newInstance())
       //     .addToBackStack(null)
         //   .commit()
    }
}