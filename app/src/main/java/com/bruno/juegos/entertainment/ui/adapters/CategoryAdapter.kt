package com.bruno.juegos.entertainment.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bruno.juegos.entertainment.databinding.CategoryListBinding
import com.bruno.juegos.entertainment.model.Results

class CategoryAdapter(val result: List<Results>,
) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(val binding: CategoryListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(results: Results) {
            binding.result = results
            binding.cardViewClick.setOnClickListener(View.OnClickListener {
             results.category
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding = CategoryListBinding.inflate(
           LayoutInflater.from(parent.context),
           parent,
           false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         holder.bind(result[position])
    }

    override fun getItemCount(): Int = result.size
}