package com.sirius.cybird.ui.home

import android.content.Context
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.kennyc.view.MultiStateView
import com.sirius.cybird.R
import com.sirius.cybird.databinding.FragmentMovieHotBinding
import com.sirius.cybird.di.component.ActivityComponent
import com.sirius.cybird.net.api.FilmsApi
import com.sirius.cybird.rx.TransformScheduler
import com.sirius.cybird.ui.base.BaseRecyclerFragment
import com.sirius.cybird.utils.ToastUtils
import retrofit2.Retrofit
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class MovieHotFragment : BaseRecyclerFragment() {

    @Inject
    lateinit var homePresenter: HomePresenter

    override fun onRefresh() {
        loadData()
    }

    lateinit var mMovieHotBinding: FragmentMovieHotBinding
    override fun setupViews() {
        super.setupViews()
        mMovieHotBinding = getBaseViewBinding()
    }

    override fun getLayouResource(): Int {
        return R.layout.fragment_movie_hot
    }

    override fun loadData() {
        homePresenter.getFilms()
        homePresenter.getGirls()
        Handler().postDelayed(
                {
                    mRecyclerView.adapter = SimpleStringRecyclerViewAdapter(activity!!, getRandomSublist(sCheeseStrings, 30))
                    mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
                }
                , 3000)

        setOnRetry { ToastUtils.show(R.string.tab_mine) }
    }

    val sCheeseStrings = arrayOf("Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi", "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre", "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese", "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro", "Appenzell", "Aragon", "Ardi Gasna", "Ardrahan", "Armenian String", "Aromes au Gene de Marc", "Asadero", "Asiago", "Aubisque Pyrenees", "Autun", "Avaxtskyr", "Baby Swiss", "Babybel", "Baguette Laonnaise", "Bakers", "Baladi", "Balaton", "Bandal", "Banon", "Barry's Bay Cheddar", "Basing", "Basket Cheese", "Bath Cheese", "Bavarian Bergkase", "Baylough", "Beaufort", "Beauvoorde", "Beenleigh Blue", "Beer Cheese", "Bel Paese", "Bergader", "Bergere Bleue", "Berkswell")
    private fun getRandomSublist(array: Array<String>, amount: Int): List<String> {
        val list = ArrayList<String>(amount)
        val random = Random()
        while (list.size < amount) {
            list.add(array[random.nextInt(array.size)])
        }
        return list
    }

    class SimpleStringRecyclerViewAdapter(context: Context, private val mValues: List<String>) : RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder>() {

        private val mTypedValue = TypedValue()
        private val mBackground: Int

        class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
            var mBoundString: String? = null
            val mImageView: ImageView
            val mTextView: TextView

            init {
                mImageView = mView.findViewById(R.id.avatar) as ImageView
                mTextView = mView.findViewById<View>(android.R.id.text1) as TextView
            }

            override fun toString(): String {
                return super.toString() + " '" + mTextView.text
            }
        }

        init {
            context.theme.resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true)
            mBackground = mTypedValue.resourceId
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item, parent, false)
            view.setBackgroundResource(mBackground)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.mBoundString = mValues[position]
            holder.mTextView.text = position.toString() + " " + mValues[position]

            holder.mView.setOnClickListener { v ->
            }

            holder.mImageView.setImageResource(R.mipmap.ic_account)
        }


        override fun getItemCount(): Int {
            return mValues.size
        }
    }

    override fun initializeInjector() {
        getComponent(ActivityComponent::class.java).inject(this)
    }
}