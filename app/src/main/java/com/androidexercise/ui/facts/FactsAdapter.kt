package com.androidexercise.ui.facts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.androidexercise.R
import com.androidexercise.model.Rows
import com.bumptech.glide.Glide
import java.util.*

class FactsAdapter(private val mContext: Context, rows: ArrayList<Rows>) :
    ArrayAdapter<Rows>(mContext, 0, rows) {
    private var mRows = ArrayList<Rows>()
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var listItem = convertView
        if (listItem == null) listItem =
            LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false)
        val (title, description, imageHref) = mRows[position]
        val titleTV =
            listItem!!.findViewById<View>(R.id.titleTextView) as TextView
        val descriptionTV =
            listItem.findViewById<View>(R.id.descriptionTextView) as TextView
        val referenceIV =
            listItem.findViewById<View>(R.id.referenceImageView) as ImageView
        titleTV.text = title
        descriptionTV.text = description
        Glide.with(mContext).load(imageHref).into(referenceIV)
        return listItem
    }

    init {
        mRows = rows
    }
}