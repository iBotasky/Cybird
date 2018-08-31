package com.sirius.cybird.ui.movie.top

import android.databinding.DataBindingUtil
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sirius.cybird.BR
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ItemFilmTopBinding
import com.sirius.cybird.net.response.Film
import com.sirius.cybird.ui.Navigation
import com.sirius.cybird.utils.GlideUtil

class MovieTopAdapter : BaseQuickAdapter<Film, MovieTopAdapter.ViewHolder>(R.layout.item_film_top) {
    override fun convert(helper: ViewHolder, item: Film) {
        val binding = helper.binding
        GlideUtil.loadImageCenterCrop(mContext, binding.ivFilmImg, item.images.small)
        GlideUtil.loadImageCenterCrop(mContext, binding.ivDirector, item.directors[0].avatars.small)
        var directors: String = ""
        for (director in item.directors) {
            if (item.directors.indexOf(director) != item.directors.size - 1)
                directors += director.name + "/"
            else
                directors += director.name
        }
        var tags = ""
        for (tag in item.genres) {
            if (item.genres.indexOf(tag) != item.genres.size - 1)
                tags += tag + "/"
            else
                tags += tag
        }
        binding.setVariable(BR.tag, mContext.getString(R.string.movie_tag, tags))
        binding.setVariable(BR.rank, mContext.getString(R.string.movie_rank, mData.indexOf(item) + 1))
        binding.setVariable(BR.director, mContext.getString(R.string.movie_director, directors))
        binding.setVariable(BR.title, mContext.getString(R.string.movie_top_title,  item.title))
        binding.setVariable(BR.rating, item.rating.average.toString())
        binding.setVariable(BR.stars, (item.rating.average * 5.0f / item.rating.max).toFloat())
        binding.executePendingBindings()

        binding.root.setOnClickListener({ Navigation.startFilmDetail(mContext, item.id, item.images.small)})
    }


    override fun getItemView(layoutResId: Int, parent: ViewGroup): View {
        //有loadmore的时候需要return super.getItemView(layoutResId, parent)
        val binding: ItemFilmTopBinding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false)
                ?: return super.getItemView(layoutResId, parent)
        val view = binding.root
        view.setTag(R.id.id_tag_movie_top, binding)
        return view
    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        val binding: ItemFilmTopBinding
            get() = itemView.getTag(R.id.id_tag_movie_top) as ItemFilmTopBinding
    }
}