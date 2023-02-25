package com.example.bototravel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView

public class  CustomAdapter_dstour (var context : Context , var mangtour : List<data>): BaseAdapter(

) {

     class ViewHolder(row: View){
        var tvDiemdi : TextView
        var tvDiemden : TextView
        var tvNgaydi : TextView
        var tvNgayve : TextView
        var tvGia : TextView
        var btnXem : Button
        init {
            tvDiemden = row.findViewById(R.id.tvden)
            tvDiemdi = row.findViewById(R.id.tvdi)
            tvNgaydi = row.findViewById(R.id.tvngdi)
            tvNgayve = row.findViewById(R.id.tvngve)
            tvGia = row.findViewById(R.id.tvgia)
            btnXem = row.findViewById(R.id.btnxem)
        }
    }
    override fun getCount(): Int {
        return mangtour.size
    }

    override fun getItem(p0: Int): Any {
        return mangtour.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertview: View?, p2: ViewGroup?): View {
        var view : View?
        var viewholder : ViewHolder
        if (convertview == null){
            var layoutinflater : LayoutInflater = LayoutInflater.from(context)
            view =layoutinflater.inflate(R.layout.dong_danhsachtour,null)
            viewholder = ViewHolder(view)
            view.tag =viewholder

        }
        else{
            view = convertview
            viewholder = convertview.tag as ViewHolder

        }
        var data :data = getItem(position) as data
        viewholder.tvDiemdi.text = data.Diemdi
        viewholder.tvDiemden.text = data.Diemden
        viewholder.tvNgaydi.text = data.Ngaydi
        viewholder.tvNgayve.text = data.Ngayve
        viewholder.tvGia.text = data.Gia.toString()

        return view as View
    }
}