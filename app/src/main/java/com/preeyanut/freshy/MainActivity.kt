package com.preeyanut.freshy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginFragment = LoginFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.main_layout,loginFragment,"LoginFragment")
        transaction.addToBackStack("LoginFragment")
        transaction.commit()
    }

    override fun onBackPressed() {
        val manager = supportFragmentManager.findFragmentById(R.id.main_layout)
        if (manager is LoginFragment ) {
            finish()
        }else{
            super.onBackPressed();
        }

    }

}
