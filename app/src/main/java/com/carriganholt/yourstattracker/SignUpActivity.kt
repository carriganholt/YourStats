package com.carriganholt.yourstattracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        this.title = "Sign Up"

        val enterEmail = findViewById<EditText>(R.id.enterNewEmail)
        val enterPassword = findViewById<EditText>(R.id.enterNewPassword)
        val enterPassword2 = findViewById<EditText>(R.id.enterNewPassword2)
        val submit = findViewById<Button>(R.id.buttonSubmit)

        submit.setOnClickListener {
            val emailMatch = android.util.Patterns.EMAIL_ADDRESS.matcher(enterEmail.text).matches()
            val passMatch = enterPassword.text.toString() == enterPassword2.text.toString()
            val passVal = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,32}$")

            if(emailMatch && passMatch && enterPassword.text.toString().matches(passVal)) {
                auth.createUserWithEmailAndPassword(enterEmail.text.toString(), enterPassword.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {

                        val db = FirebaseFirestore.getInstance()

                        val stat: MutableMap<String, Any> = HashMap()
                        stat["games"] = 0.0
                        stat["shots"] = 0.0
                        stat["shotsMade"] = 0.0
                        stat["freeThrows"] = 0.0
                        stat["freeThrowsMade"] = 0.0
                        stat["threePoints"] = 0.0
                        stat["threePointsMade"] = 0.0
                        stat["points"] = 0.0
                        stat["rebounds"] = 0.0
                        stat["assists"] = 0.0
                        stat["steals"] = 0.0
                        stat["blocks"] = 0.0
                        stat["id"] = auth.currentUser!!.uid
                        db.collection("stats").document(auth.currentUser!!.uid).set(stat)

                        startActivity(Intent(this, MainActivity::class.java))
                    }
                }

            } else if(!emailMatch) {
                Toast.makeText(this, "Invalid email!", Toast.LENGTH_SHORT).show()

            } else if(!passMatch) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(this, "Password must have an upper case, lower case, and a number!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}