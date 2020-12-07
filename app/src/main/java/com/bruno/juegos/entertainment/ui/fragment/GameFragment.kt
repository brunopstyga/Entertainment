package com.bruno.juegos.entertainment.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bruno.juegos.entertainment.R
import com.bruno.juegos.entertainment.databinding.FragmentGameBinding
import com.bruno.juegos.entertainment.model.Results
import com.bruno.juegos.entertainment.ui.adapters.QuestinsMultlipleAdapter
import com.bruno.juegos.entertainment.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GameFragment : Fragment() {

    val viewModel by activityViewModels<MainViewModel>()
    lateinit var binding: FragmentGameBinding
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    val results = ArrayList<Results>()
    val questions = ArrayList<String>()
    val questionsMultipleAdapter = QuestinsMultlipleAdapter(results) { v ->
//        viewModel.showInputData(v, payersCost.find { it.installments.toString() == v.tag.toString() })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        binding.questionsList.apply {
            adapter = questionsMultipleAdapter
            layoutManager = LinearLayoutManager(context)
        }
        return binding.root
    }

}