package com.example.afinal.finalapplication.helper

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.afinal.finalapplication.R
import com.example.afinal.finalapplication.models.Contact
import com.example.afinal.finalapplication.ui.description.DescriptionActivity
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter(private val list: ArrayList<Contact>, private val context: Context): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.name.text = list.get(position).name
        holder.itemView.groupName.text = list.get(position).groupId.toString()
        holder.itemView.setOnClickListener {
            Logger.msg("position " + position)
            val intent = Intent(context, DescriptionActivity::class.java)
            intent.putExtra("position", position)
            context.startActivity(intent)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }
}