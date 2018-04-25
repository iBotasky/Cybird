package com.sirius.cybird.ui.movie.hot


import android.databinding.DataBindingUtil
import android.support.annotation.Nullable
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sirius.cybird.BR
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ItemFilmBinding
import com.sirius.cybird.net.response.Film
import com.sirius.cybird.utils.GlideUtil


class MovieHotAdapter : BaseQuickAdapter<Film, MovieHotAdapter.ViewHolder>(R.layout.item_film) {
    override fun convert(helper: ViewHolder, item: Film) {
        val binding = helper.binding
        GlideUtil.loadImage(mContext, binding.ivFilmImg, item.images.small)
        binding.setVariable(BR.title, item.title)
        binding.setVariable(BR.rating, item.rating.average.toString())
        binding.setVariable(BR.stars, (item.rating.average * 5.0f / item.rating.max).toFloat())

        var directors: String = ""
        for (director in item.directors) {
            if (item.directors.indexOf(director) != item.directors.size - 1)
                directors += director.name + "/"
            else
                directors += director.name
        }

        binding.setVariable(BR.director, mContext.getString(R.string.movie_director, directors))
        binding.executePendingBindings()

    }


    override fun getItemView(layoutResId: Int, parent: ViewGroup): View {
        val binding: ItemFilmBinding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false)
        val view = binding.root
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding)
        return view
    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        val binding: ItemFilmBinding
            get() = itemView.getTag(R.id.BaseQuickAdapter_databinding_support) as ItemFilmBinding
    }
}