package com.xiugechen.reading_app.Presentation

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.xiugechen.reading_app.Data.DataManager
import com.xiugechen.reading_app.Data.FileDisplay
import com.xiugechen.reading_app.Data.ReadIndicator
import com.xiugechen.reading_app.R

class FileAdapter() : RecyclerView.Adapter<FileAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val fileView = LayoutInflater.from(parent.context).inflate(R.layout.content_file_selection_row, parent, false)

        return ViewHolder(fileView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.filename.text = DataManager.mCachedFileDisplays[position].filename
        holder.fileDescription.text = DataManager.mCachedFileDisplays[position].fileDiscription

        val readIndicator = DataManager.mCachedFileDisplays[position].readIndicator

        if (readIndicator.equals(ReadIndicator.UNREAD)) {
            holder.readIndicator.text = "unread"
            holder.readIndicator.setTextColor(Color.RED)
        }
        else if (readIndicator.equals(ReadIndicator.READ)) {
            holder.readIndicator.text = "read"
            holder.readIndicator.setTextColor(Color.GREEN)
        }
        else {
            holder.readIndicator.text = ""
        }

        holder.readButton.setOnClickListener {
            DataManager.mCachedFileDisplays[position].readIndicator = ReadIndicator.READ
            Log.d("FileAdapter", "onBindViewHolder: TODO")
        }
    }

    override fun getItemCount() : Int = DataManager.mCachedFileDisplays.size

    class ViewHolder(fileView: View) : RecyclerView.ViewHolder(fileView) {
        val filename: TextView = fileView.findViewById(R.id.filename)
        val fileDescription: TextView = fileView.findViewById(R.id.fileDescription)
        val readIndicator: TextView = fileView.findViewById(R.id.readIndicator)
        val readButton: Button = fileView.findViewById(R.id.readButton)
    }
}