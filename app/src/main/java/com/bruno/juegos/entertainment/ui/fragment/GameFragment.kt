package com.bruno.juegos.entertainment.ui.fragment

import android.app.AlertDialog
import android.content.Context
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
import com.bruno.juegos.entertainment.model.ResultApi
import com.bruno.juegos.entertainment.model.Results
import com.bruno.juegos.entertainment.ui.adapters.QuestinsMultlipleAdapter
import com.bruno.juegos.entertainment.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GameFragment : Fragment() {

    val viewModel by activityViewModels<MainViewModel>()
    lateinit var binding: FragmentGameBinding
    val results = ArrayList<Results>()
    val questionsMultipleAdapter = QuestinsMultlipleAdapter(results)



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

}