package com.sirius.cybird.ui.photo

import android.support.v4.view.ViewPager
import android.view.View
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityPhotoViewBinding
import com.sirius.cybird.ui.Navigation
import com.sirius.cybird.ui.base.BaseActivity

/**
 *Created by Botasky on 2018/5/12
 */
class PhotoViewActivity : BaseActivity() {

    lateinit var mPhotoViewBinding: ActivityPhotoViewBinding
    lateinit var mPhotos: List<String>

    var mCurrentIndex = 0

    override fun setupViews() {
        super.setupViews()
        mPhotoViewBinding = getBaseViewBinding()
        mPhotos = intent.getStringArrayListExtra(Navigation.EXTRA_DATA)
        mCurrentIndex = intent.getIntExtra(Navigation.EXTRA_INDEX, 0)
        setToolbarTitle("" + mCurrentIndex + "/" + mPhotos.size)

        setupViewPager()

    }

    private fun setupViewPager(){
        mPhotoViewBinding.idViewPager.adapter = PhotoViewAdapter(mPhotos, this)
        mPhotoViewBinding.idViewPager.currentItem = mCurrentIndex - 1
        mPhotoViewBinding.idViewPager.setOnPageChangeListener(object :ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {

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