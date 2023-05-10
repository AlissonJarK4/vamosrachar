package com.example.myapplication

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {


//    @android.support.annotation.RequiresApi(Build.VERSION_CODES.DONUT)
    override fun onCreate(savedInstanceState: Bundle?) {
        var tts: TextToSpeech? = TextToSpeech(this, this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var total: EditText = findViewById(R.id.valorTotal);
        var qtdPessoas: EditText = findViewById(R.id.qtdPessoas);
        var valorPessoa: TextView = findViewById(R.id.valorPessoa);
        var rsText: TextView = findViewById(R.id.rs);
        var btShare: FloatingActionButton = findViewById(R.id.btnShare)
        var btSpeak: FloatingActionButton = findViewById(R.id.btnSpeak)

        total = findViewById(R.id.valorTotal);
        qtdPessoas = findViewById(R.id.qtdPessoas);
//        total.setText("0");
//        qtdPessoas.setText("1");

        total.doAfterTextChanged {

            var totalDouble = total.text.toString().toDouble()
            var qtdPessoasInt = qtdPessoas.text.toString().toInt()
            var valorPessoa: TextView = findViewById(R.id.valorPessoa)
            valorPessoa.text = (totalDouble /qtdPessoasInt).format(2);
            Log.d("PDM23", (totalDouble /qtdPessoasInt).format(2))
        }

        qtdPessoas.doAfterTextChanged {

            if(qtdPessoas.text.toString() != "") {
                var totalDouble = total.text.toString().toDouble()
                var qtdPessoasInt = qtdPessoas.text.toString().toInt()
                var valorPessoa: TextView = findViewById(R.id.valorPessoa)
                valorPessoa.text = (totalDouble / qtdPessoasInt).format(2);
                Log.d("PDM23", (totalDouble / qtdPessoasInt).format(2))
            }
        }

        btShare.setOnClickListener {
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "Valor total: R$" + total.text + "\n" + "Pessoas: " + qtdPessoas.text + "\n" + "Valor pra cada pessoa: R\$" + valorPessoa.text);
            }
            startActivity(Intent.createChooser(shareIntent, "Vai mandar pra quem? 🤔"));
        }

        btSpeak.setOnClickListener {
            speak();
        }

    }

    override fun onInit(status: Int) {
        var btSpeak: FloatingActionButton = findViewById(R.id.btnSpeak)
        var tts: TextToSpeech? = TextToSpeech(this, this)

        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.getDefault())

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language not supported!")
            } else {
                btSpeak.isEnabled = true
            }
        }
    }

    fun Double.format(digits: Int) = "%.${digits}f".format(this)

    private fun speak() {
        var tts: TextToSpeech? = TextToSpeech(this, this)
        var valorPessoa: TextView = findViewById(R.id.valorPessoa);
        var rsText: TextView = findViewById(R.id.rs);
        val text = rsText.text.toString() + valorPessoa.text.toString()
        tts!!.speak("O valor para cada pessoa é de " + text, TextToSpeech.QUEUE_FLUSH, null,"");
    }

    public override fun onDestroy() {
        var tts: TextToSpeech? = TextToSpeech(this, this)
        // Shutdown TTS
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

}