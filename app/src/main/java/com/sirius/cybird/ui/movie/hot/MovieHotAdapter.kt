package com.sirius.cybird.ui.movie.hot


import android.support.annotation.Nullable
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sirius.cybird.BR
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ItemFilmBinding
import com.sirius.cybird.net.response.Film
import com.sirius.cybird.utils.GlideApp


class MovieHotAdapter(@param:Nullable private val films: List<Film>) : BaseQuickAdapter<Film, MovieHotAdapter.MovieViewHolder>(R.layout.item_film, films) {
    override fun convert(helper: MovieViewHolder, item: Film) {
        val binding = helper.binding
        GlideApp.with(this.mContext)
                .load("https://www.baidu.com/img/superlogo_c4d7df0a003d3db9b65e9ef0fe6da1ec.png")
                .placeholder(R.drawable.img_holder)
                .centerCrop()
                .into(helper.getView(R.id.iv_film_img))
        binding.setVariable(BR.item, item)
        binding.executePendingBindings()
    }


    override fun getItemView(layoutResId: Int, parent: ViewGroup): View {
        val binding: ItemFilmBinding = ItemFilmBinding.inflate(mLayoutInflater, parent, false)
        val view = binding.root
        view.setTag(R.id.id_tag_movie, binding)
        return view
    }

    class MovieViewHolder(view: View) : BaseViewHolder(view) {
        val binding: ItemFilmBinding
            get() = itemView.getTag(R.id.id_tag_movie) as ItemFilmBinding
    }
}