package com.sirius.cybird.ui.girls

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kennyc.view.MultiStateView
import com.sirius.cybird.R
import com.sirius.cybird.databinding.FragmentGirlsBinding
import com.sirius.cybird.di.component.ActivityComponent
import com.sirius.cybird.net.response.ResultsBean
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.base.BaseRecyclerFragment
import javax.inject.Inject

/**
 *Created by Botasky on 2018/4/30
 */
class GirlsFragment : BaseRecyclerFragment<ResultsBean, GirlsAdapter.ViewHolder>() {

    @Inject
    lateinit var mPresenter: GirlsPresenter

    lateinit var mGirlsBinding: FragmentGirlsBinding

    override fun setupViews() {
        super.setupViews()
        mGirlsBinding = getBaseViewBinding()
    }

    override fun getAdapter(): BaseQuickAdapter<ResultsBean, GirlsAdapter.ViewHolder> {
        return GirlsAdapter()
    }

    override fun loadData() {
        mPresenter.getGirls(mPage)
                .compose(TransformScheduler.applyNewThreadScheduler())
                .compose(bindToLifecycle())
                .subscribe(
                        { results -> showResults(results) },
                        { e -> mMultiStateView.viewState = MultiStateView.VIEW_STATE_ERROR },
                        { refreshEnd() }
                )
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager {
        return GridLayoutManager(context, 2)
    }

    override fun getLayouResource(): Int {
        return R.layout.fragment_girls
    }

    override fun initializeInjector() {
        getComponent(ActivityComponent::class.java).inject(this)
    }

}