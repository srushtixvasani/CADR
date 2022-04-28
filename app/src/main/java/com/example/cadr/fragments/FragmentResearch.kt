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


class FragmentResearch : Fragment() {

    var mediaController: MediaController?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_research, container, false)

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scrollView = view!!.findViewById<ScrollView>(R.id.scrollViewResearch)
        scrollView.post(Runnable { scrollView.scrollTo(0, 0) })

        // Environments of ageing
        val researchVideoView1 = view!!.findViewById<VideoView>(R.id.research1_video)
        val researchThumbnail1 = view!!.findViewById<ImageView>(R.id.research1_thumbnail)
        val researchBtn = view!!.findViewById<ImageButton>(R.id.research1_btn)

        Glide.with(context!!.applicationContext).load(R.raw.research1_thumbnail_pic).into(
            researchThumbnail1
        );

        val videoViewPath = "android.resource://" + context!!.packageName + "/" + R.raw.environments_of_ageing
        val uri = Uri.parse(videoViewPath)

        researchVideoView1.setVideoURI(uri)
        researchVideoView1.requestFocus()
        researchBtn.setOnClickListener {
            researchThumbnail1.visibility = View.INVISIBLE
            researchBtn.visibility = View.INVISIBLE
            researchVideoView1.start()
        }

        researchVideoView1.setOnPreparedListener { mp ->
            researchVideoView1.pause()
            researchVideoView1.visibility = View.VISIBLE
            researchBtn.visibility = View.VISIBLE

            if (researchBtn.performContextClick()){
                researchVideoView1.visibility = View.INVISIBLE
                researchBtn.visibility = View.INVISIBLE
            }

            mp.setOnVideoSizeChangedListener { mp, width, height ->
                mediaController = MediaController(activity)
                researchVideoView1.setMediaController(mediaController)
                researchVideoView1.keepScreenOn = true
                mediaController!!.setAnchorView(researchVideoView1)
            }

        }

        researchVideoView1.setOnCompletionListener {
            researchThumbnail1.visibility = View.VISIBLE
            researchBtn.visibility = View.VISIBLE
            Toast.makeText(activity, "Thank you for watching!", Toast.LENGTH_LONG).show()

            if (researchBtn.performContextClick()){
                researchThumbnail1.visibility = View.GONE
                researchBtn.visibility = View.GONE
            }
        }

        researchVideoView1.setOnErrorListener { mp, what, extra ->
            Toast.makeText(activity, "Sorry, an error occurred,", Toast.LENGTH_LONG).show()
            false
        }


        // Dementia and Cognition in Ageing
        val researchVideoView2 = view!!.findViewById<VideoView>(R.id.research2_video)
        val researchThumbnail2 = view!!.findViewById<ImageView>(R.id.research2_thumbnail)
        val research2Btn = view!!.findViewById<ImageButton>(R.id.research2_btn)

        Glide.with(context!!.applicationContext).load(R.raw.research2_thumbnail).into(
            researchThumbnail2
        );

        val videoViewPath2 = "android.resource://" + context!!.packageName + "/" + R.raw.dementia_and_cognition_in_ageing
        val uri2 = Uri.parse(videoViewPath2)

        researchVideoView2.setVideoURI(uri2)
        researchVideoView2.requestFocus()
        research2Btn.setOnClickListener {
            researchThumbnail2.visibility = View.INVISIBLE
            research2Btn.visibility = View.INVISIBLE
            researchVideoView2.start()
        }

        researchVideoView2.setOnPreparedListener { mp ->
            researchVideoView2.pause()
            researchVideoView2.visibility = View.VISIBLE
            research2Btn.visibility = View.VISIBLE

            if (research2Btn.performContextClick()){
                researchVideoView2.visibility = View.INVISIBLE
                research2Btn.visibility = View.INVISIBLE
            }

            mp.setOnVideoSizeChangedListener { mp, width, height ->
                mediaController = MediaController(activity)
                researchVideoView2.setMediaController(mediaController)
                researchVideoView2.keepScreenOn = true
                mediaController!!.setAnchorView(researchVideoView2)
            }

        }

        researchVideoView2.setOnCompletionListener {
            researchThumbnail2.visibility = View.VISIBLE
            research2Btn.visibility = View.VISIBLE
            Toast.makeText(activity, "Thank you for watching!", Toast.LENGTH_LONG).show()

            if (research2Btn.performContextClick()){
                researchThumbnail2.visibility = View.INVISIBLE
                research2Btn.visibility = View.INVISIBLE
            }
        }

        researchVideoView2.setOnErrorListener { mp, what, extra ->
            Toast.makeText(activity, "Sorry, an error occurred,", Toast.LENGTH_LONG).show()
            false
        }


        // Support Health and wellbeing
        val researchVideoView3 = view!!.findViewById<VideoView>(R.id.research3_video)
        val researchThumbnail3 = view!!.findViewById<ImageView>(R.id.research3_thumbnail)
        val researchBtn3 = view!!.findViewById<ImageButton>(R.id.research3_btn)

        Glide.with(context!!.applicationContext).load(R.raw.research3_thumbnail_pic).into(
            researchThumbnail3
        );

        val videoViewPath3 = "android.resource://" + context!!.packageName + "/" + R.raw.supporting_health_and_wellbeing_in_later_life
        val uri3 = Uri.parse(videoViewPath3)

        researchVideoView3.setVideoURI(uri3)
        researchVideoView3.requestFocus()
        researchBtn3.setOnClickListener {
            researchThumbnail3.visibility = View.INVISIBLE
            researchBtn3.visibility = View.INVISIBLE
            researchVideoView3.start()
        }

        researchVideoView3.setOnPreparedListener { mp ->
            researchVideoView3.pause()
            researchVideoView3.visibility = View.VISIBLE
            researchBtn3.visibility = View.VISIBLE

            if (researchBtn3.performContextClick()){
                researchVideoView3.visibility = View.INVISIBLE
                researchBtn3.visibility = View.INVISIBLE
            }

            mp.setOnVideoSizeChangedListener { mp, width, height ->
                mediaController = MediaController(activity)
                researchVideoView3.setMediaController(mediaController)
                researchVideoView3.keepScreenOn = true
                mediaController!!.setAnchorView(researchVideoView3)
            }

        }

        researchVideoView3.setOnCompletionListener {
            researchThumbnail3.visibility = View.VISIBLE
            researchBtn3.visibility = View.VISIBLE
            Toast.makeText(activity, "Thank you for watching!", Toast.LENGTH_LONG).show()

            if (researchBtn3.performContextClick()){
                researchThumbnail3.visibility = View.GONE
                researchBtn3.visibility = View.GONE
            }
        }

        researchVideoView3.setOnErrorListener { mp, what, extra ->
            Toast.makeText(activity, "Sorry, an error occurred,", Toast.LENGTH_LONG).show()
            false
        }

    }


}