package com.example.sft_p.Fragment.Auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.sft_p.MainActivity
import com.example.sft_p.R
import com.example.sft_p.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(R.layout.activity_register)

        val data: Array<String> = resources.getStringArray(R.array.my_array)
        var selected = data[0]
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, data)
        auth = FirebaseAuth.getInstance()

        spinner.adapter = myAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                //아이템이 클릭 되면 맨 위부터 position 0번부터 순서대로 동작하게 됩니다.
                when (position) {
                    0 -> {
                    }

                    1 -> {
                        selected = data[1]
                    }

                    2 -> {
                        selected = data[2]
                    }

                    3 -> {
                        selected = data[3]
                    }

                    4 -> {
                        selected = data[4]
                    }

                    5 -> {
                        selected = data[5]
                    }

                    6 -> {
                        selected = data[6]

                    }

                    7 -> {
                        selected = data[7]
                    }

                    else -> {

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }

        button.setOnClickListener {
            val regist = hashMapOf(     // regist 이름의 hashMap 생성
                "title" to editText.text.toString(), // " " 안에 이름을 필드로 생성, 기입 데이터는 해당 칸의 id 값으로 전달
                "menu" to editText2.text.toString(),
                "locate" to editText3.text.toString()
            )
            db.collection( selected )   // 파이어스토어 내에 스피너 item값을 가져와 컬렉션을 생성하거나 경로로 설정
                .document(editText.text.toString())        // 컬렉션 하위에 문서 생성, 우리는 이 생성하고자 하는 문서 값으로 스피너 값을 선택. 이 안을 스피너 값으로 작성해야함.
                .set(regist)
                .addOnSuccessListener {
                    Log.e("RegisterActivity", "성공")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener {
                    Log.e("RegisterActivity", "실패")
                }
        }
    }
}