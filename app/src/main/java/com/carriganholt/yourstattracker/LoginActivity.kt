package com.carriganholt.yourstattracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        val user = auth.currentUser

        if(user != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            setContentView(R.layout.activity_login)

            this.title = "Log In"

            val enterEmail = findViewById<EditText>(R.id.enterEmail)
            val enterPassword = findViewById<EditText>(R.id.enterPassword)
            val logIn = findViewById<Button>(R.id.buttonLogIn)
            val signUp = findViewById<Button>(R.id.buttonSignUp)

            logIn.setOnClickListener {
                if(enterEmail.text.toString() != "" && enterPassword.text.toString() != "") {
                    auth.signInWithEmailAndPassword(enterEmail.text.toString(), enterPassword.text.toString()).addOnCompleteListener {
                        if(it.isSuccessful) {
                            startActivity(Intent(this, MainActivity::class.java).putExtra("ID", enterEmail.text.toString()))
                        } else {
                            Toast.makeText(this, "Invalid email/password!", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else if(enterEmail.text.toString() == "") {
                    Toast.makeText(this, "Enter your email!", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this, "Enter your password!", Toast.LENGTH_SHORT).show()
                }
            }

            signUp.setOnClickListener {
                val intent = Intent(this, SignUpActivity::class.java)
                startActivity(intent)
            }
        }
    }
}