package com.example.sft_p.Fragment.Auth

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.sft_p.MainActivity
import com.example.sft_p.R
import com.example.sft_p.Zzim.ZzimActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.bottom.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        login_button.setOnClickListener {

            auth.signInWithEmailAndPassword(email_area.text.toString(), password_area.text.toString())
                .addOnCompleteListener(this){task ->

                    if(task.isSuccessful){

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    }else {

                        Toast.makeText(this, "fail", Toast.LENGTH_LONG).show()

                    }

                }
        }
        join_button.setOnClickListener {

            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)

        }



        home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }





        my_page.setOnClickListener {


            if(auth.currentUser == null){

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

            } else {

                val intent = Intent(this, MypageActivity::class.java)
                startActivity(intent)

            }


        }
        register_icon.setOnClickListener{

            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                startActivity(Intent(this,RegisterActivity::class.java))
            }
        }
    }
}