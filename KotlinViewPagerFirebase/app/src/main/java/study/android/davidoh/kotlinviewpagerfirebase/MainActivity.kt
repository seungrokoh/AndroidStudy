package study.android.davidoh.kotlinviewpagerfirebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import study.android.davidoh.kotlinviewpagerfirebase.adapter.MovieAdapter
import study.android.davidoh.kotlinviewpagerfirebase.listener.IFirebaseLoadDone
import study.android.davidoh.kotlinviewpagerfirebase.model.Movie
import study.android.davidoh.kotlinviewpagerfirebase.util.DepthPageTransformer

class MainActivity : AppCompatActivity(), IFirebaseLoadDone {
    lateinit var adapter: MovieAdapter

    lateinit var movies:DatabaseReference
    lateinit var iFirebaseLoadDone: IFirebaseLoadDone

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Init DB
        movies = FirebaseDatabase.getInstance().getReference("Movies")

        // Init event
        iFirebaseLoadDone = this

        loadMovie()

        vp_main.setPageTransformer(true, DepthPageTransformer())

    }

    private fun loadMovie() {
        movies.addListenerForSingleValueEvent(object : ValueEventListener {
            var movies:MutableList<Movie> = ArrayList()

            override fun onCancelled(p0: DatabaseError) {
                iFirebaseLoadDone.onMovieLoadFailed(p0.message)
            }

            override fun onDataChange(p0: DataSnapshot) {
                for (movieSnapShot in p0.children) {
                    val movie = movieSnapShot.getValue(Movie::class.java)
                    movies.add(movie!!)
                }

                iFirebaseLoadDone.onMovieLoadSuccess(movies)
            }
        })
    }

    override fun onMovieLoadSuccess(movieList: List<Movie>) {
        adapter = MovieAdapter(this, movieList)
        vp_main.adapter = adapter
    }

    override fun onMovieLoadFailed(message: String) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show()
    }
}
