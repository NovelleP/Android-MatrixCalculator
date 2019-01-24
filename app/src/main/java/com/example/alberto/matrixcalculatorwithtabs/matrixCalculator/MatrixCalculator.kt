package com.example.alberto.matrixcalculatorwithtabs.matrixCalculator

object MatrixCalculator {

    fun add(matrix1: Array<Array<Float>>, matrix2: Array<Array<Float>>, matrixResult: Array<Array<Float>>) {
        for (i: Int in 0..matrix1.size-1)
            for (j: Int in 0..matrix1[0].size-1)
                matrixResult[i][j] = matrix1[i][j] + matrix2[i][j]
    }

    fun subtract(matrix1: Array<Array<Float>>, matrix2: Array<Array<Float>>, matrixResult: Array<Array<Float>>) {
        for (i: Int in 0..matrix1.size-1)
            for (j: Int in 0..matrix1[0].size-1)
                matrixResult[i][j] = matrix1[i][j] - matrix2[i][j]

    }

    fun multiply(matrix1: Array<Array<Float>>, matrix2: Array<Array<Float>>, matrixResult: Array<Array<Float>>) {
        var result: Float
        for(i: Int in 0..matrix2[0].size-1) {
            for (j: Int in 0..matrix1.size-1) {
                result = 0f
                for (k: Int in 0..matrix1[0].size-1)
                    result += matrix1[j][k] * matrix2[k][i]
                matrixResult[j][i] = result
            }
        }
    }

    fun determinant(matrix1: Array<Array<Float>>): Float {
        var gaussElimination = GaussElimination()
        return gaussElimination.determinantByGauss(matrix1)
    }

}