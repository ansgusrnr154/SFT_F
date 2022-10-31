package com.example.sft_p.Fragment.ListFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sft_p.Fragment.MarketInfo.MarketInfoActivity
import com.example.sft_p.R
import com.example.sft_p.Utils.FirebaseUtils
import kotlinx.android.synthetic.main.fragment_first.view.*

class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val  view : View = inflater.inflate(R.layout.fragment_first, container, false)

        val list_array = arrayListOf<ContentsListModel>(

            ContentsListModel(R.drawable.korea,"놀부부대찌개 용산 리첸시아점",1,"d"),
            ContentsListModel(R.drawable.korea2,"한양원 여의도본점",1,"d"),
            ContentsListModel(R.drawable.korea3,"다독이네 숯불구이",1,"d"),
            ContentsListModel(R.drawable.korea4,"두루찌개3대",1,"d"),
            ContentsListModel(R.drawable.korea5,"배할머니네밥집 노량진점",1,"d"),
            ContentsListModel(R.drawable.korea6,"삼거리빈대떡",1,"d"),
            ContentsListModel(R.drawable.test6,"test7",1,"d"),
            ContentsListModel(R.drawable.test7,"test8",1,"d"),
            ContentsListModel(R.drawable.test8,"test9",1,"d")
        )

        val list_adapter = FirstFragAdapter(requireContext(), list_array)
        view.listview_first_fragment.adapter = list_adapter





        FirebaseUtils.db
            .collection("zzim")
            .document(FirebaseUtils.getUid())
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists() == true){
                    //Data 필드가 있을 때

                }else{
                    //Data 필드가 없을 때

                    val lecture = hashMapOf(
                        "안산닭한마리" to "",
                        "test2" to "",
                        "test3" to "",
                        "test4" to "",
                        "test5" to "",
                        "test6" to "",
                        "test7" to "",
                        "test8" to "",
                        "test9" to ""
                    )

                    FirebaseUtils.db
                        .collection("zzim")
                        .document(FirebaseUtils.getUid())
                        .set(lecture)
                        .addOnSuccessListener {  }
                        .addOnFailureListener {  }

                }
            }
            .addOnFailureListener {  }


        view.listview_first_fragment.setOnItemClickListener { adapterView, view, i, l ->

            val intent = Intent(requireContext(), MarketInfoActivity::class.java)
            intent.putExtra("title", list_array.get(i).title)
            startActivity(intent)
        }

        return view
    }
}