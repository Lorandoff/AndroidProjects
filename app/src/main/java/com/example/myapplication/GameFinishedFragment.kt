package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentGameFinishedBinding
import com.example.myapplication.domain.entity.GameResult
import com.example.myapplication.presentation.ChooseLevelFragment
import com.example.myapplication.presentation.GameFragment

class GameFinishedFragment : Fragment() {
    private val args by navArgs<GameFinishedFragmentArgs>()
   // private lateinit var gameResult: GameResult
    private lateinit var _binding : FragmentGameFinishedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return _binding.root
    }

    //override fun onCreate(savedInstanceState: Bundle?) {
    //    super.onCreate(savedInstanceState)
    //    parseArgs()
   // }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.button.setOnClickListener {
            retryGame()
        }
        //requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true){
        //    override fun handleOnBackPressed() {
         //       retryGame()
        //    }

       // })
        bindViews()
    }
    private fun bindViews() {
        with(_binding) {
            textView2.text = String.format("Красава, смотри сколько нужно набрать %s",
            args.gameResult.gameSettings.minCountofRightAnswer)
            textView3.text = String.format("Смотри сколько нарбрал ты %s",
                args.gameResult.countofRightAnswer)
            textView6.text = String.format("Вот минимальный процент правильных ответов %s",
            args.gameResult.gameSettings.minPercentofRightAnswer)
            textView7.text = String.format("Вот твой процент правильных ответов %s",
            with(args.gameResult) {
                if (countofAnswer == 0) {
                    0
                }else {
                    ((countofRightAnswer / countofAnswer.toDouble() * 100).toInt())
                }
            })
        }
    }
   // private fun parseArgs() {
    //    requireArguments().getParcelable<GameResult>(KEY_GAME_FINISHED)?.let {
    //        gameResult = it
    //    }
    //}
    private fun retryGame() {
        findNavController().popBackStack()
      //  requireActivity().supportFragmentManager.popBackStack(ChooseLevelFragment.NAME,
       //     FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
    companion object {
        private const val KEY_GAME_FINISHED = "Game_Finished"
        fun newInstance(GameResult : GameResult) : GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_FINISHED, GameResult)
                }
            }
        }
    }
}