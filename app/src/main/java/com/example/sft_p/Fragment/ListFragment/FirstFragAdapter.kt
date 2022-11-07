package com.example.sft_p.Fragment.ListFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.sft_p.R
import kotlinx.android.synthetic.main.listview_item.view.*

class FirstFragAdapter(val context: Context, val title : ArrayList<String>, val menu : ArrayList<String> , val locate : ArrayList<String> ) : BaseAdapter() {

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        val view : View = LayoutInflater.from(context).inflate(R.layout.listview_item, null)

//        view.lv_textview_1.text = title.get(0)
//        view.lv_textview_2.text = menu.get(0)
//        view.lv_textview_3.text = locate.get(0)


        return view
    }

    override fun getItem(p0: Int): Any {
        return 0
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }


    override fun getCount(): Int {
       return title.size
    }

}