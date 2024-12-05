package com.example.timecapsuleApp.Users.Authorization

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.timecapsuleApp.R
import com.example.timecapsuleApp.API.ApiClient
import com.example.timecapsuleApp.Users.service.UserService
import com.example.timecapsuleApp.Users.repository.UserRepository
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var authService: UserService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //menu for login -> register activities
        val loginLayout = view.findViewById<android.widget.LinearLayout>(R.id.loginMenuLayout)
        val loginMenuItem = view.findViewById<android.widget.TextView>(R.id.loginMenuItem)
        val registerMenuItem = view.findViewById<android.widget.TextView>(R.id.registerMenuItem)

        //login form
        val emailEditText = view.findViewById<EditText>(R.id.loginEmailEditText)
        val passwordEditText = view.findViewById<EditText>(R.id.loginPasswordEditText)
        val loginButton = view.findViewById<android.widget.Button>(R.id.loginButton)
        // authentication for login
        authService = UserService(UserRepository(ApiClient.transactionApi))



        // go to register activity
        registerMenuItem.setOnClickListener {

            val rotationAnimation = ObjectAnimator.ofFloat(loginLayout,  "rotationY", 0f, 180f)
            rotationAnimation.duration = 500
            rotationAnimation.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    (activity as? AuthActivity)?.switchToRegistration()
                }
            })
            rotationAnimation.start()
        }

        // log inp
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            lifecycleScope.launch {
                authService.loginUser(email, password,requireContext())
            }
        }
    }


}



