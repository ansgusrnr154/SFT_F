package com.example.sft_p.Fragment.ListFragment

import android.content.Intent
import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.init
import com.example.sft_p.Fragment.MarketInfo.MarketInfoActivity
import com.example.sft_p.R
import com.example.sft_p.Utils.FirebaseUtils
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_first.view.*

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       var view = LayoutInflater.from(activity).inflate(R.layout.fragment_first,container,false)
        return view
    }
    inner class DetailViewRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        init {

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }

    }
}






//        view.listview_first_fragment.setOnItemClickListener { adapterView, view, i, l ->
//
//            val intent = Intent(requireContext(), MarketInfoActivity::class.java)
//            intent.putExtra("title", title_array.get(i).title)
//            startActivity(intent)
//        }
