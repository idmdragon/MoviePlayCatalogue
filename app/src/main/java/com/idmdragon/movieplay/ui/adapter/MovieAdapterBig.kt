package com.idmdragon.movieplay.ui.adapter

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.idmdragon.base_ui.BaseRecyclerViewAdapter
import com.idmdragon.domain.model.Movie
import com.idmdragon.movieplay.BuildConfig
import com.idmdragon.movieplay.R
import com.idmdragon.movieplay.databinding.ItemCoverBigBinding
import com.idmdragon.movieplay.databinding.ItemCoverBinding

class MovieAdapterBig(private val context: Context) : BaseRecyclerViewAdapter<Movie, ItemCoverBigBinding>() {

    override fun getViewBinding(parent: ViewGroup): ItemCoverBigBinding =
        ItemCoverBigBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun bindItem(binding: ItemCoverBigBinding, item: Movie, position: Int) {
        binding.apply {
            with(item){

                Glide.with(context)
                    .load(BuildConfig.BASE_IMAGE_URL + posterPath)
                    .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.gray)))
                    .transform(CenterCrop(), RoundedCorners(10))
                    .into(ivPoster)
            }
        }
    }
}