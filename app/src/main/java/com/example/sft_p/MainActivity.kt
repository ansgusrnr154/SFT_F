package com.example.sft_p

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.sft_p.Fragment.Auth.LoginActivity
import com.example.sft_p.Fragment.Auth.MypageActivity
import com.example.sft_p.Fragment.Auth.RegisterActivity
import com.example.sft_p.Zzim.ZzimActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.gridview
import kotlinx.android.synthetic.main.bottom.*

class MainActivity : AppCompatActivity() {

    internal lateinit var viewpager : ViewPager

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)

        auth = FirebaseAuth.getInstance()


        val img = arrayOf(
            R.drawable.korean,
            R.drawable.china,
            R.drawable.japan,
            R.drawable.dessert,
            R.drawable.yankee,
            R.drawable.fried,
            R.drawable.bun,
            R.drawable.pizza

        )

        val text = arrayOf(
            "한식",
            "중식",
            "일식",
            "디저트",
            "양식",
            "치킨",
            "분식",
            "피자"

        )

        val gridviewAdapter = GridviewAdapter(this, img, text)
        gridview.adapter = gridviewAdapter

        gridview.setOnItemClickListener { adapterView, view, i, l ->

            val intent = Intent(this, LectureActivity::class.java)
            startActivity(intent)

        }

        viewpager = findViewById(R.id.viewpager) as ViewPager

        val adapter = ViewPagerAdapter(this)
        viewpager.adapter = adapter


        home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



        zzim_icon.setOnClickListener {

            if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                startActivity(Intent(this,ZzimActivity::class.java))
            }

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

            if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                startActivity(Intent(this,RegisterActivity::class.java))
            }
        }


    }
}