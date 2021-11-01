package com.example.myeyes.presentation.ui

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myeyes.R
import com.example.myeyes.databinding.ActivityMainBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var intentRecognizer: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MyEyes)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUserPermissions()
        intentRecognizer = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intentRecognizer.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(p0: Bundle?) {

            }

            override fun onBeginningOfSpeech() {

            }

            override fun onRmsChanged(p0: Float) {

            }

            override fun onBufferReceived(p0: ByteArray?) {

            }

            override fun onEndOfSpeech() {

            }

            override fun onError(p0: Int) {

            }

            override fun onResults(bundle: Bundle?) {
                val matches: ArrayList<String>? =
                    bundle?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (matches != null) {
                    binding.textView.text = matches.get(0)
                }
            }

            override fun onPartialResults(p0: Bundle?) {

            }

            override fun onEvent(p0: Int, p1: Bundle?) {

            }

        })

        binding.startButton.setOnClickListener {
            speechRecognizer.startListening(intentRecognizer)
        }

        binding.stopButton.setOnClickListener {
            speechRecognizer.stopListening()
        }
    }

    private fun setUserPermissions() {
        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.RECORD_AUDIO
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) {
                    }

                    if (report.isAnyPermissionPermanentlyDenied) {
                        showSettingsDialog()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest>,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).withErrorListener {
                Toast.makeText(
                    baseContext,
                    "Error occurred! ",
                    Toast.LENGTH_SHORT
                ).show()
            }
            .onSameThread()
            .check()
    }

    private fun showSettingsDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(baseContext)
        builder.setTitle("Need Permissions")
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.")
        builder.setPositiveButton(
            "GOTO SETTINGS"
        ) { dialog, which ->
            dialog.cancel()
        }
        builder.setNegativeButton(
            "Cancel"
        ) { dialog, which -> dialog.cancel() }
        builder.show()
    }
}