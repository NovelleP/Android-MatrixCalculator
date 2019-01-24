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

class MatrixBTab : Fragment(){

    companion object MatrixB {
        var matrix: Array<Array<Float>> = Array(2, {Array(2, {0f})})
            get() = field.clone()
            set(m: Array<Array<Float>>) {
                field = m.clone()
            }
        lateinit var gvM2: GridView
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.matrix_b, container, false)

        setUpGUI(container!!, view)


        return view
    }

    private fun setUpGUI(container: ViewGroup, view: View) {
        val spinnerSize = arrayOf(2,3,4,5)

        val spinner2Rows = view.findViewById(R.id.spinner2Rows) as Spinner
        spinner2Rows.adapter = ArrayAdapter(container!!.context, android.R.layout.simple_spinner_item, spinnerSize)
        val spinner2Columns = view.findViewById(R.id.spinner2Columns) as Spinner
        spinner2Columns.adapter = ArrayAdapter(container.context, android.R.layout.simple_spinner_item, spinnerSize)

        var rows = spinner2Rows.selectedItem.toString().toInt()
        var columns = spinner2Columns.selectedItem.toString().toInt()

        matrix = Array(rows, {Array(columns, {0f})})
        var adapter = GridViewAdapter(container.context, matrix)

        gvM2 = view.findViewById(R.id.gvMatrix2) as GridView
        gvM2.numColumns = columns
        gvM2.adapter = adapter

        val buttonResize = view.findViewById(R.id.btResize2) as Button
        buttonResize.setOnClickListener{
            var rows = spinner2Rows.selectedItem.toString().toInt()
            var columns = spinner2Columns.selectedItem.toString().toInt()
            gvM2.numColumns = columns
            matrix = Array(rows, { Array(columns, {0f}) })
            adapter = GridViewAdapter(container.context, matrix)
            gvM2.adapter = adapter
        }

        val buttonReset = view.findViewById(R.id.btReset2) as Button
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