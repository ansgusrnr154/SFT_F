package com.example.sft_p.Fragment.Auth

import android.app.Activity
import android.content.Intent
import android.net.Uri
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
import com.example.sft_p.Fragment.ListFragment.ContentDTO
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.addphoto_btn_upload
import kotlinx.android.synthetic.main.activity_zzim.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class RegisterActivity : AppCompatActivity() {
    var PICK_IMAGE_FROM_ALBUM = 0
    var storage : FirebaseStorage? = null
    var photoUri : Uri? = null
    var auth : FirebaseAuth? = null
    var firestore : FirebaseFirestore? = null



    val array_list = ArrayList<String>()

    private lateinit var binding: ActivityRegisterBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(R.layout.activity_register)
        val data: Array<String> = resources.getStringArray(R.array.my_array)
        var selected = data[0]
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, data)

        //Initiate
        storage = FirebaseStorage.getInstance()
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        //Open the album
        var photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent,PICK_IMAGE_FROM_ALBUM)

        //add image upload event
        addphoto_btn_upload.setOnClickListener {
            var timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            var imageFileName = "IMAGE_" + timestamp + "_.png"

            var storageRef = storage?.reference?.child("images")?.child(imageFileName)

            //FileUpload
            storageRef?.putFile(photoUri!!)?.continueWithTask {task: Task<UploadTask.TaskSnapshot> ->
                return@continueWithTask storageRef.downloadUrl
            }?.addOnSuccessListener { uri ->
                var contentDTO = ContentDTO()

                //Insert downloadUrl of image
                contentDTO.imageUrl = uri.toString()

                //Insert uid of user
                contentDTO.uid = auth?.currentUser?.uid

                //Insert userId
                contentDTO.userId = auth?.currentUser?.email

                //Insert explain of content
                contentDTO.explain = addphoto_edit_explain.text.toString()

                //Insert timestamp
                contentDTO.timestamp = System.currentTimeMillis()

                //?????? ??????
                contentDTO.title = editText1.text.toString()

                //?????? ??????
                contentDTO.menu = editText2.text.toString()

                //?????? ??????
                contentDTO.locate = editText3.text.toString()

                firestore?.collection(selected)?.document()?.set(contentDTO)

                setResult(Activity.RESULT_OK)

                finish()
            }
        }

        spinner.adapter = myAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {

                //???????????? ?????? ?????? ??? ????????? position 0????????? ???????????? ???????????? ?????????.
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
//        button.setOnClickListener {
//            val regist = hashMapOf(     // regist ????????? hashMap ??????
//                "title" to editText1.text.toString(), // " " ?????? ????????? ????????? ??????, ?????? ???????????? ?????? ?????? id ????????? ??????
//                "menu" to editText2.text.toString(),
//                "locate" to editText3.text.toString()
//            )
//            db.collection( selected )   // ?????????????????? ?????? ????????? item?????? ????????? ???????????? ??????????????? ????????? ??????
//                .document(editText1.text.toString())        // ????????? ????????? ?????? ??????, ????????? ??? ??????????????? ?????? ?????? ????????? ????????? ?????? ??????. ??? ?????? ????????? ????????? ???????????????.
//                .set(regist)
//                .addOnSuccessListener {
//                    Log.e("RegisterActivity", "??????")
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                }
//                .addOnFailureListener {
//                    Log.e("RegisterActivity", "??????")
//                }
//        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == PICK_IMAGE_FROM_ALBUM){
            if(resultCode == Activity.RESULT_OK){
                //This is path to the selected image
                photoUri = data?.data
                addphoto_image.setImageURI(photoUri)

            }else{
                //Exit the addPhotoActivity if you leave the album without selecting it
                finish()

            }
        }
    }


//    fun contentUpload(){
//
//        //Make filename
//        var timestamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
//        var imageFileName = "IMAGE_" + timestamp + "_.png"
//
//        var storageRef = storage?.reference?.child("images")?.child(imageFileName)
//
//        //FileUpload
//        storageRef?.putFile(photoUri!!)?.continueWithTask {task: Task<UploadTask.TaskSnapshot> ->
//            return@continueWithTask storageRef.downloadUrl
//        }?.addOnSuccessListener { uri ->
//            var contentDTO = ContentDTO()
//
//            //Insert downloadUrl of image
//            contentDTO.imageUrl = uri.toString()
//
//            //Insert uid of user
//            contentDTO.uid = auth?.currentUser?.uid
//
//            //Insert userId
//            contentDTO.userId = auth?.currentUser?.email
//
//            //Insert explain of content
//            contentDTO.explain = addphoto_edit_explain.text.toString()
//
//            //Insert timestamp
//            contentDTO.timestamp = System.currentTimeMillis()
//
//            firestore?.collection(selected)?.document()?.set(contentDTO)
//
//            setResult(Activity.RESULT_OK)
//
//            finish()
//        }
//    }
}