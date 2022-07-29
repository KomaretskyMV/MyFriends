package com.kmv.myfriends

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kmv.myfriends.authorization.AuthorizationFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, AuthorizationFragment.newInstance())
                .commitNow()
        }
    }
}