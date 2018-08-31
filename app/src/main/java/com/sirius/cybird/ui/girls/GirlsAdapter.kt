package com.sirius.cybird.ui.girls

import android.databinding.DataBindingUtil
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ItemGrilsBinding
import com.sirius.cybird.net.response.ResultsBean
import com.sirius.cybird.ui.Navigation
import com.sirius.cybird.utils.GlideUtil
import java.util.ArrayList

/**
 *Created by Botasky on 2018/4/30
 */
class GirlsAdapter : BaseQuickAdapter<ResultsBean, GirlsAdapter.ViewHolder>(R.layout.item_grils) {
    var mPhotos: MutableList<String> = mutableListOf()
    var mPhotosId: MutableList<String> = mutableListOf()

    override fun convert(helper: ViewHolder, item: ResultsBean) {
        val binding = helper.binding
        GlideUtil.loadImageCenterCrop(mContext, binding.ivImg, item.url)
        binding.root.setOnClickListener(
                ({
                    mPhotos.clear()
                    for (bean in mData) {
                        mPhotos.add(bean.url)
                        mPhotosId.add(bean._id)
                    }
                    Navigation.startPhotosView(mContext, mPhotos.toList() as ArrayList<String>, mPhotosId.toList() as ArrayList<String>, helper.adapterPosition)
                })
        )
    }

    override fun getItemView(layoutResId: Int, parent: ViewGroup): View {
        //有loadmore的时候需要return super.getItemView(layoutResId, parent)
        val binding: ItemGrilsBinding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false)
                ?: return super.getItemView(layoutResId, parent)
        val view = binding.root
        view.setTag(R.id.id_tag_girl, binding)
        return view
    }

    class ViewHolder(view: View) : BaseViewHolder(view) {
        val binding: ItemGrilsBinding
            get() = itemView.getTag(R.id.id_tag_girl) as ItemGrilsBinding
    }
}