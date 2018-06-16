package com.sirius.cybird.ui

import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.transition.AutoTransition
import android.support.transition.TransitionManager
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.sirius.cybird.R
import kotlinx.android.synthetic.main.activity_test_1.*

/**
 * 本来相拥在Splash，但是发现在onCreate写的动画是没法动的，只能通过像这样用点击事件触发。
 * Created By Botasky 2018/5/24
 */
class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_1)

        addAnimationOperations()
    }

    private fun addAnimationOperations() {
        var set = false
        val constraint1 = ConstraintSet()
        constraint1.clone(constraint)
        val constraint2 = ConstraintSet()
        constraint2.clone(this, R.layout.activity_test_2)

        findViewById<TextView>(R.id.textview).setOnClickListener {

            val transition = AutoTransition()
            transition.duration = 2000
            TransitionManager.beginDelayedTransition(this.constraint, transition)
            val constraint = if (set) constraint1 else constraint2
            constraint.applyTo(this.constraint)
            set = !set
        }

    }

}