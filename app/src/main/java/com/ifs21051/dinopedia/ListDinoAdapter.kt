package com.ifs21051.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21051.dinopedia.databinding.ItemRowDinoBinding

class ListDinoAdapter(private val listDinos: ArrayList<Dinos>) :
    RecyclerView.Adapter<ListDinoAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback:
                               OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType:
    Int): ListViewHolder {
        val binding =
            ItemRowDinoBinding.inflate(LayoutInflater.from(viewGroup.context),
                viewGroup, false)
        return ListViewHolder(binding)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position:
    Int) {
        val dinos = listDinos[position]
        holder.binding.ivDetaiDinolIcon.setImageResource(dinos.gambar)
        holder.binding.tvDetailNameDino.text = dinos.nama
        holder.itemView.setOnClickListener {
            onItemClickCallback
                .onItemClicked(listDinos[holder.adapterPosition])
        }
    }
    override fun getItemCount(): Int = listDinos.size
    class ListViewHolder(var binding: ItemRowDinoBinding) :
        RecyclerView.ViewHolder(binding.root)
    interface OnItemClickCallback {
        fun onItemClicked(data: Dinos)
    }
}