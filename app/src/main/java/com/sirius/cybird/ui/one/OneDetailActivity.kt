package com.sirius.cybird.ui.one

import android.graphics.Bitmap
import android.graphics.Canvas
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityOneDetailBinding
import com.sirius.cybird.net.response.OneDetailData
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.Navigation
import com.sirius.cybird.ui.base.BaseActivity
import com.sirius.cybird.utils.FileUtils
import com.sirius.cybird.utils.GlideUtil
import io.reactivex.Observable
import org.jetbrains.anko.error
import org.jetbrains.anko.toast

/**
 *
 *Create by Botasky 2019/1/24
 */
class OneDetailActivity : BaseActivity() {
    lateinit var mOneDetailBinding: ActivityOneDetailBinding
    lateinit var mData: OneDetailData.Data.Content

    override fun setupViews() {
        super.setupViews()
        mData = intent.getParcelableExtra(Navigation.EXTRA_DATA)
        error { "back${mData.imgUrl}" }
        mOneDetailBinding = getBaseViewBinding()
        mOneDetailBinding.content = mData
        GlideUtil.loadImageCenterCrop(this, mOneDetailBinding.ivImg, mData.imgUrl)

        mOneDetailBinding.ivDownload.setOnClickListener {
            val disposed = Observable.just(mOneDetailBinding.container)
                    .flatMap {
                        val bitmap = Bitmap.createBitmap(it.width, it.height, Bitmap.Config.ARGB_8888)
                        val canvas = Canvas(bitmap)
                        it.draw(canvas)
                        Observable.just(bitmap)
                    }
                    .flatMap {
                        GlideUtil.saveImage(this@OneDetailActivity, it, mData.volume)
                    }
                    .compose(TransformScheduler.applyNewThreadScheduler())
                    .compose(bindToLifecycle())
                    .subscribe {
                        toast(it)
                    }
        }
    }

    override fun isDisplayHomeAsUpEnable(): Boolean {
        return true
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_one_detail
    }

    override fun initializeInjector() {
        mActivityComponent.inject(this)
    }
}