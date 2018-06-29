package com.example.davidoh.recyclerviewexample

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import java.net.URL

class ContentRecyclerAdapter(val context: Context, val contentData: ArrayList<Content>) : RecyclerView.Adapter<ContentRecyclerAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return contentData.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(contentData[position],context)
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var title = itemView?.findViewById<TextView>(R.id.layoutitem_title)
        var message = itemView?.findViewById<TextView>(R.id.layoutitem_content)
        var image = itemView?.findViewById<ImageView>(R.id.layoutitem_imageview)

        fun bind(content: Content, context: Context){
            //val resourId = context.resources.getIdentifier(content.photo, "drawable", context.packageName)

            Glide.with(context).load(URL(content.photo)).into(image!!)
            //image?.setImageResource(resourId)
            val resourId = context.resources.getIdentifier(content.photo, "drawable", context.packageName)
            image?.setImageResource(resourId)
          
            title?.text = content.title
            message?.text = content.message

        }
    }
}