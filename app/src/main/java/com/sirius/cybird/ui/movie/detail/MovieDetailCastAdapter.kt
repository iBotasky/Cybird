package com.sirius.cybird.ui.movie.detail

import android.databinding.DataBindingUtil
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ItemMovieDetailCastsActorBinding
import com.sirius.cybird.utils.GlideUtil

/**
 *Created by Botasky on 2018/5/5
 */
class MovieDetailCastAdapter(val datas:List<MovieCasts>) : BaseQuickAdapter<MovieDetailCastAdapter.MovieCasts, MovieDetailCastAdapter.ViewHolder>(R.layout.item_movie_detail_casts_actor, datas) {
    data class MovieCasts(val name: String,
                          val avatar: String,
                          val position: String)

    override fun convert(helper: ViewHolder, item: MovieCasts) {
        val binding = helper.castsActorBinding
        GlideUtil.loadImage(mContext, binding.ivAvatar, item.avatar)
        binding.actor = item
        binding.executePendingBindings()
    }

    override fun getItemView(layoutResId: Int, parent: ViewGroup?): View {
        val binding: ItemMovieDetailCastsActorBinding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false)
                ?: return super.getItemView(layoutResId, parent)

        val view = binding.root
        view.setTag(R.id.tag_movie_casts_actor, binding)
        return view
    }


    class ViewHolder(view: View) : BaseViewHolder(view) {
        val castsActorBinding: ItemMovieDetailCastsActorBinding
            get() = itemView.getTag(R.id.tag_movie_casts_actor) as ItemMovieDetailCastsActorBinding
    }
}