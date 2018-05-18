package com.sirius.cybird.ui.photo

import android.support.v4.view.ViewPager
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.blankj.utilcode.util.ToastUtils
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityPhotoViewBinding
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.Navigation
import com.sirius.cybird.ui.base.BaseActivity
import com.sirius.cybird.utils.GlideUtil
import io.reactivex.Observable

/**
 *Created by Botasky on 2018/5/12
 */
class PhotoViewActivity : BaseActivity() {

    lateinit var mPhotoViewBinding: ActivityPhotoViewBinding
    lateinit var mPhotos: List<String>

    val mToolbarDismissAnimation: Animation = AlphaAnimation(1.0f, 0.0f)
    val mToolbarDisplayAnimation: Animation = AlphaAnimation(0.0f, 1.0f)

    var mCurrentIndex = 0

    lateinit var mPhotoViewModel: PhotoViewModel

    override fun setupViews() {
        super.setupViews()
        mPhotoViewBinding = getBaseViewBinding()
        mPhotos = intent.getStringArrayListExtra(Navigation.EXTRA_DATA)
        mCurrentIndex = intent.getIntExtra(Navigation.EXTRA_INDEX, 0)
        mPhotoViewModel = PhotoViewModel(mCurrentIndex, mPhotos.size)
        mPhotoViewBinding.photo = mPhotoViewModel
        setupViewPager()
        setupDownload()
    }

    private fun setupDownload() {
        mPhotoViewBinding.ivDownload.setOnClickListener({
            Observable.just(mPhotos[mCurrentIndex])
                    .flatMap { url -> GlideUtil.saveImage(this, url) }
                    .compose(TransformScheduler.applyNewThreadScheduler())
                    .compose(bindToLifecycle())
                    .subscribe(
                            { s -> ToastUtils.showShort(s) },
                            { e -> ToastUtils.showShort(e.message) },
                            {}
                    )
        })
    }

    private fun setupViewPager() {
        mToolbarDisplayAnimation.duration = 100
        mToolbarDismissAnimation.duration = 100

        mToolbarDisplayAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                mToolbar?.visibility = View.VISIBLE
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })
        mToolbarDismissAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                mToolbar?.visibility = View.INVISIBLE
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })


        mPhotoViewBinding.idViewPager.adapter = PhotoViewAdapter(mPhotos, this, object : PhotoViewAdapter.Listener {
            override fun callback() {
                if (mToolbar?.visibility == View.INVISIBLE) {
                    mToolbar?.startAnimation(mToolbarDisplayAnimation)
                } else {
                    mToolbar?.startAnimation(mToolbarDismissAnimation)
                }
            }
        })
        mPhotoViewBinding.idViewPager.currentItem = mCurrentIndex
        mPhotoViewBinding.idViewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                mCurrentIndex = position
                mPhotoViewModel.index = position
            }
        })
    }

    override fun isDisplayHomeAsUpEnable(): Boolean {
        return true
    }

    override fun initializeInjector() {
        component.inject(this)
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_photo_view
    }
}