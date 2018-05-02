package com.sirius.cybird.ui.daily

import com.kennyc.view.MultiStateView
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityDailyDetailBinding
import com.sirius.cybird.net.response.ZHNewsDetailData
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.Navigation
import com.sirius.cybird.ui.base.BaseActivity
import com.sirius.cybird.utils.GlideUtil
import kotlinx.android.synthetic.main.activity_daily_detail.*
import javax.inject.Inject


/**
 *Created by Botasky on 2018/5/1
 */
class DailyDetailActivity : BaseActivity() {

    @Inject
    lateinit var mPresenter: DailyDetailPresenter

    lateinit var mDetailBinding: ActivityDailyDetailBinding

    override fun setupViews() {
        super.setupViews()
        mDetailBinding = getBaseViewBinding()
        GlideUtil.loadImage(this, iv_read_bg, intent.getStringExtra(Navigation.EXTRA_IMG))
        loadData()
        //设置MultiStateView的逻辑
        setOnRetry {
            if (mMultiStateView!!.viewState != MultiStateView.VIEW_STATE_LOADING){
                mMultiStateView!!.viewState = MultiStateView.VIEW_STATE_LOADING
                loadData()
            }
        }
    }

    override fun loadData(){
        mPresenter.getDailyDetail(intent.getIntExtra(Navigation.EXTRA_ID, 0))
                .compose(TransformScheduler.applyNewThreadScheduler())
                .compose(bindToLifecycle())
                .subscribe(
                        {data -> loadView(data)},
                        {e->mMultiStateView!!.viewState = MultiStateView.VIEW_STATE_ERROR},
                        {}
                )
    }

    private fun loadView(data:ZHNewsDetailData){
        mMultiStateView!!.viewState = MultiStateView.VIEW_STATE_CONTENT
        mDetailBinding.collapsingToolbar.title = data.title
        mDetailBinding.wvContent.settings.javaScriptEnabled = true
        val css = "<link rel=\"stylesheet\" href=\"" + data.css[0] + "\" type=\"text/css\">"
        var html = "<html><head>" + css + "</head><body>" + data.body + "</body></html>"
        html = html.replace("<div class=\"img-place-holder\">", "")
        wv_content.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null)
    }

    override fun isDisplayHomeAsUpEnable(): Boolean {
        return true
    }

    override fun initializeInjector() {
        component.inject(this)
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_daily_detail
    }

    override fun onDestroy() {
        super.onDestroy()
        mDetailBinding.wvContent.removeAllViews()
        mDetailBinding.wvContent.destroy()
    }
}