package com.example.alberto.matrixcalculatorwithtabs.tabs_fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.GridView
import android.widget.Toast
import com.example.alberto.matrixcalculatorwithtabs.R
import com.example.alberto.matrixcalculatorwithtabs.adapters.GridViewAdapter
import com.example.alberto.matrixcalculatorwithtabs.matrixCalculator.MatrixCalculator
import kotlinx.android.synthetic.main.matrix_result.*

class MatrixResultTab : Fragment() {
    var operation: Button? = null
    var matrix3: Array<Array<Float>> = Array(3, { Array(3, { 0f }) })
    var adapter3: GridViewAdapter? = null
    var det: Float = 0f


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.matrix_result, container, false)
        setUpGUI(container!!, view, inflater)
        return view
    }

    @SuppressLint("ResourceAsColor")
    private fun setUpGUI(container: ViewGroup, view: View, inflater: LayoutInflater) {
        var rows = MatrixBTab.matrix.size
        var columns = MatrixBTab.matrix[0].size
        matrix3 = Array(rows, { Array(columns, { 0f }) })
        adapter3 = GridViewAdapter(container.context, matrix3)
        var gvM3 = view.findViewById(R.id.gvMatrix3) as GridView
        gvM3.numColumns = columns
        gvM3.adapter = adapter3
        val buttonAdd = view.findViewById(R.id.btAdd) as Button
        val buttonSub = view.findViewById(R.id.btSub) as Button
        val buttonMul = view.findViewById(R.id.btMul) as Button
        val buttonDet = view.findViewById(R.id.btDet) as Button
        val buttonReset = view.findViewById(R.id.btReset3) as Button
        val buttonResult = view.findViewById(R.id.btResult) as Button

        buttonAdd.setOnClickListener {
            if(operation != null)
                operation!!.setBackgroundColor(resources.getColor(R.color.unselectedButton))
            operation = buttonAdd
            resizeAddSub(container)
            buttonAdd.setBackgroundColor(resources.getColor(R.color.selectedButton))
        }
        buttonSub.setOnClickListener {
            if(operation != null)
                operation!!.setBackgroundColor(resources.getColor(R.color.unselectedButton))
            operation = buttonSub
            resizeAddSub(container)
            buttonSub.setBackgroundColor(resources.getColor(R.color.selectedButton))
        }
        buttonMul.setOnClickListener {
            if(operation != null)
                operation!!.setBackgroundColor(resources.getColor(R.color.unselectedButton))
            operation = buttonMul
            resizeMul(container)
            buttonMul.setBackgroundColor(resources.getColor(R.color.selectedButton))
        }
        buttonDet.setOnClickListener {
            if(operation != null)
                operation!!.setBackgroundColor(resources.getColor(R.color.unselectedButton))
            operation = buttonDet
            buttonDet.setBackgroundColor(resources.getColor(R.color.selectedButton))
        }
        buttonReset.setOnClickListener {
            if(operation != null)
                operation!!.setBackgroundColor(resources.getColor(R.color.unselectedButton))
            resetMatrix(matrix3)
            adapter3!!.notifyDataSetChanged()
            det = 0f
            operation = null
            etDeterminant.setText("")
        }

        buttonResult.setOnClickListener {
            var matrix1: Array<Array<Float>> = MatrixATab.matrix
            var matrix2: Array<Array<Float>> = MatrixBTab.matrix
            var isMatrix1Empty = emptyEditTextMatrix1()
            var isMatrix2Empty = emptyEditTextMatrix2()

            if (!isMatrix1Empty) {
                readMatrix1()
                matrix1 = MatrixATab.matrix
            }
            if (!isMatrix2Empty) {
                readMatrix2()
                matrix2 = MatrixBTab.matrix
            }

            when (operation) {
                buttonAdd -> {
                    if (matrix1.size == matrix2.size && matrix1[0].size == matrix2[0].size)
                        MatrixCalculator.add(matrix1, matrix2, matrix3)
                    else
                        Toast.makeText(container.context, "wrong size", Toast.LENGTH_LONG).show()
                }

                buttonSub -> {
                    if (matrix1.size == matrix2.size && matrix1[0].size == matrix2[0].size)
                        MatrixCalculator.subtract(matrix1, matrix2, matrix3)
                    else
                        Toast.makeText(container.context, "wrong size", Toast.LENGTH_LONG).show()
                }

                buttonMul -> {
                    if (matrix1[0].size == matrix2.size)
                        MatrixCalculator.multiply(matrix1, matrix2, matrix3)
                    else
                        Toast.makeText(container.context, "wrong size", Toast.LENGTH_LONG).show()
                }

                buttonDet -> {
                    if(matrix1.size== matrix1[0].size )
                        det = MatrixCalculator.determinant(matrix1)
                    else
                        Toast.makeText(container.context, "MatrixA must be square", Toast.LENGTH_LONG).show()
                }

                else -> Toast.makeText(container.context, "An operation must be selected", Toast.LENGTH_LONG).show()
            }

            if (operation != buttonDet && operation != null) {
                if (!isMatrix1Empty && !isMatrix2Empty)
                    showResult()
                else
                    Toast.makeText(container.context, "MatrixA and MatrixB must be complete", Toast.LENGTH_LONG).show()
            }
            else if (operation == buttonDet) {
                if(!isMatrix1Empty)
                    setDeterminant()
                else
                    Toast.makeText(container.context, "MatrixA must be complete", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun deselectButton() {

    }

    private fun resizeAddSub(container: ViewGroup) {
        var rows = MatrixBTab.matrix.size
        var columns = MatrixBTab.matrix[0].size
        gvMatrix3.numColumns = columns
        matrix3 = Array(rows, { Array(columns, { 0f }) })
        adapter3 = GridViewAdapter(container.context, matrix3)
        gvMatrix3.adapter = adapter3
    }

    private fun resizeMul(container: ViewGroup) {
        var rows =  MatrixATab.matrix.size
        var columns = MatrixBTab.matrix[0].size
        gvMatrix3.numColumns = columns
        matrix3 = Array(rows, { Array(columns, { 0f }) })
        adapter3 = GridViewAdapter(container.context, matrix3)
        gvMatrix3.adapter = adapter3
    }

    private fun readMatrix1() {
        var matrix1 = MatrixATab.matrix
        var gvM1 = MatrixATab.gvM1


        var itemView: View
        var editText: EditText
        for (i in 0..matrix1.size - 1)
            for (j in 0..matrix1[i].size - 1) {
                itemView = gvM1.getChildAt(i * matrix1[i].size + j)
                editText = itemView.findViewById(R.id.etItem) as EditText
                matrix1[i][j] = editText.text.toString().toFloat()
            }
        MatrixATab.matrix = matrix1.clone()
    }

    private fun readMatrix2() {
        var matrix2 = MatrixBTab.matrix
        var gvM2 = MatrixBTab.gvM2

        var itemView: View
        var editText: EditText
        for (i in 0..matrix2.size - 1)
            for (j in 0..matrix2[i].size - 1) {
                itemView = gvM2.getChildAt(i * matrix2[i].size + j)
                editText = itemView.findViewById(R.id.etItem) as EditText
                matrix2[i][j] = editText.text.toString().toFloat()
            }
        MatrixBTab.matrix = matrix2.clone()
    }

    private fun emptyEditTextMatrix1(): Boolean {
        var matrix1 = MatrixATab.matrix
        var gvM1 = MatrixATab.gvM1

        var itemView: View
        var editText: EditText

        for (i in 0..matrix1.size - 1)
            for (j in 0..matrix1[i].size - 1) {
                itemView = gvM1.getChildAt(i * matrix1[i].size + j)
                editText = itemView.findViewById(R.id.etItem) as EditText
                if (editText.text.toString() == "")
                    return true
            }
        return false
    }

    private fun emptyEditTextMatrix2(): Boolean {
        var matrix2 = MatrixBTab.matrix
        var gvM2 = MatrixBTab.gvM2

        var itemView: View
        var editText: EditText
        for (i in 0..matrix2.size - 1)
            for (j in 0..matrix2[i].size - 1) {
                itemView = gvM2.getChildAt(i * matrix2[i].size + j)
                editText = itemView.findViewById(R.id.etItem) as EditText
                if (editText.text.toString() == "")
                    return true
            }
        return false
    }

    private fun showResult() {
        var itemView: View
        var editText: EditText
        for (i in 0..matrix3.size - 1)
            for (j in 0..matrix3[i].size - 1) {
                itemView = gvMatrix3.getChildAt(i * matrix3[i].size + j)
                editText = itemView.findViewById(R.id.etItem) as EditText
                editText.setText(matrix3[i][j].toString())
            }
    }

    private fun setDeterminant() {
        etDeterminant.setText(det.toString())
    }

    private fun resetMatrix(matrix: Array<Array<Float>>){
        for(i in 0..matrix.size-1)
            for(j in 0..matrix[i].size-1)
                matrix[i][j] = 0f
    }

}