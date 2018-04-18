package com.sirius.cybird.ui.movie.hot



import android.support.annotation.Nullable
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sirius.cybird.R
import com.sirius.cybird.net.response.Film
import com.sirius.cybird.utils.GlideApp


class MovieHotAdapter(@param:Nullable private val films: List<Film>) : BaseQuickAdapter<Film, BaseViewHolder>(R.layout.item_film, films) {
    override fun convert(helper: BaseViewHolder, item: Film) {
        GlideApp.with(this.mContext)
                .load(item.images.medium)
                .placeholder(R.drawable.img_holder)
                .centerCrop()
                .into(helper.getView(R.id.iv_film_img))

        helper.setText(R.id.film_name, item.title)
        helper.setText(R.id.tv_score, item.rating.average.toString())

        helper.setRating(R.id.film_rating_bar, (item.rating.average / item.rating.max * 5.0f).toFloat())
    }
}