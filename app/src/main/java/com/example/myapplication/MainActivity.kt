package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var total: EditText = findViewById(R.id.valorTotal);
        var qtdPessoas: EditText = findViewById(R.id.qtdPessoas);
        var btSpeak: FloatingActionButton = findViewById(R.id.btnSpeak)

        total = findViewById(R.id.valorTotal);
        qtdPessoas = findViewById(R.id.qtdPessoas);
//        total.setText("0");
//        qtdPessoas.setText("0");

        total.doAfterTextChanged {

            var totalDouble = total.text.toString().toDouble()
            var qtdPessoasDouble = qtdPessoas.text.toString().toDouble()
            Log.d("PDM23", (totalDouble /qtdPessoasDouble).toString())
        }
    }

}