package com.sirius.cybird.ui.movie.detail

import android.databinding.DataBindingUtil
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.sirius.cybird.BR
import com.sirius.cybird.R
import com.sirius.cybird.databinding.*
import com.sirius.cybird.net.response.DetailCasts
import com.sirius.cybird.net.response.DetailComment
import com.sirius.cybird.net.response.DetailHead
import com.sirius.cybird.net.response.DetailSummary
import com.sirius.cybird.utils.divider.HorizontalSpaceDecoration

/**
 * Created By Botasky 2018/5/4
 */
class MovieDetailAdapter(val list: List<MultiItemEntity>) : BaseMultiItemQuickAdapter<MultiItemEntity, MovieDetailAdapter.ViewHolder>(list) {

    init {
        addItemType(HEAD, R.layout.item_movie_detail_head)
        addItemType(CASTS, R.layout.item_movie_detail_casts)
        addItemType(SUMMARY, R.layout.item_movie_detail_summary)
        addItemType(COMMENT, R.layout.item_movie_detail_comment)
    }

    companion object {
        const val HEAD = 1
        const val SUMMARY = 2
        const val CASTS = 3
        const val COMMENT = 4
    }

    override fun convert(helper: ViewHolder, item: MultiItemEntity) {
        when (item.itemType) {
            HEAD -> {
                val head = item as DetailHead
                val binding = helper.headBinding
                binding.setVariable(BR.head, head)
                binding.executePendingBindings()

            }
            CASTS -> {
                val cast = item as DetailCasts
                val binding = helper.castsBinding
                val adapter = MovieDetailCastAdapter(cast.actors)
                binding.rvCasts.layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false)
                if (binding.rvCasts.itemDecorationCount == 0)
                    binding.rvCasts.addItemDecoration(HorizontalSpaceDecoration(R.dimen.dimen_20, R.dimen.dimen_0, R.dimen.dimen_10))
                binding.rvCasts.adapter = adapter
                binding.executePendingBindings()
            }
            SUMMARY -> {
                val summary = item as DetailSummary
                val binding = helper.summaryBinding
                binding.setVariable(BR.summary, summary.summary)
                binding.executePendingBindings()
            }
            COMMENT ->{
                val comment = item as DetailComment
                val binding = helper.commentBinding
                binding.setVariable(BR.data, comment)
                binding.executePendingBindings()
            }
        }
    }


    override fun getItemView(layoutResId: Int, parent: ViewGroup?): View {
        when (layoutResId) {
            R.layout.item_movie_detail_head -> {
                val binding: ItemMovieDetailHeadBinding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false)
                val view = binding.root
                view.setTag(R.id.tag_movie_head, binding)
                return view
            }
            R.layout.item_movie_detail_summary -> {
                val binding: ItemMovieDetailSummaryBinding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false)
                val view = binding.root
                view.setTag(R.id.tag_movie_summary, binding)
                return view
            }
            R.layout.item_movie_detail_casts -> {
                val binding: ItemMovieDetailCastsBinding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false)
                val view = binding.root
                view.setTag(R.id.tag_movie_casts, binding)
                return view
            }
            R.layout.item_movie_detail_comment->{
                val binding:ItemMovieDetailCommentBinding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false)
                val view = binding.root
                view.setTag(R.id.tag_movie_comment, binding)
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

        val summaryBinding: ItemMovieDetailSummaryBinding
            get() = itemView.getTag(R.id.tag_movie_summary) as ItemMovieDetailSummaryBinding

        val castsBinding: ItemMovieDetailCastsBinding
            get() = itemView.getTag(R.id.tag_movie_casts) as ItemMovieDetailCastsBinding

        val commentBinding: ItemMovieDetailCommentBinding
            get()= itemView.getTag(R.id.tag_movie_comment) as ItemMovieDetailCommentBinding
    }
}