package com.sirius.cybird.ui.movie.hot



import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.Nullable
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sirius.cybird.BR
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ItemFilmBinding
import com.sirius.cybird.net.response.Film


class MovieHotAdapter(@param:Nullable private val films: List<Film>) : BaseQuickAdapter<Film, MovieHotAdapter.MovieViewHolder>(R.layout.item_film, films) {
    override fun convert(helper: MovieViewHolder, item: Film) {
        val binding = helper.binding
        binding.setVariable(BR.title, item.title)
        binding.setVariable(BR.rating, item.rating.average)
        binding.executePendingBindings()
    }


    override fun getItemView(layoutResId: Int, parent: ViewGroup): View {
        val binding:ItemFilmBinding  = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false)
        val view = binding.root
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding)
        return view
    }

    class MovieViewHolder(view: View) : BaseViewHolder(view) {
        val binding: ItemFilmBinding
            get() = itemView.getTag(R.id.BaseQuickAdapter_databinding_support) as ItemFilmBinding
    }
}