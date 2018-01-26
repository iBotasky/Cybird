package com.sirius.cybird.ui.activitys.main

import android.os.Bundle
import android.util.Log
import com.sirius.cybird.CybirdApplication
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ActivityMainBinding
import com.sirius.cybird.db.FilmEntity
import com.sirius.cybird.net.api.FilmsApi
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.base.BaseActivity
import io.objectbox.Box
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * Create by Botasky
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var mFilmBox: Box<FilmEntity>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = MainViewModel("Botasky")
        mBinding?.viewModel = viewModel
        mBinding?.executePendingBindings()
        mFilmBox = CybirdApplication.getBoxStore().boxFor(FilmEntity::class.java)
        button.setOnClickListener({
            FilmsApi.getFilmsService()
                    .getComingSoon(0, 10)
                    .flatMap { filmsData -> Observable.fromIterable(filmsData.films) }
                    .compose(bindToLifecycle())
                    .compose(TransformScheduler.applyNewThreadScheduler())
                    .subscribe({ film ->
                        val filmEntity = FilmEntity(text = film.title, comment = film.originalTitle, date = Date())
                        mFilmBox.put(filmEntity)
                        Log.e("TAG", "put success " + film.id)
                    }, { e ->
                        Log.e("TAG", e.message)
                    })
        })
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

}
