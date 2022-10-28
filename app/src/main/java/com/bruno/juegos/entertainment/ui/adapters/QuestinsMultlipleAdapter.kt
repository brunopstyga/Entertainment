package com.bruno.juegos.entertainment.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bruno.juegos.entertainment.databinding.QuestionsChoiseBinding
import com.bruno.juegos.entertainment.model.Results

class QuestinsMultlipleAdapter(
    val result: List<Results>,
    private val listener: CuestionOnClickAdapterListener,
) :
    RecyclerView.Adapter<QuestinsMultlipleAdapter.ViewHolder>() {

    interface CuestionOnClickAdapterListener {
        fun onClickViewData(position: Int, view: QuestionsChoiseBinding)
    }

    class ViewHolder(val bind: QuestionsChoiseBinding,
                     private val listener: CuestionOnClickAdapterListener
    ) : RecyclerView.ViewHolder(bind.root) {
        fun bind(results: Results) {
            bind.result = results

            bind.tvQuestion.text.toString()
                .replace(Regex("[&^A-Za-z0-9]").toString().toLowerCase(),"")

            listener.onClickViewData(adapterPosition, bind)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = QuestionsChoiseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(result[position])
    }
}


