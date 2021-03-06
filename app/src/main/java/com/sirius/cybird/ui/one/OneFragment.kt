package com.sirius.cybird.ui.one

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kennyc.view.MultiStateView
import com.sirius.cybird.R
import com.sirius.cybird.di.component.ActivityComponent
import com.sirius.cybird.net.error.ExceptionHandler
import com.sirius.cybird.net.response.Film
import com.sirius.cybird.net.response.OneDetailData
import com.sirius.cybird.rx.DataSubscriber
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.base.BaseRecyclerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import javax.inject.Inject

/**
 *
 *Create by Botasky 2018/8/30
 */
class OneFragment : BaseRecyclerFragment<OneDetailData.Data.Content, OneAdapter.ViewHolder>() {
    @Inject
    lateinit var mPresenter: OnePresenter


    override fun setupViews() {
        super.setupViews()
        mFloatingButton?.visibility = View.GONE
    }

    override fun isEnableSwipeLayout(): Boolean {
        return false
    }

    override fun getAdapter(): BaseQuickAdapter<OneDetailData.Data.Content, OneAdapter.ViewHolder> {
        return OneAdapter()
    }

    override fun loadData() {
        val disposed = mPresenter.getLast7DayDetail()
                .compose(bindToLifecycle())
                .compose(TransformScheduler.applyNewThreadScheduler())
                .subscribe(object : DataSubscriber<List<OneDetailData.Data.Content>>(activity!!) {
                    override fun onNext(t: List<OneDetailData.Data.Content>) {
                        super.onNext(t)
                        showResults(t)
                    }
                    override fun onError(e: Throwable) {
                        super.onError(e)
                        if (mAdapter.data.isEmpty()){
                            mMultiStateView.viewState = MultiStateView.VIEW_STATE_ERROR
                        }else
                            mAdapter.loadMoreFail()
                    }

                    override fun onComplete() {
                        super.onComplete()
                        refreshEnd()
                    }
                })


//                .subscribe(
//                        { films -> showResults(films) },
//                        { e -> mMultiStateView.viewState = MultiStateView.VIEW_STATE_ERROR },
//                        { refreshEnd() }
//                )
    }


    override fun getLayouResource(): Int {
        return R.layout.fragment_one

    }

    override fun initializeInjector() {
        getComponent(ActivityComponent::class.java).inject(this)
    }

}