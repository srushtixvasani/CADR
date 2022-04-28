package com.example.cadr.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.cadr.R


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentAboutUs.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentAboutUs : Fragment() {

    var mediaController: MediaController ?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_us, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Add video to welcome page
        val videoView = view!!.findViewById<VideoView>(R.id.aboutUsVideo)
        val thumbnailVideoView = view!!.findViewById<ImageView>(R.id.aboutUsThumbnail)
        val playBtn = view!!.findViewById<ImageButton>(R.id.aboutUsBtn)


        Glide.with(context!!.applicationContext).load(R.raw.about_us_thumbnail).into(
            thumbnailVideoView
        );

        val videoViewPath = "android.resource://" + context!!.packageName + "/" + R.raw.about_us
        val uri = Uri.parse(videoViewPath)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        playBtn.setOnClickListener {
            thumbnailVideoView.visibility = View.GONE
            playBtn.visibility = View.GONE
            videoView.start()
        }


        videoView.setOnPreparedListener { mp ->
            videoView.pause()
            thumbnailVideoView.visibility = View.VISIBLE
            playBtn.visibility = View.VISIBLE

            if (playBtn.performContextClick()){
                thumbnailVideoView.visibility = View.GONE
                playBtn.visibility = View.GONE
            }

            mp.setOnVideoSizeChangedListener { mp, width, height ->
                mediaController = MediaController(activity)
                videoView.setMediaController(mediaController)
                videoView.keepScreenOn = true
                mediaController!!.setAnchorView(videoView)
            }

        }


        videoView.setOnCompletionListener {
            thumbnailVideoView.visibility = View.VISIBLE
            playBtn.visibility = View.VISIBLE
            Toast.makeText(activity, "Thank you for watching!", Toast.LENGTH_LONG).show()

            if (playBtn.performContextClick()){
                thumbnailVideoView.visibility = View.GONE
                playBtn.visibility = View.GONE
            }
        }

        videoView.setOnErrorListener { mp, what, extra ->
            Toast.makeText(activity, "Sorry, an error occurred,", Toast.LENGTH_LONG).show()
            false
        }


//        videoView.initialize(api_key, object: YouTubePlayer.OnInitializedListener{
//            override fun onInitializationSuccess(
//                p0: YouTubePlayer.Provider?,
//                p1: YouTubePlayer?,
//                p2: Boolean
//            ) {
//                p1?.loadVideo("ld2cQRCk9CA")
//                p1?.play()
//            }
//
//            override fun onInitializationFailure(
//                p0: YouTubePlayer.Provider?,
//                p1: YouTubeInitializationResult?
//            ) {
//                Toast.makeText(activity, "Sorry, an error occurred,", Toast.LENGTH_LONG).show()
//            }
//
//        })
    }





}