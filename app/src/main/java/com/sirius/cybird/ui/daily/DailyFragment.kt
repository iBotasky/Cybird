package com.sirius.cybird.ui.daily

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.sirius.cybird.R
import com.sirius.cybird.di.component.ActivityComponent
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.base.BaseRecyclerMultiFragment
import javax.inject.Inject

class DailyFragment: BaseRecyclerMultiFragment<DailyAdapter.ViewHolder>() {

    @Inject
    lateinit var mPresenter: DailyPresenter


    override fun getAdapter(): BaseMultiItemQuickAdapter<MultiItemEntity, DailyAdapter.ViewHolder> {
        return DailyAdapter(arrayListOf())
    }

    override fun loadData() {
        mPresenter.getDailyStories()
                .compose(bindToLifecycle())
                .compose(TransformScheduler.applyNewThreadScheduler())
                .subscribe({multiItems-> showResult(multiItems)})
    }

    private fun showResult(multiList:List<MultiItemEntity>){
        mAdapter.setNewData(multiList)
    }

    override fun getLayouResource(): Int {
        return R.layout.fragment_daily
    }

    override fun initializeInjector() {
        getComponent(ActivityComponent::class.java).inject(this)
    }

}