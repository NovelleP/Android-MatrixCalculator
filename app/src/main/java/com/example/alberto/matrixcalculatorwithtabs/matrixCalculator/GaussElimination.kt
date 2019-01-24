package com.example.alberto.matrixcalculatorwithtabs.matrixCalculator

class GaussElimination {

    private var totalCoefficient = 0f
    private var determinant = 1.0f

    fun determinantByGauss(matrix: Array<Array<Float>>): Float {
        var aux = matrix.clone()
        for(i in 0..aux.size-1){
            moveRows(aux, i)
            makeZeroBelow(aux, i)
        }
        return calculateDeterminant(aux)
    }

    private fun moveRows(matrix: Array<Array<Float>>, j: Int) {
        if(matrix[j][j] == 0f)
            for(i in j+1..matrix.size-1)
                if(matrix[i][j] != 0f) {
                    move(i, j, matrix)
                    determinant *= -1
                }
    }

    private fun move(row1: Int, row2: Int, matrix: Array<Array<Float>>) {
        var aux = matrix[row1]
        matrix[row1] = matrix[row2]
        matrix[row2] = aux
    }

    private fun makeZeroBelow(matrix: Array<Array<Float>>, j: Int) {
        for(i in j+1..matrix.size-1){
            var coefficient = matrix[i][j]/matrix[j][j]
            totalCoefficient += coefficient
            for(k in j..matrix[i].size-1)
                matrix[i][k] = matrix[i][k] - coefficient*matrix[j][k]
        }
    }

    private fun calculateDeterminant(matrix: Array<Array<Float>>): Float{
        for(i in 0..matrix.size-1)
            determinant *= matrix[i][i]
        return determinant
    }

}