package com.sirius.cybird.ui.mine

import com.sirius.cybird.R
import com.sirius.cybird.databinding.FragmentMineBinding
import com.sirius.cybird.di.component.ActivityComponent
import com.sirius.cybird.ui.base.BaseFragment

/**
 * Created By Botasky 28/04/2018
 */
class MineFragment : BaseFragment() {
    lateinit var mMineBinding: FragmentMineBinding

    override fun setupViews() {
        super.setupViews()
        mMineBinding = getBaseViewBinding()
        initViews()
    }

    private fun initViews(){

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun getLayouResource(): Int {
        return R.layout.fragment_mine
    }

    override fun initializeInjector() {
        getComponent(ActivityComponent::class.java).inject(this)
    }
}