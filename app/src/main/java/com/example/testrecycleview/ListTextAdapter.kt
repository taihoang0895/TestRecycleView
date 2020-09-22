package com.example.testrecycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testrecycleview.databinding.ListTextItemBinding

class ListTextAdapter(val onItemSelectedListener:(pos:Int)->Unit) : ListAdapter<TextItemView, ListTextAdapter.ListTextViewHolder>(TextDiffUtil()) {
    lateinit var bindingItem: ListTextItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListTextViewHolder {
        bindingItem = ListTextItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListTextViewHolder(bindingItem)
    }

    override fun onBindViewHolder(holder: ListTextViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    override fun onBindViewHolder(holder: ListTextViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            holder.bindData(getItem(position))
        } else {
            val isSelected = payloads.firstOrNull() as Boolean
            if(isSelected){
                holder.binding.itemSelectedIv.setImageResource(R.drawable.callassistant_checkbox_checked_icon)
            }else{
                holder.binding.itemSelectedIv.setImageResource(R.drawable.callassistant_checkbox_unchecked_icon)
            }

        }
    }

    inner class ListTextViewHolder(val binding: ListTextItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemSelectedListener(adapterPosition)
            }

        }
        fun bindData(textItem: TextItemView) {
            if(textItem.checked){
                binding.itemSelectedIv.setImageResource(R.drawable.callassistant_checkbox_checked_icon)
            }else{
                binding.itemSelectedIv.setImageResource(R.drawable.callassistant_checkbox_unchecked_icon)
            }
            binding.contentTv.text = textItem.textItem.text
        }
    }

    override fun submitList(list: List<TextItemView>?) {
        super.submitList(ArrayList<TextItemView>(list ?: listOf()))
    }
}

class TextDiffUtil : DiffUtil.ItemCallback<TextItemView>() {
    override fun areItemsTheSame(oldItem: TextItemView, newItem: TextItemView): Boolean {
        return oldItem.textItem == newItem.textItem
    }

    override fun areContentsTheSame(oldItem: TextItemView, newItem: TextItemView): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(oldItem: TextItemView, newItem: TextItemView): Any? {
        return newItem.checked
    }
}
