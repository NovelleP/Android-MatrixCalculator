package com.example.alberto.matrixcalculatorwithtabs.tabs_fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.Spinner
import com.example.alberto.matrixcalculatorwithtabs.R
import com.example.alberto.matrixcalculatorwithtabs.adapters.GridViewAdapter

class MatrixATab : Fragment(){

    companion object MatrixA {
        var matrix: Array<Array<Float>> = Array(2, {Array(2, {0f})})
            get() = field.clone()
            set(m) {
                field = m.clone()
            }
        lateinit var gvM1: GridView

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.matrix_a, container, false)

        setUpGUI(container!!, view)

        return view
    }

    private fun setUpGUI(container: ViewGroup, view: View) {
        val spinnerSize = arrayOf(2,3,4,5)

        val spinner1Rows = view.findViewById(R.id.spinner1Rows) as Spinner
        spinner1Rows.adapter = ArrayAdapter(container!!.context, android.R.layout.simple_spinner_item, spinnerSize)
        val spinner1Columns = view.findViewById(R.id.spinner1Columns) as Spinner
        spinner1Columns.adapter = ArrayAdapter(container.context, android.R.layout.simple_spinner_item, spinnerSize)

        var rows = spinner1Rows.selectedItem.toString().toInt()
        var columns = spinner1Columns.selectedItem.toString().toInt()

        matrix = Array(rows, {Array(columns, {0f})})
        var adapter = GridViewAdapter(container.context, matrix)

        gvM1 = view.findViewById(R.id.gvMatrix1) as GridView
        gvM1.numColumns = columns
        gvM1.adapter = adapter

        val buttonResize = view.findViewById(R.id.btResize1) as Button
        buttonResize.setOnClickListener{
            var rows = spinner1Rows.selectedItem.toString().toInt()
            var columns = spinner1Columns.selectedItem.toString().toInt()
            gvM1.numColumns = columns
            matrix = Array(rows, { Array(columns, { 0f }) })
            adapter = GridViewAdapter(container.context, matrix)
            gvM1.adapter = adapter
        }

        val buttonReset = view.findViewById(R.id.btReset1) as Button
        buttonReset.setOnClickListener{
            resetMatrix(matrix)
            adapter.notifyDataSetChanged()
        }
    }

    private fun resetMatrix(matrix: Array<Array<Float>>){
        for(i in 0..matrix.size-1)
            for(j in 0..matrix[i].size-1)
                matrix[i][j] = 0f
    }


}