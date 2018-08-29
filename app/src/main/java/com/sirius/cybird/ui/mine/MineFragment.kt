package com.sirius.cybird.ui.mine

import com.sirius.cybird.BR
import com.sirius.cybird.R
import com.sirius.cybird.databinding.FragmentMineBinding
import com.sirius.cybird.di.component.ActivityComponent
import com.sirius.cybird.net.response.OneDetailData
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.base.BaseFragment
import com.sirius.cybird.utils.GlideUtil
import javax.inject.Inject

/**
 * Created By Botasky 28/04/2018
 */
class MineFragment : BaseFragment() {
    @Inject
    lateinit var mMinePresenter:MinePresenter

    lateinit var mMineBinding: FragmentMineBinding

    override fun setupViews() {
        super.setupViews()
        mMineBinding = getBaseViewBinding()
        initDatas()
    }

    private fun initDatas(){
        mMinePresenter.getLastDayInfo()
                .compose(bindToLifecycle())
                .compose(TransformScheduler.applyNewThreadScheduler())
                .subscribe(
                        { detail -> initView(detail.data, detail.data.weather)},
                        { e -> e.printStackTrace() },
                        {}
                )
    }

    private fun initView(detail:OneDetailData.Data, weather: OneDetailData.Data.Weather){
        var content = detail.contentList[0]
        GlideUtil.loadImage(activity!!, mMineBinding.ivBg, content.imgUrl)
        mMineBinding.setVariable(BR.content, content)
        mMineBinding.setVariable(BR.weather, weather)
        mMineBinding.executePendingBindings()
    }

    override fun getLayouResource(): Int {
        return R.layout.fragment_mine
    }

    override fun initializeInjector() {
        getComponent(ActivityComponent::class.java).inject(this)
    }
}