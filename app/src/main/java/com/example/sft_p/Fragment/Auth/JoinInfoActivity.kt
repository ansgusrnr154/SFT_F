package com.example.sft_p.Fragment.Auth

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.sft_p.MainActivity
import com.example.sft_p.R
import com.example.sft_p.Zzim.ZzimActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_join_info.*
import kotlinx.android.synthetic.main.bottom.*

class JoinInfoActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_info)

        auth = FirebaseAuth.getInstance()

        join_info_login_button.setOnClickListener {
            val user = hashMapOf(
                "nickname" to join_info_email_area.text.toString()
            )
            db.collection("users")
                .document(auth.currentUser?.uid.toString())
                .set(user)
                .addOnSuccessListener {

                    Log.e("JoinInfoActivity", "성공")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                }
                .addOnFailureListener { Log.e("JoinInfoActivity", "실패")}


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
}