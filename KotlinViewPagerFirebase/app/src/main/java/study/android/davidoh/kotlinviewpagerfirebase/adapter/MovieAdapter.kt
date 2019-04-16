package study.android.davidoh.kotlinviewpagerfirebase.adapter

import android.content.Context
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso
import study.android.davidoh.kotlinviewpagerfirebase.R
import study.android.davidoh.kotlinviewpagerfirebase.model.Movie

class MovieAdapter(internal var context: Context,
                   internal var data: List<Movie>): PagerAdapter() {

    internal var layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(context)
    }
    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // Inflate view
        val view = layoutInflater.inflate(R.layout.item_movie, container, false)
        // View
        val ivImage = view.findViewById<View>(R.id.iv_movie) as ImageView
        val tvTitle = view.findViewById<View>(R.id.tv_title) as TextView
        val tvDescription = view.findViewById<View>(R.id.tv_movie_desctiption) as TextView
        val fbFavorite = view.findViewById<View>(R.id.fb_favorite) as FloatingActionButton

        // Set Data
        Picasso.get().load(data[position].image).into(ivImage)
        tvTitle.text = data[position].name
        tvDescription.text = data[position].description

        // Event
        fbFavorite.setOnClickListener {
            Toast.makeText(context, "Fb Clicked", Toast.LENGTH_SHORT).show()
        }

        view.setOnClickListener {
            Toast.makeText(context, "" + data[position].name, Toast.LENGTH_SHORT).show()
        }

        container.addView(view)
        return view
    }
}