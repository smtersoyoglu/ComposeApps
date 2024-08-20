package com.smtersoyoglu.calculatorapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    private val _equationText = MutableLiveData("")
    val equationText: LiveData<String> = _equationText

    private val _resultText = MutableLiveData("0")
    val resultText: LiveData<String> = _resultText

    fun onButtonClick(btn: String) {

        _equationText.value?.let {
            if (btn == "AC") {
                _equationText.value = ""
                _resultText.value = "0"
                return
            }

            if (btn == "C") {
                if (it.isNotEmpty()) {
                    _equationText.value = it.substring(0, it.length - 1)
                    return
                }
            }

            if (btn == "=") {
                try {
                    _resultText.value = evaluateExpression(it).toString()
                }catch (e: Exception) {
                    _resultText.value = "Error"
                }
                return
            }

            _equationText.value = it + btn

        }
    }

    /**         isNotBlank()
     * isNotBlank() fonksiyonu, bir String'in boş veya yalnızca boşluk karakterlerinden oluşup oluşmadığını kontrol eder.
     * Bu, isNotEmpty() fonksiyonundan farklıdır çünkü isNotEmpty() yalnızca dizenin uzunluğunun sıfırdan büyük olup olmadığını kontrol ederken,
     * isNotBlank() dizenin yalnızca boşluk karakterleri içerip içermediğini de kontrol eder.
     */
    private fun evaluateExpression(expression: String): Double {
        // Basit bir parser ve hesaplayıcı
        val tokens =
            expression.split("(?<=[-+*/()])|(?=[-+*/()])".toRegex()).filter { it.isNotBlank() }
        val values = mutableListOf<Double>()
        val ops = mutableListOf<Char>()


        for (token in tokens) {
            when {
                token.isNumber() -> values.add(token.toDouble())
                token == "(" -> ops.add('(')
                token == ")" -> {
                    while (ops.isNotEmpty() && ops.last() != '(') {
                        values.add(
                            applyOp(
                                ops.removeAt(ops.lastIndex),
                                values.removeAt(values.lastIndex - 1),
                                values.removeAt(values.lastIndex)
                            )
                        )
                    }
                    ops.removeAt(ops.lastIndex)
                }

                token.isOperator() -> {
                    while (ops.isNotEmpty() && hasPrecedence(token[0], ops.last())) {
                        values.add(
                            applyOp(
                                ops.removeAt(ops.lastIndex),
                                values.removeAt(values.lastIndex - 1),
                                values.removeAt(values.lastIndex)
                            )
                        )
                    }

                    ops.add(token[0])
                }
            }
        }

        while (ops.isNotEmpty()) {
            values.add(
                applyOp(
                    ops.removeAt(ops.lastIndex),
                    values.removeAt(values.lastIndex - 1),
                    values.removeAt(values.lastIndex)
                )
            )
        }

        return values.last()

    }

    private fun String.isNumber(): Boolean {
        return this.toDoubleOrNull() != null
    }

    private fun String.isOperator(): Boolean {
        return this in arrayOf("+", "-", "*", "/")
    }

    private fun hasPrecedence(op1: Char, op2: Char): Boolean {
        if (op2 == '(' || op2 == ')') return false
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false
        return true
    }

    private fun applyOp(op: Char, a: Double, b: Double): Double {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> {
                if (b == 0.0) throw ArithmeticException("Division by zero is not allowed.")
                a / b
            }

            else -> throw UnsupportedOperationException("Unsupported operator: $op")
        }
    }
}


