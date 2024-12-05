package com.example.timecapsuleApp.Users.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timecapsuleApp.R
import com.example.timecapsuleApp.Users.models.User
import com.example.timecapsuleApp.Model.UserAdapter
import com.example.timecapsuleApp.Users.Authorization.AuthActivity
import com.example.timecapsuleApp.API.security.SharedPreferencesUtil
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter

    private var userList = mutableListOf<User>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)



        recyclerView = findViewById(R.id.userRecyclerList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(userList)

        recyclerView.adapter = userAdapter





        val bottomNavigatonView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigatonView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {

                R.id.nav_home -> {
                    true
                }


                R.id.nav_logout -> {
                    val intent = Intent(this, AuthActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    SharedPreferencesUtil.cleanPreferences(this)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> {
                    false
                }
            }
        }

    }
}