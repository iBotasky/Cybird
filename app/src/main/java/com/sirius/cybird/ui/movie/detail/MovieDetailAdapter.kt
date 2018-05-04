package com.sirius.cybird.ui.movie.detail

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ItemMovieDetailHeadBinding

/**
 * Created By Botasky 2018/5/4
 */
class MovieDetailAdapter(val list: List<MultiItemEntity>) : BaseMultiItemQuickAdapter<MultiItemEntity, MovieDetailAdapter.ViewHolder>(list) {

    init {
        addItemType(HEAD, R.layout.item_movie_detail_head)
    }

    companion object {
        val HEAD = 1
        val SUMMARY = 2
        val CASTS = 3
    }

    override fun convert(helper: ViewHolder?, item: MultiItemEntity?) {

    }


    override fun getItemView(layoutResId: Int, parent: ViewGroup?): View {

        when (layoutResId) {
            R.layout.item_movie_detail_head -> {
                val binding: ItemMovieDetailHeadBinding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false)
                val view = binding.root
                view.setTag(R.id.tag_movie_head, binding)
                return view
            }

            else -> {
                return super.getItemView(layoutResId, parent)
            }
        }

    }


    class ViewHolder(view: View) : BaseViewHolder(view) {
        val headBinding: ItemMovieDetailHeadBinding
            get() = itemView.getTag(R.id.tag_movie_head) as ItemMovieDetailHeadBinding
    }
}