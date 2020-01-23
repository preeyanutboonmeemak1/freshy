package com.preeyanut.freshy


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 */
class ProductFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_product, container, false)

        val jsonString : String = loadJsonFromAsset("coffees.json", activity!!.baseContext).toString()
        val json = JSONObject(jsonString)
        val jsonArray = json.getJSONArray("coffees")

        recyclerView = view.findViewById(R.id.recycle_view_product)

        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity!!.baseContext)
        recyclerView.layoutManager = layoutManager

        val adapter = ProductAdapter(activity!!.baseContext,jsonArray)
        recyclerView.adapter = adapter

        recyclerView.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
//                Toast.makeText(context, "clicked on " + jsonArray.get(position), Toast.LENGTH_SHORT).show()

                var product = jsonArray.get(position).toString()
                val productDetailFragment = ProductDetailFragment().setInstance(product)
                val fm = fragmentManager
                val transaction: FragmentTransaction = fm!!.beginTransaction()
                transaction.replace(R.id.main_layout,productDetailFragment,"ProductDetailFragment")
                transaction.addToBackStack("ProductDetailFragment")
                transaction.commit()
            }
        })


        return view
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int, view: View)
    }

    fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
        this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {
            override fun onChildViewDetachedFromWindow(view: View) {
                view?.setOnClickListener(null)
            }

            override fun onChildViewAttachedToWindow(view: View) {
                view?.setOnClickListener {
                    val holder = getChildViewHolder(view)
                    onClickListener.onItemClicked(holder.adapterPosition, view)
                }
            }
        })
    }

    private fun loadJsonFromAsset(filename: String, baseContext: Context?): String? {
        val json: String?

        try {
            val inputStream = baseContext!!.assets.open(filename)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (ex: java.io.IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }






}
