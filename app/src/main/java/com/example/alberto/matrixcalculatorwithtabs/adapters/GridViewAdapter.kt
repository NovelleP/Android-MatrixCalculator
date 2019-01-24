package com.example.alberto.matrixcalculatorwithtabs.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.alberto.matrixcalculatorwithtabs.R

class GridViewAdapter constructor(private val context: Context, private var matrix: Array<Array<Float>>) : BaseAdapter() {

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflator.inflate(R.layout.grid_view_item, null)

        return view
    }

    override fun getItem(p0: Int): Any = matrix[p0%matrix.size][p0-(p0-p0%matrix.size)]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getCount(): Int = matrix.size * matrix[0].size
}