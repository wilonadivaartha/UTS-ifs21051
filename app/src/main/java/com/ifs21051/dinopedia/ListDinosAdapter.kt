package com.ifs21051.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21051.dinopedia.databinding.ItemRowDinosBinding

class ListDinosAdapter(private val listDinos: ArrayList<Dinos>) :
    RecyclerView.Adapter<ListDinosAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowDinosBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val Dinos = listDinos[position]
        holder.binding.ivDetailIcon.setImageResource(Dinos.icon)
        holder.binding.tvDetailName.text = Dinos.nama
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listDinos[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listDinos.size

    class ListViewHolder(var binding: ItemRowDinosBinding) : RecyclerView.ViewHolder(binding.root)
    interface OnItemClickCallback {
        fun onItemClicked(data: Dinos)
    }
}