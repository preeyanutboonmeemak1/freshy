package com.preeyanut.freshy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    private val objUser = object {
        var username = "kit"
        var password = "123"
    }

    private val obj = object {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View =  inflater.inflate(R.layout.fragment_login, container, false)

        val username: EditText? = view.findViewById(R.id.txtUsername)
        val password: EditText? = view.findViewById(R.id.txtPassword)

        val btnLogin: Button = view.findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener{
            if(this.checkLogin(username!!.text.toString(),password!!.text.toString())){
                Toast.makeText(this.context,"Login Success",Toast.LENGTH_LONG).show()

                val productFragment = ProductFragment()
                val fm = fragmentManager
                val transaction: FragmentTransaction = fm!!.beginTransaction()
                transaction.replace(R.id.main_layout,productFragment,"ProductFragment")
                transaction.addToBackStack("ProductFragment")
                transaction.commit()
            }else{
                Toast.makeText(this.context,"Login Failed",Toast.LENGTH_LONG).show()
            }

        }


        return view
    }

    private fun checkLogin(username: String,password: String): Boolean {
        if(username == objUser.username && password == objUser.password) {
            return true
        }

        return false

    }


}

