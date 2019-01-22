package com.sirius.cybird.ui.main

import android.content.Intent
import android.os.Bundle
import com.sirius.cybird.CybirdApp
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityMainBinding
import com.sirius.cybird.db.FilmEntity
import com.sirius.cybird.net.api.FilmsApi
import com.sirius.cybird.ui.home.HomeActivity
import com.sirius.cybird.ui.base.BaseActivity
import io.objectbox.Box
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast

/**
 * Create by Botasky
 */

enum class Delivery {
    STANDARD, EXPEDITED
}


class MainActivity : BaseActivity(),AnkoLogger {
    private lateinit var mFilmBox: Box<FilmEntity>
    private lateinit var mMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = MainViewModel("Botasky")
        setToolbarTitle(R.string.app_name)
        mMainBinding = getBaseViewBinding()
        mMainBinding.viewModel = viewModel
        mMainBinding.executePendingBindings()
        mFilmBox = CybirdApp.getBoxStore().boxFor(FilmEntity::class.java)
        button.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            toast("""${max(20, 30)} , default ${default(a = 2)}""")
            withTest()
            applyTest()
            letTest()
            error(getShippingCostCalculator(Delivery.EXPEDITED)(5f))
//            FilmsApi.getFilmsService()
//                    .getComingSoon(0, 10)
//                    .flatMap { filmsData -> Observable.fromIterable(filmsData.films) }
//                    .compose(bindToLifecycle())
//                    .compose(TransformScheduler.applyNewThreadScheduler())
//                    .subscribe({ film ->
//                        val filmEntity = FilmEntity(text = film.title, comment = film.originalTitle, date = Date())
//                        mFilmBox.put(filmEntity)
//                        Log.e("TAG", "put success " + film.id)
//                    }, { e ->
//                        Log.e("TAG", e.message)
//                    })
        }
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun initializeInjector() {
    }


    fun max(a: Int, b: Int): Int = if (a > b) a else b

    fun default(a: Int = 2, b: Int = 33) = if (a > b) a else b

    fun withTest() {
        val string = "Arg"
        val newString = with(string) {
            this.plus("mm")
        }
        error(newString)
    }

    fun applyTest() = "mMM".apply {
        error(this.length)
    }

    fun letTest() {
        val str: String = "Arg"
        val result = str.let {
            error(this) // Receiver this指MainActivity，
            error(it) // Argument
            42 // Block return value
        }
        error(result)
    }

    //函数作为返回值，返回的是一个用Float为参数，Double为返回值得函数计算，
    fun getShippingCostCalculator(delivery: Delivery): (Float) -> Double {
        if (delivery == Delivery.EXPEDITED) {
            return { 6 + 2.1 * it }
        }
        return { 1.3 * it }
    }
}
