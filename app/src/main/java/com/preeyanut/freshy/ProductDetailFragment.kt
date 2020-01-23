package com.preeyanut.freshy


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.json.JSONObject

/**
 * A simple [Fragment] subclass.
 */
class ProductDetailFragment : Fragment() {

    private var name:String? = null
    private var image:String? = null
    private var description:String? = null

    fun setInstance(jsonString: String): ProductDetailFragment{
        var json = JSONObject(jsonString)
        val productDetailFragment = ProductDetailFragment()
        val bundle = Bundle()
        bundle.putString("name",json.get("name").toString())
        bundle.putString("image",json.get("image").toString())
        bundle.putString("description",json.get("description").toString())
        productDetailFragment.arguments = bundle
        return productDetailFragment

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_product_detail, container, false)
        val name: TextView = view.findViewById(R.id.name_detail)
        val description: TextView = view.findViewById(R.id.description_detail)
        val imageUrl: ImageView = view.findViewById(R.id.image_detail)
        name.text = this.name
        description.text = this.description
        Glide.with(activity!!.baseContext).load(image).into(imageUrl)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if(bundle != null){
            name = bundle.getString("name").toString()
            image = bundle.getString("image").toString()
            description = bundle.getString("description").toString()
        }

    }


}
