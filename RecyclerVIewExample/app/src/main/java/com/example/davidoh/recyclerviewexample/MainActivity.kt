package com.example.davidoh.recyclerviewexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = ContentRecyclerAdapter(this, DataService.contentData)
        recyclerView.adapter = adapter

        val recyclerLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = recyclerLayoutManager
        recyclerView.setHasFixedSize(true)

    }

}
