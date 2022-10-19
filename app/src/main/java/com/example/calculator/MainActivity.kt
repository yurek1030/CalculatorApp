package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    lateinit var bindClass : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindClass.root)

        bindClass.btn0.setOnClickListener {setTextFields("0")}
        bindClass.btn1.setOnClickListener {setTextFields("1")}
        bindClass.btn2.setOnClickListener {setTextFields("2")}
        bindClass.btn3.setOnClickListener {setTextFields("3")}
        bindClass.btn4.setOnClickListener {setTextFields("4")}
        bindClass.btn5.setOnClickListener {setTextFields("5")}
        bindClass.btn6.setOnClickListener {setTextFields("6")}
        bindClass.btn7.setOnClickListener {setTextFields("7")}
        bindClass.btn8.setOnClickListener {setTextFields("8")}
        bindClass.btn9.setOnClickListener {setTextFields("9")}
        bindClass.add.setOnClickListener {setTextFields("+")}
        bindClass.subtract.setOnClickListener {setTextFields("-")}
        bindClass.multiply.setOnClickListener {setTextFields("*")}
        bindClass.divide.setOnClickListener {setTextFields("/")}
        bindClass.comma.setOnClickListener{setTextFields(".")}

        bindClass.ac.setOnClickListener {
            bindClass.mathOperation.text = ""
            equation = ""
        }

        bindClass.back.setOnClickListener {
            val str = bindClass.mathOperation.text.toString()
            if (str.isNotEmpty()) {
                bindClass.mathOperation.text = str.substring(0, str.length - 1)
            }
            equation = equation.substring(0, equation.length - 1)
        }


        bindClass.equal.setOnClickListener{
            try{
                val ex = ExpressionBuilder(equation).build()
                val result = ex.evaluate()

                val longRes = result.toLong()
                if (result == longRes.toDouble()){
                    final_result = longRes.toString()
                }
                else{
                    final_result = result.toString()
                }


                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("final_result", final_result)
                startActivity(intent)


            }
            catch(e:Exception){
                final_result = "Нет ответа"
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("final_result", final_result)
                startActivity(intent)
            }

        }
    }


    var equation = "empty"
    var final_result = ""
    fun setTextFields(str: String) {
//       for saving first_number on a screen

//        val lastItem = equation[equation.length - 1].toString()
        if (equation == "empty"){
            equation = ""
        }
//        if ((lastItem == "+") || (lastItem == "-") ||
//            (lastItem == "*") || (lastItem == "/"))
//        {
//            bindClass.mathOperation.text = ""
//        }

        if ((str != "+") && (str != "-") && (str != "*") && (str != "/")){
            bindClass.mathOperation.append(str)
        }
        else {
            bindClass.mathOperation.text = ""
        }
        equation += str
    }
}