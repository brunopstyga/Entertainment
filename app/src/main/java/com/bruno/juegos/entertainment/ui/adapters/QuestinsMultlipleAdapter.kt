package com.bruno.juegos.entertainment.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bruno.juegos.entertainment.databinding.ContentTypeLayoutBinding
import com.bruno.juegos.entertainment.model.Results

class QuestinsMultlipleAdapter(
        val result: List<Results>,
) :
    RecyclerView.Adapter<QuestinsMultlipleAdapter.ViewHolder>() {

    class ViewHolder(val bind: ContentTypeLayoutBinding) : RecyclerView.ViewHolder(bind.root) {
        fun bind(results: Results) {
            bind.result = results

            bind.tvQuestion.text.toString().replace(Regex("[^A-Za-z0-9]").toString(),"")

            val questions: MutableList<String> = mutableListOf(
                    results.incorrectAnswers.component2(),
                    results.incorrectAnswers.component1(),
                    results.incorrectAnswers.component3(),
                    results.correctAnswer)

            if(!results.type.contains("multiple")) {
                bind.thirdAnswerRadioButton.visibility = View.GONE
                bind.fourthAnswerRadioButton.visibility = View.GONE

                when (adapterPosition) {
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
                when (adapterPosition) {
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

            bind.submitButton.setOnClickListener { view: View ->
                if(bind.fourthAnswerRadioButton.isChecked &&
                        bind.fourthAnswerRadioButton.text.trim().contains(results.correctAnswer.trim())){
                    Toast.makeText(view.context, "Correcta", Toast.LENGTH_SHORT).show()
                }else if(bind.thirdAnswerRadioButton.isChecked &&
                        bind.secondAnswerRadioButton.text.trim().contains(results.correctAnswer.trim())){
                    Toast.makeText(view.context, "Correcta", Toast.LENGTH_SHORT).show()
                }else if(bind.secondAnswerRadioButton.isChecked &&
                        bind.secondAnswerRadioButton.text.trim().contains(results.correctAnswer.trim())){
                    Toast.makeText(view.context, "Correcta", Toast.LENGTH_SHORT).show()
                }else if(bind.firstAnswerRadioButton.isChecked &&
                        bind.firstAnswerRadioButton.text.trim().contains(results.correctAnswer.trim())){
                    Toast.makeText(view.context, "Correcta", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(view.context, "INCORRECTA", Toast.LENGTH_SHORT).show()
                }

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ContentTypeLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(result[position])
    }
}


