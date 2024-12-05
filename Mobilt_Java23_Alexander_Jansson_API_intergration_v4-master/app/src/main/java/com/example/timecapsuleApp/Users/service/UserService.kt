package com.example.timecapsuleApp.Users.service


import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.timecapsuleApp.Transactions.activity.MainActivity
import com.example.timecapsuleApp.Users.Authorization.AuthActivity
import com.example.timecapsuleApp.Users.models.User
import com.example.timecapsuleApp.Users.repository.UserRepository

class UserService(private val userRepository: UserRepository) {





        suspend fun registerUser(email: String, password: String, context: Context){

            val createUser = User(
                email = email,
                password = password
            )

            try{
                val response = userRepository.registerUser(createUser)
                if (response.isSuccessful){
                    Log.d("MainActivity", "User created successfully")
                    Toast.makeText(context, "Registrering lyckades!", Toast.LENGTH_SHORT).show()
                    (context as? AuthActivity)?.switchToLogin()
                } else {
                    Log.e("MainActivity", "Error: ${response}")
                    Toast.makeText(context, "Registrering misslyckades!", Toast.LENGTH_SHORT).show()
                }
            }catch (e:Exception){
                Log.e("AuthActivity", "Exception: ${e.message}")
                Toast.makeText(context, "Något gick fel: ${e.message}", Toast.LENGTH_SHORT).show()
            }
       }



    // authenticate with JWT tokens
    suspend fun loginUser(email: String, password: String, context: Context) {

        val user = User(email = email, password = password)

        try {
            val response = userRepository.authenticateUser(user)
            if (response.isSuccessful) {

                val token = response.body()?.token
                if (token != null) {
                    Log.d("LoginFragment", "JWT Token: $token")
                    Toast.makeText(context, "JWT Token: $token", Toast.LENGTH_LONG).show()

                    val masterKey = MasterKey.Builder(context)
                        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                        .build()

                    val sharedPreferences = EncryptedSharedPreferences.create(
                        context,
                        "MyPrefs",
                        masterKey,
                        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                    )

                    sharedPreferences.edit().putString("token", token).apply()
                   val intent = Intent(context, MainActivity::class.java)
                    // tar bort auth ur stack så man inte kan gå tbx utan att logga ut
                    // ( viktigt för att kunna rensa shared prefs osv ordentligt..)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                } else {
                    Log.d("NULL TOKEN", "JWT Token: $token")
                    Toast.makeText(context, "JWT Token: $token", Toast.LENGTH_LONG).show()
                }
            }
        } catch (e: Exception){
           Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()

        }
    }

}
