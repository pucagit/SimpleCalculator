package com.example.calculator

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var textResult: TextView

    private var state: Int = 1  // state = {1: input for op1, 2: input for op2}
    private var op: Int = 0  // op = {1: add, 2: minus, 3: multiply, 4: divide}
    private var op1: String = ""
    private var op2: String = ""
    private var isDecimal: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.text_result)

        findViewById<Button>(R.id.btn0).setOnClickListener(this)
        findViewById<Button>(R.id.btn1).setOnClickListener(this)
        findViewById<Button>(R.id.btn2).setOnClickListener(this)
        findViewById<Button>(R.id.btn3).setOnClickListener(this)
        findViewById<Button>(R.id.btn4).setOnClickListener(this)
        findViewById<Button>(R.id.btn5).setOnClickListener(this)
        findViewById<Button>(R.id.btn6).setOnClickListener(this)
        findViewById<Button>(R.id.btn7).setOnClickListener(this)
        findViewById<Button>(R.id.btn8).setOnClickListener(this)
        findViewById<Button>(R.id.btn9).setOnClickListener(this)
        findViewById<Button>(R.id.btnAdd).setOnClickListener(this)
        findViewById<Button>(R.id.btnSub).setOnClickListener(this)
        findViewById<Button>(R.id.btnMulti).setOnClickListener(this)
        findViewById<Button>(R.id.btnDiv).setOnClickListener(this)
        findViewById<Button>(R.id.btnDot).setOnClickListener(this)
        findViewById<Button>(R.id.btnAddorSub).setOnClickListener(this)
        findViewById<Button>(R.id.btnC).setOnClickListener(this)
        findViewById<Button>(R.id.btnCE).setOnClickListener(this)
        findViewById<Button>(R.id.btnBS).setOnClickListener(this)
        findViewById<Button>(R.id.btnEqual).setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val id = p0?.id
        when (id) {
            R.id.btn0 -> addDigit("0")
            R.id.btn1 -> addDigit("1")
            R.id.btn2 -> addDigit("2")
            R.id.btn3 -> addDigit("3")
            R.id.btn4 -> addDigit("4")
            R.id.btn5 -> addDigit("5")
            R.id.btn6 -> addDigit("6")
            R.id.btn7 -> addDigit("7")
            R.id.btn8 -> addDigit("8")
            R.id.btn9 -> addDigit("9")
            R.id.btnDot -> addDot()
            R.id.btnAdd -> { op = 1; state = 2; isDecimal = false }
            R.id.btnSub -> { op = 2; state = 2; isDecimal = false }
            R.id.btnMulti -> { op = 3; state = 2; isDecimal = false }
            R.id.btnDiv -> { op = 4; state = 2; isDecimal = false }
            R.id.btnEqual -> calculate()
            R.id.btnAddorSub -> toggleSign()
            R.id.btnC -> clearAll()
            R.id.btnCE -> clearEntry()
            R.id.btnBS -> backspace()
        }
    }

    private fun addDigit(c: String) {
        if(state == 1) {
            if(op1 == "0") op1 = c;
            else op1 += c;
            textResult.text = op1;
        } else {
            if(op2 == "0") op2 = c;
            else op2 += c;
            textResult.text = op2;
        }
    }

    private fun addDot() {
        if(!isDecimal) {
            isDecimal == true;
            if(state == 1) {
                op1 += "."
                textResult.text = op1;
            }
            else {
                op2 += "."
                textResult.text = op2;
            }
        }
    }

    private fun toggleSign() {
        if(state == 1) {
            op1 = (-op1.toDouble()).toString();
            textResult.text = op1;
        } else {
            op2 = (-op2.toDouble()).toString();
            textResult.text = op1;
        }
    }

    private fun clearAll() {
        state = 1;
        op = 0;
        op1 = "0";
        op2 = "0";
        textResult.text = "0";
        isDecimal = false;
    }

    private fun clearEntry() {
        if(state == 1) {
            op1 = "0";
        } else op2 = "0";
        textResult.text = "0";
        isDecimal = false;
    }

    private fun backspace() {
        if (state == 1) {
            op1 = if (op1.length > 1) op1.dropLast(1) else "0"
            textResult.text = op1
        } else {
            op2 = if (op2.length > 1) op2.dropLast(1) else "0"
            textResult.text = op2
        }
    }

    private fun calculate() {
        var result = 0.0
        when (op) {
            1 -> result = op1.toDouble() + op2.toDouble()
            2 -> result = op1.toDouble() - op2.toDouble()
            3 -> result = op1.toDouble() * op2.toDouble()
            4 -> if (op2.toDouble() != 0.0) result = op1.toDouble() / op2.toDouble() else {
                textResult.text = "Error"
                return
            }
        }

        if (result == result.toInt().toDouble()) {
            textResult.text = result.toInt().toString()
        } else {
            textResult.text = result.toString()
        }
    }
}