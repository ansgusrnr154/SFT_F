package com.example.sft_p.Fragment.ListFragment

import android.content.Intent
import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.example.sft_p.Fragment.MarketInfo.MarketInfoActivity
import com.example.sft_p.R
import com.example.sft_p.Utils.FirebaseUtils
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_first.view.*
import kotlinx.android.synthetic.main.listview_item.view.*

class FirstFragment : Fragment() {
    var firestore : FirebaseFirestore? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       var view = LayoutInflater.from(activity).inflate(R.layout.fragment_first,container,false)
        firestore = FirebaseFirestore.getInstance()

        view.detail_recyclerView.adapter = DetailViewRecyclerViewAdapter()
        view.detail_recyclerView.layoutManager = LinearLayoutManager(activity)
        return view
    }
    inner class DetailViewRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
        var contentDTOs : ArrayList<ContentDTO> = arrayListOf()
        var contentUidList : ArrayList<String> = arrayListOf()
        init {

            firestore?.collection("한식")?.orderBy("timestamp")?.addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                contentDTOs.clear()
                contentUidList.clear()
                //Sometimes, This code return null of querySnapshot when it signout
                if(querySnapshot == null) return@addSnapshotListener

                for(snapshot in querySnapshot!!.documents){
                    var item = snapshot.toObject(ContentDTO::class.java)
                    contentDTOs.add(item!!)
                    contentUidList.add(snapshot.id)
                }
                notifyDataSetChanged()
            }

        }

        override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
           var view = LayoutInflater.from(p0.context).inflate(R.layout.listview_item,p0,false)
            return CustomViewHolder(view)
        }

        inner class CustomViewHolder(view: View): RecyclerView.ViewHolder(view)

        override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
            var viewholder = (p0 as CustomViewHolder).itemView

            viewholder. detailviewitem_profile_textview.text = contentDTOs!![p1].userId

            Glide.with(p0.itemView.context).load(contentDTOs!![p1].imageUrl).into(viewholder.detailviewitem_imageview_content)

            //Explain of content
            viewholder.detailviewitem_explain_textview.text = contentDTOs!![p1].explain

            //likes
            viewholder.detailviewitem_favoritecounter_textview.text = "Likes" + contentDTOs!![p1].favoritesCount

            //ProfileImage
            Glide.with(p0.itemView.context).load(contentDTOs!![p1].imageUrl).into(viewholder.detailviewitem_profile_image)

        }

        override fun getItemCount(): Int {
            return contentDTOs.size
        }

    }

}

//        view.listview_first_fragment.setOnItemClickListener { adapterView, view, i, l ->
//
//            val intent = Intent(requireContext(), MarketInfoActivity::class.java)
//            intent.putExtra("title", title_array.get(i).title)
//            startActivity(intent)
//        }
