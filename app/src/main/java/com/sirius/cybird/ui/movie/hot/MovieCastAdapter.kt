package com.sirius.cybird.ui.movie.hot

import android.databinding.DataBindingUtil
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sirius.cybird.BR
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ItemFilmCastsBinding
import com.sirius.cybird.net.response.Cast
import com.sirius.cybird.utils.GlideUtil
import org.jetbrains.annotations.NotNull

class MovieCastAdapter(@NotNull casts: List<Cast>) : BaseQuickAdapter<Cast, MovieCastAdapter.ViewHolder>(R.layout.item_film_casts, casts) {
    override fun convert(helper: ViewHolder, item: Cast) {
        val binding = helper.binding
        if (item.avatars != null &&!TextUtils.isEmpty(item.avatars.small)) {
            GlideUtil.loadImageCenterCrop(mContext, binding.ivAvatar, item.avatars.small)
        }
        binding.setVariable(BR.name, item.name)
        binding.executePendingBindings()
    }


    override fun getItemView(layoutResId: Int, parent: ViewGroup): View {
        //有loadmore的时候需要return super.getItemView(layoutResId, parent)
        val binding: ItemFilmCastsBinding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false)
                ?: return super.getItemView(layoutResId, parent)
        val view = binding.root
        view.setTag(R.id.id_tag_movie_cast, binding)
        return view
    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        val binding: ItemFilmCastsBinding
            get() = itemView.getTag(R.id.id_tag_movie_cast) as ItemFilmCastsBinding
    }
}