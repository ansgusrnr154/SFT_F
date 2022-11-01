package com.example.sft_p.Fragment.ListFragment

import android.content.Intent
import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sft_p.Fragment.MarketInfo.MarketInfoActivity
import com.example.sft_p.R
import com.example.sft_p.Utils.FirebaseUtils
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_first.view.*

class FirstFragment : Fragment() {

    private val db = FirebaseFirestore.getInstance()

    private val title_array = ArrayList<String>()
    private val menu_array = ArrayList<String>()
    private val locate_array = ArrayList<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val  view : View = inflater.inflate(R.layout.fragment_first, container, false)


        //adapter연결

        val first_adapter = FirstFragAdapter(requireContext(), title_array, menu_array, locate_array)
        view.listview_first_fragment.adapter = first_adapter

        //db연결

        db.collection("한식")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    title_array.add(document.get("title") as String)
                    menu_array.add(document.get("menu") as String)
                    locate_array.add(document.get("locate") as String)
                }
                first_adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->

            }









//        view.listview_first_fragment.setOnItemClickListener { adapterView, view, i, l ->
//
//            val intent = Intent(requireContext(), MarketInfoActivity::class.java)
//            intent.putExtra("title", title_array.get(i).title)
//            startActivity(intent)
//        }

        return view
    }
}