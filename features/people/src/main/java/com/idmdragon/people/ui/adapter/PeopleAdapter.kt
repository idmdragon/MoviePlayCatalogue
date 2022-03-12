package com.idmdragon.people.ui.adapter

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.idmdragon.domain.model.People
import com.idmdragon.movieplay.BuildConfig
import com.idmdragon.movieplay.databinding.ItemCoverBinding
import com.idmdragon.people.databinding.ItemPersonBinding
import com.idmdragon.movieplay.R as appR

class PeopleAdapter : PagingDataAdapter<People, PeopleAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<People>() {
        override fun areItemsTheSame(oldItem: People, newItem: People): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: People, newItem: People): Boolean =
            oldItem.id == newItem.id
    }
) {

    var onClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleAdapter.ViewHolder {
        val itemBinding =
            ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PeopleAdapter.ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(private val binding: ItemPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: People) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(BuildConfig.BASE_IMAGE_URL+item.profilePath)
                    .placeholder(ColorDrawable(ContextCompat.getColor(itemView.context, appR.color.gray)))
                    .transform(CenterCrop(), RoundedCorners(10))
                    .into(ivPoster)

                root.setOnClickListener {
                    onClickListener?.invoke(item.id)
                }
            }
        }
    }
}