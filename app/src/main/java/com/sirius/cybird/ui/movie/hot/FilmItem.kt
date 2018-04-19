package com.sirius.cybird.ui.movie.hot

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import com.mikepenz.fastadapter.items.AbstractItem
import com.sirius.cybird.BR
import com.sirius.cybird.R
import com.sirius.cybird.databinding.ItemFilmBinding
import com.sirius.cybird.net.response.Film

class FilmItem(var mFilm: Film) : AbstractItem<FilmItem, FilmItem.ViewHolder>() {
    init {
        withIdentifier(mFilm.id.toLong())
    }

    override fun getType(): Int {
        return Film::class.java.hashCode()
    }

    /**
     * defines the layout which will be used for this item in the list
     *
     * @return the layout for this item
     */
    override fun getLayoutRes(): Int {
        return R.layout.item_film
    }

    /**
     * binds the data of this item onto the viewHolder
     *
     * @param viewHolder the viewHolder of this item
     */
    override fun bindView(viewHolder: ViewHolder, payloads: List<*>?) {
        super.bindView(viewHolder, payloads)

        //get the context
        val ctx = viewHolder.itemView.context
        viewHolder.binding.setVariable(BR.title, mFilm.title)
        viewHolder.binding.setVariable(BR.rating, mFilm.rating.average.toString())
        viewHolder.binding.executePendingBindings()
//        viewHolder.binding!!.setVariable(BR.title, mCourseInfo.getTitle())
//        viewHolder.binding!!.setVariable(BR.tag, mCourseInfo.getTag())
//        viewHolder.binding!!.setVariable(BR.time, UtilTime.convertTime3(mCourseInfo.getTime()))
//        viewHolder.binding!!.setVariable(BR.rating, mCourseInfo.getLevel())
//        viewHolder.binding!!.setVariable(BR.level, "Lv." + mCourseInfo.getLevel())
//        viewHolder.binding!!.setVariable(BR.desc, ctx.getString(R.string.course_count_desc, "" + mCourseInfo.getCount()))
//        viewHolder.binding!!.setVariable(BR.img, mCourseInfo.getImg())
//        viewHolder.binding!!.setVariable(BR.name, mCourseInfo.getCoachName())
//        viewHolder.binding!!.executePendingBindings()
    }

    override fun getViewHolder(view: View): ViewHolder {
        return ViewHolder(view)
    }

    /**
     * our ViewHolder
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding:ItemFilmBinding = DataBindingUtil.bind(view)!!
    }

}