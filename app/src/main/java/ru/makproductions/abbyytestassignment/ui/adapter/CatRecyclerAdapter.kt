package ru.makproductions.abbyytestassignment.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.makproductions.abbyytestassignment.R
import ru.makproductions.abbyytestassignment.model.entity.Cat
import ru.makproductions.abbyytestassignment.ui.item.CatView

class CatRecyclerAdapter(val onItemClick: ((Cat) -> Unit)? = null) :
    RecyclerView.Adapter<CatRecyclerAdapter.ItemViewHolder>() {
    var cats: List<Cat> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemViewHolder {
        val catView = LayoutInflater.from(parent.context).inflate(R.layout.cat_list_item, parent, false) as CatView
        return ItemViewHolder(catView = catView)
    }

    override fun getItemCount(): Int {
        return cats.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ItemViewHolder(val catView: CatView) : RecyclerView.ViewHolder(catView) {
        fun bind(position: Int) {
            val cat: Cat = cats[position]
            catView.catImageView.setImageBitmap(cat.image)
            catView.catTextView.text = cat.name
            itemView.setOnClickListener({ onItemClick?.invoke(cat) })
        }
    }
}