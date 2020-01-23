package com.preeyanut.freshy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.json.JSONArray

class ProductAdapter(context: Context,private val dataSource: JSONArray): RecyclerView.Adapter<Holder>() {

    private val adapterContext : Context = context



    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.layout_product_list, parent, false))
    }

    override fun getItemCount(): Int {
        return dataSource.length()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.holder()
        holder.productName.text = dataSource.getJSONObject(position).getString("name").toString()

        Glide.with(adapterContext)
            .load(dataSource.getJSONObject(position).getString("image").toString())
            .into(holder.productImage)
    }

}

class Holder(view : View) : RecyclerView.ViewHolder(view) {
    private val views = view
    lateinit var productListlayout : LinearLayout
    lateinit var productName: TextView
    lateinit var productImage: ImageView

    fun holder(){
        productListlayout = views.findViewById<View>(R.id.list_product_main) as LinearLayout
        productName = views.findViewById<View>(R.id.product_name) as TextView
        productImage = views.findViewById<View>(R.id.product_image) as ImageView
    }

}