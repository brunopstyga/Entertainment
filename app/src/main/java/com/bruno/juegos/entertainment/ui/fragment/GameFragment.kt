package com.bruno.juegos.entertainment.ui.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bruno.juegos.entertainment.R
import com.bruno.juegos.entertainment.databinding.FragmentGameBinding
import com.bruno.juegos.entertainment.databinding.QuestionsChoiseBinding
import com.bruno.juegos.entertainment.model.ResultApi
import com.bruno.juegos.entertainment.model.Results
import com.bruno.juegos.entertainment.ui.adapters.CategoryAdapter
import com.bruno.juegos.entertainment.ui.adapters.QuestinsMultlipleAdapter
import com.bruno.juegos.entertainment.ui.viewmodel.MainViewModel
import com.bruno.juegos.entertainment.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GameFragment : Fragment() , QuestinsMultlipleAdapter.CuestionOnClickAdapterListener {

    val viewModel by activityViewModels<MainViewModel>()
    lateinit var binding: FragmentGameBinding
    val results = ArrayList<Results>()
    //    val categoryAdapter = CategoryAdapter(results)
    val questionsMultipleAdapter = QuestinsMultlipleAdapter(results, this)



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
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner){
            when(it){
                is ResultApi.Success -> {
                    results.apply {
                        binding.questionsLoading.visibility = View.GONE
//                        clear()
                        addAll(it.data.results)
                    }
                    questionsMultipleAdapter.notifyDataSetChanged()
                }
                is ResultApi.Error -> {
                    AlertDialog.Builder(context)
                        .setTitle("ERROR")
                        .setMessage("Intente más tarde")
                        .setNeutralButton("Cerrar") { _, _ ->
                        }
                        .show()
                }
            }
        }

    }

    override fun onClickViewData(position: Int, bind: QuestionsChoiseBinding) {

        val questions: MutableList<String> = mutableListOf(
            bind.result!!.incorrectAnswers.component1(),
            bind.result!!.incorrectAnswers.component2(),
            bind.result!!.incorrectAnswers.component3(),
            bind.result!!.correctAnswer)

        if(!bind.result!!.type.contains("multiple")) {
            bind.thirdAnswerRadioButton.visibility = View.GONE
            bind.fourthAnswerRadioButton.visibility = View.GONE

            when (position) {
                0 -> {
                    bind.firstAnswerRadioButton.text = questions[0]
                    bind.secondAnswerRadioButton.text = questions[1]
                }
                1 -> {
                    questions.shuffle()
                    bind.firstAnswerRadioButton.text = questions[0]
                    bind.secondAnswerRadioButton.text = questions[1]
                }
                else -> {
                    questions.shuffle()
                    bind.firstAnswerRadioButton.text = questions[0]
                    bind.secondAnswerRadioButton.text = questions[1]
                }
            }

        }else {
            when (position) {
                0 -> {
                    bind.firstAnswerRadioButton.text = questions[0]
                    bind.secondAnswerRadioButton.text = questions[1]
                    bind.thirdAnswerRadioButton.text = questions[2]
                    bind.fourthAnswerRadioButton.text = questions[3]
                }
                1 -> {
                    questions.shuffle()
                    bind.firstAnswerRadioButton.text = questions[0]
                    bind.secondAnswerRadioButton.text = questions[1]
                    bind.thirdAnswerRadioButton.text = questions[2]
                    bind.fourthAnswerRadioButton.text = questions[3]
                }
                else -> {
                    questions.shuffle()
                    bind.firstAnswerRadioButton.text = questions[0]
                    bind.secondAnswerRadioButton.text = questions[1]
                    bind.thirdAnswerRadioButton.text = questions[2]
                    bind.fourthAnswerRadioButton.text = questions[3]
                }
            }
        }

        results.forEach {
            bind.submitButton.setOnClickListener { view: View ->

                if(correctAnswer(bind.fourthAnswerRadioButton,it)){
                    showSnackBar(binding.root,correctAnswer(bind.fourthAnswerRadioButton,it))
                }else if(correctAnswer(bind.thirdAnswerRadioButton,it)){
                    showSnackBar(binding.root,correctAnswer(bind.thirdAnswerRadioButton,it))
                }else if(correctAnswer(bind.secondAnswerRadioButton,it)){
                    showSnackBar(binding.root,correctAnswer(bind.secondAnswerRadioButton,it))
                }else if(correctAnswer(bind.firstAnswerRadioButton,it)){
                    showSnackBar(binding.root,correctAnswer(bind.firstAnswerRadioButton,it))
                }else{
                    showSnackBar(binding.root,false)
                }

            }
        }

    }

    private fun correctAnswer(radioButton : RadioButton, results: Results) : Boolean {
        return radioButton.isChecked &&
                radioButton.text.trim().contains(results.correctAnswer)
    }

}