package com.example.myapplication.presentation

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.GameFinishedFragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentGameBinding
import com.example.myapplication.domain.entity.GameInterface
import com.example.myapplication.domain.entity.GameResult
import com.example.myapplication.domain.entity.Levels

class GameFragment : Fragment() {
    private val args by navArgs<GameFragmentArgs>()
    //private lateinit var levels: Levels
    private val viewModelFactory by lazy {
        ViewModelFactory(args.levels, requireActivity().application)
    }
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ViewModel::class.java]
    }
    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(_binding.option1)
            add(_binding.option2)
            add(_binding.option3)
            add(_binding.option4)
            add(_binding.option5)
            add(_binding.option6)
        }
    }
    private lateinit var _binding : FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return _binding.root
    }

   // override fun onCreate(savedInstanceState: Bundle?) {
    //    super.onCreate(savedInstanceState)
    //    parseArgs()
    //}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setClickListenersOnClickOptions()

    }
    private fun setClickListenersOnClickOptions() {
        for (tvoption in tvOptions) {
            tvoption.setOnClickListener {
                viewModel.chooseAnswer(tvoption.text.toString().toInt())
            }

        }
    }
    private fun observeViewModel() {
        viewModel.questions.observe(viewLifecycleOwner) {
            _binding.tvCount.text = it.sum.toString()
            _binding.tvLeftNumber.text = it.visibleNumber.toString()
            for (i in 0 until tvOptions.size) {
                tvOptions[i].text = it.options[i].toString()
            }
        }
        viewModel.percentofRightAnswers.observe(viewLifecycleOwner) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                _binding.progressBar.setProgress(it, true)
            }
        }
        viewModel.enoughcountofRightAnswers.observe(viewLifecycleOwner){
            _binding.tvQuestion.setTextColor(setColor(it))

        }
        viewModel.enoughpercentofRightAnswers.observe(viewLifecycleOwner) {
            val color = setColor(it)
            _binding.progressBar.progressTintList = ColorStateList.valueOf(color)
        }
        viewModel.formattedTime.observe(viewLifecycleOwner) {
            _binding.textView.text = it
        }
        viewModel.minPercent.observe(viewLifecycleOwner) {
            _binding.progressBar.secondaryProgress = it
        }
        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
        viewModel.progressAnswers.observe(viewLifecycleOwner) {
            _binding.tvQuestion.text = it
        }
    }
    private fun setColor(it : Boolean) : Int {
        val colorResId = if (it) {
            android.R.color.holo_green_light
        }else {
            android.R.color.holo_red_light
        }
        return ContextCompat.getColor(requireContext(),colorResId)
    }
   // private fun parseArgs(){
     //   requireArguments().getParcelable<Levels>(KEY_LEVEL)?.let {
       //     levels = it
      //  } //as Levels
   // }
    private fun launchGameFinishedFragment(gameResult: GameResult) {
      findNavController().navigate(GameFragmentDirections.
      actionGameFragmentToGameFinishedFragment(gameResult))
       // requireActivity().supportFragmentManager.beginTransaction()
       //     .replace(R.id.MainContainerView, GameFinishedFragment.newInstance(gameResult))
        //    .addToBackStack(null)
        //    .commit()
    }
    companion object {
        private const val KEY_LEVEL = "level"
        fun newInstance(levels: Levels) : GameFragment{
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, levels)
                }
            }
        }
    }
}