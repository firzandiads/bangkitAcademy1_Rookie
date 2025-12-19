package com.example.androidpemula

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListHeroAdapter(private val listHero: ArrayList<Hero>) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listHero[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvDescription.text = description

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra(DetailActivity.key_hero, listHero[holder.adapterPosition])
            intentDetail.putExtra("name", holder.itemView.context.resources.getStringArray(R.array.data_name)[holder.adapterPosition])
            intentDetail.putExtra("description", holder.itemView.context.resources.getStringArray(R.array.data_description)[holder.adapterPosition])
            intentDetail.putExtra("alamat", holder.itemView.context.resources.getStringArray(R.array.detail_alamat)[holder.adapterPosition])
            intentDetail.putExtra("notelp", holder.itemView.context.resources.getStringArray(R.array.detail_notelp)[holder.adapterPosition])
            intentDetail.putExtra("kordinat", holder.itemView.context.resources.getStringArray(R.array.detail_kordinat)[holder.adapterPosition])

            holder.itemView.context.startActivity(intentDetail)
        }
    }



    override fun getItemCount(): Int = listHero.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }
}