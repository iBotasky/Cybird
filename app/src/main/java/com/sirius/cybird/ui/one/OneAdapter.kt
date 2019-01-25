package com.sirius.cybird.ui.one

import android.databinding.DataBindingUtil
import android.view.View
import android.view.ViewGroup
import com.android.databinding.library.baseAdapters.BR
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ItemOneDetailBinding
import com.sirius.cybird.net.response.OneDetailData
import com.sirius.cybird.ui.Navigation
import com.sirius.cybird.utils.GlideUtil

/**
 *
 *Create by Botasky 2018/8/30
 */
class OneAdapter: BaseQuickAdapter<OneDetailData.Data.Content, OneAdapter.ViewHolder>(R.layout.item_one_detail) {

    override fun convert(helper: ViewHolder, item: OneDetailData.Data.Content) {
        val binding = helper.binding
        GlideUtil.loadImageCenterCrop(mContext, binding.ivImg, item.imgUrl)
        binding.root.setOnClickListener {
            Navigation.startOneDetail(mContext, item)
        }
        binding.setVariable(BR.content, item)
        binding.executePendingBindings()
    }

    override fun getItemView(layoutResId: Int, parent: ViewGroup): View {
        //有loadmore的时候需要return super.getItemView(layoutResId, parent)
        val binding: ItemOneDetailBinding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false)
                ?: return super.getItemView(layoutResId, parent)
        val view = binding.root
        view.setTag(R.id.id_tag_one_detail, binding)
        return view
    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        val binding: ItemOneDetailBinding
            get() = itemView.getTag(R.id.id_tag_one_detail) as ItemOneDetailBinding
    }
}