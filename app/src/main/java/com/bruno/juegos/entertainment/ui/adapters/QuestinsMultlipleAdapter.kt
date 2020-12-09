package com.bruno.juegos.entertainment.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bruno.juegos.entertainment.databinding.ContentTypeMultipleBinding
import com.bruno.juegos.entertainment.model.Results

class QuestinsMultlipleAdapter(
    val result: List<Results>,
//    val onClickListener: View.OnClickListener
) :
    RecyclerView.Adapter<QuestinsMultlipleAdapter.ViewHolder>() {

    class ViewHolder(val bind: ContentTypeMultipleBinding) : RecyclerView.ViewHolder(bind.root) {
        fun bind(results: Results) {
            bind.result = results
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ContentTypeMultipleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        binding.categoryItemCard.setOnClickListener(onClickListener)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(result[position])
    }
}


