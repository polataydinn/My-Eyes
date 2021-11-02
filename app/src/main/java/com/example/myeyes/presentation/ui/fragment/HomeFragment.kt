package com.example.myeyes.presentation.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myeyes.databinding.FragmentHomeBinding
import java.util.ArrayList


class HomeFragment : Fragment() {
    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding
    private lateinit var speechRecognizer: SpeechRecognizer
    private lateinit var intentRecognizer: Intent

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        intentRecognizer = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intentRecognizer.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )


        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(requireContext())
        speechRecognizer.setRecognitionListener(object: RecognitionListener{
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

        binding.startRecord.setOnClickListener {
            speechRecognizer.startListening(intentRecognizer)
        }

        binding.stopRecord.setOnClickListener {
            speechRecognizer.stopListening()
        }
    }

}