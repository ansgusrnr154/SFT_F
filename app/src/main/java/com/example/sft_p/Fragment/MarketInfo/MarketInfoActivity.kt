package com.example.sft_p.Fragment.MarketInfo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Toast
import com.example.sft_p.Fragment.ListFragment.ContentDTO
import com.example.sft_p.R
import com.example.sft_p.Utils.FirebaseUtils
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_market_info.*

class MarketInfoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_info)

//        lecture_text.text = intent.getStringExtra("title")

//        lecture_text.text = data!!.title



        //찜여부 확인

        FirebaseUtils.db
            .collection("zzim")
            .document(FirebaseUtils.getUid())
            .get()
            .addOnSuccessListener { documentSnapshot ->

                if (documentSnapshot.get(intent.getStringExtra("title").toString()) == true){
                header_zzim.text = "찜한 식당입니다!"
                header_zzim.setTextColor(Color.BLUE)
                }
            }

       FirebaseUtils.getUid()
        FirebaseUtils.db


        zzim.setOnClickListener{

            //이미 찜이 되어있을때
            if (header_zzim.text.equals("찜한 식당입니다!")){

                header_zzim.text = "찜한 식당입니다!"
                header_zzim.setTextColor(Color.RED)

                FirebaseUtils.db
                    .collection("zzim")
                    .document(FirebaseUtils.getUid())
                    .update(intent.getStringExtra("title").toString(), "")
                    .addOnSuccessListener {

                    }
                    .addOnFailureListener {

                    }
            }else{
                header_zzim.text = "찜한 식당입니다!"
                header_zzim.setTextColor(Color.BLUE)
            }

            //찜이 되어있지 않을때

            FirebaseUtils.db
                .collection("zzim")
                .document(FirebaseUtils.getUid())
                .update(intent.getStringExtra("title").toString(), true)
                .addOnSuccessListener {
                    Toast.makeText(this,"찜하였습니다!",Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this,"다시시도해주세요.",Toast.LENGTH_LONG).show()
                }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_area, ContentFragment())
            .commit()

        figure_1.setOnClickListener {

            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25F)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_area, ContentFragment())
                .commit()
        }

        figure_2.setOnClickListener {

            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25F)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_area, InfoFragment())
                .commit()
        }

        figure_3.setOnClickListener {

            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15F)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25F)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_area, ReviewFragment())
                .commit()

        }

    }
}