package com.sirius.cybird.ui.photo

import android.support.v4.view.ViewPager
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityPhotoViewBinding
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.Navigation
import com.sirius.cybird.ui.base.BaseActivity
import com.sirius.cybird.utils.GlideUtil
import io.reactivex.Observable
import org.jetbrains.anko.toast

/**
 *Created by Botasky on 2018/5/12
 */
class PhotoViewActivity : BaseActivity() {

    lateinit var mPhotoViewBinding: ActivityPhotoViewBinding
    lateinit var mPhotos: List<String>
    lateinit var mPhotosId: List<String>

    val mToolbarDismissAnimation: Animation = AlphaAnimation(1.0f, 0.0f)
    val mToolbarDisplayAnimation: Animation = AlphaAnimation(0.0f, 1.0f)

    var mCurrentIndex = 0

    lateinit var mPhotoViewModel: PhotoViewModel

    override fun setupViews() {
        super.setupViews()
        mPhotoViewBinding = getBaseViewBinding()
        mPhotos = intent.getStringArrayListExtra(Navigation.EXTRA_DATA)
        mPhotosId = intent.getStringArrayListExtra(Navigation.EXTRA_ID)
        mCurrentIndex = intent.getIntExtra(Navigation.EXTRA_INDEX, 0)
        mPhotoViewModel = PhotoViewModel(mCurrentIndex, mPhotos.size)
        mPhotoViewBinding.photo = mPhotoViewModel
        setupViewPager()
        setupDownload()
    }

    private fun setupDownload() {
        mPhotoViewBinding.ivDownload.setOnClickListener(({
            Observable.just(mPhotos[mCurrentIndex])
                    .flatMap { url -> GlideUtil.saveImage(this, url, mPhotosId[mCurrentIndex]) }
                    .compose(TransformScheduler.applyNewThreadScheduler())
                    .compose(bindToLifecycle())
                    .subscribe(
                            { s -> toast(s) },
                            { e -> toast(e.message!!) },
                            {}
                    )
        }))
    }

    private fun setupViewPager() {
        mToolbarDisplayAnimation.duration = 200
        mToolbarDismissAnimation.duration = 200

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