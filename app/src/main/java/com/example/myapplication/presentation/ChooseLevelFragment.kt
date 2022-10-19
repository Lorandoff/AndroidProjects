package com.example.myapplication.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentChooseLevelBinding
import com.example.myapplication.domain.entity.Levels

class ChooseLevelFragment : Fragment() {
    private lateinit var _binding : FragmentChooseLevelBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.buttonEasy.setOnClickListener {
            launchGameFragment(Levels.EASY)
        }
        _binding.buttonTest.setOnClickListener {
            launchGameFragment(Levels.TEST)
        }
        _binding.buttonNormal.setOnClickListener {
            launchGameFragment(Levels.NORMAL)
        }
        _binding.buttonHard.setOnClickListener {
            launchGameFragment(Levels.HARD)
        }
    }
    private fun launchGameFragment(levels : Levels) {
    findNavController().navigate(ChooseLevelFragmentDirections.
    actionChooseLevelFragment2ToGameFragment(levels))
       // requireActivity().supportFragmentManager.beginTransaction()
       //     .replace(R.id.MainContainerView, GameFragment.newInstance(levels))
        //    .addToBackStack(NAME)
        //    .commit()
    }
    companion object {
        const val NAME = "GameFragment"
        fun newInstance() : ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }
}