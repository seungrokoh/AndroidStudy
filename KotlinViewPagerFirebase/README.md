# KotlinViewPagerFirebase

# __:iphone: 샘플 뷰__
![Sample View](README_IMGS/sample.gif)
***

# __:seedling: Step 1 Firebase 연동 및 Library 추가__

##### 안드로이드 스튜디오에서 Firebase Database 연동하기

    Tools -> Firebase -> Realtime Database

##### Library 추가

    // design
    implementation 'com.android.support:design:28.0.0'

    // firebase
    implementation 'com.google.firebase:firebase-database:16.1.0'
    implementation 'com.google.firebase:firebase-core:16.0.8'

    // picasso
    implementation 'com.squareup.picasso:picasso:2.71828'


# __:seedling: Step 2 Model 만들기__
##### __Movie.kt__
```kotlin
class Movie {
    val name: String? = null
    val description: String? = null
    val image: String? = null
}
```
# __:seedling: Step 3 ViewPagerAdapter 만들기__
##### __MovieAdapter.kt__
```kotlin
class MovieAdapter(internal var context: Context,
                   internal var data: List<Movie>): PagerAdapter() {

    internal var layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(context)
    }

    ...

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = layoutInflater.inflate(R.layout.item_movie, container, false)
        ...

        container.addView(view)
        return view
    }
}
```

# __:seedling: Step 4 Firebase에서 데이터 가져오기__

##### MainActivity.kt
```kotlin
class MainActivity : AppCompatActivity(), IFirebaseLoadDone {
    lateinit var adapter: MovieAdapter
    lateinit var movies:DatabaseReference

    ...

    override fun onCreate(savedInstanceState: Bundle?) {
        ...
        movies = FirebaseDatabase.getInstance().getReference("Movies")
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

    ...

}
```
# __:seedling: Step 5 가져온 데이터 연동시키기__

##### MainActivity.kt
```kotlin
override fun onMovieLoadSuccess(movieList: List<Movie>) {
    adapter = MovieAdapter(this, movieList)
    vp_main.adapter = adapter
}

override fun onMovieLoadFailed(message: String) {
    Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show()
}
```
