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

/**
 * Create by Botasky
 */
class MainActivity : BaseActivity() {
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
        button.setOnClickListener(({
            startActivity(Intent(this, HomeActivity::class.java))
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
        }))
    }

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun initializeInjector() {
    }
}
