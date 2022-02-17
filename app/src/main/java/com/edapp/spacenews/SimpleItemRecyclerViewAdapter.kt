package com.edapp.spacenews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.edapp.spacenews.views.ItemDetailFragment.Companion.ARG_ITEM_ID
import com.edapp.spacenews.models.SpaceNewsArticle
import com.edapp.spacenews.views.ArticleListActivity
import com.squareup.picasso.Picasso

class SimpleItemRecyclerViewAdapter(
    private val parentActivity: ArticleListActivity,
    private var values: List<SpaceNewsArticle>
) :
    RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener = View.OnClickListener { v ->
        val item = v.tag as SpaceNewsArticle

        val bundle = Bundle()
        bundle.putInt(
            ARG_ITEM_ID,
            item.id
        )
        parentActivity.navController.navigate(R.id.show_item_detail, bundle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        Picasso.with(holder.image.context).load(item.imageUrl)
            .resize(150,150)
            .centerCrop()
            .into(holder.image)

        holder.title.text = item.title

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.list_item_image)
        val title: TextView = view.findViewById(R.id.list_item_title)
    }
}