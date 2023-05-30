package com.example.dresstique_android.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import com.example.dresstique_android.R

class PromoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_promo, container, false)
        val videoView = view.findViewById<VideoView>(R.id.promo_video1)
        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        val path = "android.resource://" + requireContext().packageName + "/" + R.raw.promo
        videoView.setVideoURI(Uri.parse(path))
        videoView.requestFocus()
        videoView.start()

        return view
    }
}