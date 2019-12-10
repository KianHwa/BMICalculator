package com.example.bmicalculator

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    lateinit var bmiImg : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calculate = findViewById<Button>(R.id.buttonCalculate)
        calculate.setOnClickListener{calcBMI()}

        val reset = findViewById<Button>(R.id.buttonReset)
        reset.setOnClickListener{reset()}
    }

    private fun calcBMI(){
        val weight = findViewById<EditText>(R.id.editTextWeight)
        val height = findViewById<EditText>(R.id.editTextHeight)
        val viewBMI = findViewById<TextView>(R.id.textViewBMI)

        if(!weight.text.isBlank() && !height.text.isBlank()) {
            var bmi =
                weight.text.toString().toDouble() / ((height.text.toString().toDouble() / 100).pow(
                    2.0
                ))
            viewBMI.text = "BMI : " + String.format("%.2f", bmi.toString().toDouble())
            getBMIImg(bmi)
        }
        else{
            viewBMI.text = "BMI : 0"
            getBMIImg(0.0)
        }
    }

    private fun getBMIImg(bmi : Double){
        val bmiImage = findViewById<ImageView>(R.id.imageViewProfile)

         if(bmi<18.5)
            bmiImage.setImageResource(R.drawable.under)
        else if (bmi in 18.5..24.9)
             bmiImage.setImageResource(R.drawable.normal)
        else
             bmiImage.setImageResource(R.drawable.over)
    }

    private fun reset(){
        val weight = findViewById<EditText>(R.id.editTextWeight)
        val height = findViewById<EditText>(R.id.editTextHeight)
        val viewBMI = findViewById<TextView>(R.id.textViewBMI)
        val bmiImage = findViewById<ImageView>(R.id.imageViewProfile)

        weight.text = null
        height.text= null
        viewBMI.setText("BMI : ")
        bmiImage.setImageResource(R.drawable.empty)
    }

}
