package com.example.applicationtts

import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfRenderer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.HashSet

class MainActivity : AppCompatActivity() {

    lateinit var tts: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this, TextToSpeech.OnInitListener {
            Toast.makeText(
                this,
                if (it == 0) "initialised successfully" else "error in initialization",
                Toast.LENGTH_SHORT
            ).show()

            val stringBuilder = StringBuilder()
            tts.voices.iterator().forEach {
                voice -> stringBuilder.append(voice.name + "\n")
            }

            Log.d("voices", stringBuilder.toString())

            val locale = Locale("hi", "IN")
            tts.language = locale
//            tts.voice = Voice(
//                "hi-IN-Wavenet-A",
//                locale,
//                Voice.QUALITY_NORMAL,
//                Voice.LATENCY_NORMAL,
//                true,
//                tts.voice.features
//            )
        })

        button.setOnClickListener(View.OnClickListener {
            tts.speak(
                editText.text,
                TextToSpeech.QUEUE_FLUSH,
                null,
                TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID
            )
        })
    }

}
