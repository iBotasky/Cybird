package com.sirius.cybird.ui.daily

import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActvityDailyDetailBinding
import com.sirius.cybird.net.response.ZHNewsDetailData
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.Navigation
import com.sirius.cybird.ui.base.BaseActivity
import com.sirius.cybird.utils.GlideUtil
import kotlinx.android.synthetic.main.actvity_daily_detail.*
import javax.inject.Inject


/**
 *Created by Botasky on 2018/5/1
 */
class DailyDetailActivity : BaseActivity() {

    @Inject
    lateinit var mPresenter: DailyDetailPresenter

    lateinit var mDetailBinding: ActvityDailyDetailBinding

    override fun setupViews() {
        super.setupViews()
        mDetailBinding = getBaseViewBinding()
        loadData()
    }

    fun loadData(){
        mPresenter.getDailyDetail(intent.getIntExtra(Navigation.EXTRA_ID, 0))
                .compose(TransformScheduler.applyNewThreadScheduler())
                .compose(bindToLifecycle())
                .subscribe(
                        {data -> loadView(data)}
                )
    }

    private fun loadView(data:ZHNewsDetailData){
        GlideUtil.loadImage(this, iv_read_bg, data.image)
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
        return R.layout.actvity_daily_detail
    }
}