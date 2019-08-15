package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView


class CustomAdapter(context: Context,arrayListDetails:ArrayList<Model>) : BaseAdapter() {

    private val layoutInflater: LayoutInflater
    private val arrayListDetails: ArrayList<Model>

    init {
        this.layoutInflater = LayoutInflater.from(context)
        this.arrayListDetails = arrayListDetails
    }

    override fun getCount(): Int {
        return arrayListDetails.size
    }

    override fun getItem(position: Int): Any {
        return arrayListDetails.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        val listRowHolder: ListRowHolder
        if (convertView == null) {
            view = this.layoutInflater.inflate(R.layout.adapter_layout, parent, false)
            listRowHolder = ListRowHolder(view)
            view.tag = listRowHolder
        } else {
            view = convertView
            listRowHolder = view.tag as ListRowHolder
        }

        listRowHolder.myCompanyName.text = arrayListDetails.get(position).companyName
        listRowHolder.myTicker.text = arrayListDetails.get(position).ticker
        listRowHolder.myChanges.text = arrayListDetails.get(position).changes
        listRowHolder.myPrice.text = arrayListDetails.get(position).price
        listRowHolder.mychangesPercentage.text = arrayListDetails.get(position).changesPercentage
    return view
    }
}

private class ListRowHolder(row: View?){
    public val myCompanyName: TextView
    public val myTicker : TextView
    public val myChanges: TextView
    public val myPrice: TextView
    public val mychangesPercentage: TextView
    public val linearLayout: LinearLayout

    init {
        this.myCompanyName = row?.findViewById<TextView>(R.id.myCompanyName) as TextView
        this.myTicker = row?.findViewById<TextView>(R.id.myTicker) as TextView
        this.myChanges = row?.findViewById<TextView>(R.id.myChanges) as TextView
        this.myPrice = row?.findViewById<TextView>(R.id.myPrice) as TextView
        this.mychangesPercentage = row?.findViewById<TextView>(R.id.mychangesPercentage) as TextView
        this.linearLayout = row?.findViewById<LinearLayout>(R.id.linearLayout) as LinearLayout
    }
}