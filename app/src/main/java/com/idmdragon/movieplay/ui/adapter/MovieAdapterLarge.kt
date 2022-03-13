package com.idmdragon.movieplay.ui.adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.idmdragon.base_ui.BaseRecyclerViewAdapter
import com.idmdragon.domain.model.MovieTv
import com.idmdragon.movieplay.BuildConfig
import com.idmdragon.movieplay.R
import com.idmdragon.movieplay.constant.ConstantExtras
import com.idmdragon.movieplay.constant.ConstantPage
import com.idmdragon.movieplay.databinding.ItemCoverBigBinding

class MovieAdapterLarge(private val context: Context) :
    BaseRecyclerViewAdapter<MovieTv, ItemCoverBigBinding>() {

    override fun getViewBinding(parent: ViewGroup): ItemCoverBigBinding =
        ItemCoverBigBinding.inflate(LayoutInflater.from(context), parent, false)

    override fun bindItem(binding: ItemCoverBigBinding, item: MovieTv, position: Int) {
        binding.apply {
            with(item) {

                root.setOnClickListener {
                    context.startActivity(
                        Intent(
                            context,
                            Class.forName(ConstantPage.PAGE_DETAIL_MOVIE)
                        ).apply {
                            putExtra(ConstantExtras.EXTRAS_MOVIE_ID,item.id)
                            putExtra(ConstantExtras.EXTRAS_MOVIE_TYPE,item.movieType)
                        }
                    )
                }

                Glide.with(context)
                    .load(BuildConfig.BASE_IMAGE_URL + posterPath)
                    .placeholder(ColorDrawable(ContextCompat.getColor(context, R.color.grayDark)))
                    .transform(CenterCrop(), RoundedCorners(10))
                    .into(ivPoster)
            }
        }
    }
}