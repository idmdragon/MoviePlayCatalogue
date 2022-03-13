package com.idmdragon.search.ui.adapter

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.idmdragon.domain.model.Search
import com.idmdragon.movieplay.R
import com.idmdragon.search.BuildConfig
import com.idmdragon.search.databinding.ItemSearchBinding


class SearchListAdapter : PagingDataAdapter<Search, SearchListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<Search>() {
        override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean =
            oldItem.id == newItem.id
    }
) {

    var onUserClickListener: ((Int, String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListAdapter.ViewHolder {
        val itemBinding =
            ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SearchListAdapter.ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Search) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(BuildConfig.BASE_IMAGE_URL+item.posterPath)
                    .placeholder(ColorDrawable(ContextCompat.getColor(itemView.context, R.color.gray)))
                    .transform(CenterCrop(), RoundedCorners(10))
                    .into(ivPoster)

                tvTitle.text = item.title
                tvDate.text = item.releaseDate
                tvDate.isVisible = item.releaseDate.isNotEmpty()
                tvMediaType.text = item.mediaType.replaceFirstChar {it.uppercase()}

                ratingBar.rating = (item.voteAverage / 2).toFloat()
                root.setOnClickListener {
                    onUserClickListener?.invoke(item.id,item.mediaType)
                }
            }
        }
    }
}