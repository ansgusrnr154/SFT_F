package com.example.sft_p.Fragment.Auth

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.sft_p.MainActivity
import com.example.sft_p.R
import com.example.sft_p.Zzim.ZzimActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_mypage.logout_button
import kotlinx.android.synthetic.main.activity_mypage.nickname_area
import kotlinx.android.synthetic.main.bottom.*

class MypageActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    private  lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        auth = FirebaseAuth.getInstance()

        val docRef = db.collection("users").document(auth.currentUser?.uid.toString())

        docRef.get().addOnSuccessListener { documentSnapshot ->

            nickname_area.setText(documentSnapshot.get("nickname").toString())


        }
        logout_button.setOnClickListener {

            auth.signOut()

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
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