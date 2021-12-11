package com.example.guesssports.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.os.*
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.example.guesssports.R
import com.example.guesssports.Settings
import com.example.guesssports.databinding.FragmentPlayBinding
import com.example.guesssports.model.Question
import kotlin.collections.ArrayList
import android.os.CountDownTimer

import android.R.string.no
import java.util.*


class PlayFragment() : Fragment() {

    private lateinit var binding: FragmentPlayBinding
    var count: Int = 0
    var wrong = 3
    var ques = 1
    private var countrueAnswers: Int = 0
    var mediaPlayer: MediaPlayer? = null
    var list = ArrayList<Question>()
    private lateinit var timer: CountDownTimer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayBinding.inflate(inflater, container, false)

        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.wrong_answer)
        initListQuestion()
        setQuestion(count)

        val a = true
        timer = object : CountDownTimer(2000, 1000){

            override fun onTick(p0: Long) {

                val activeWeek: LinearLayout? = null
                var answer = ""
                val weekClickListen = View.OnClickListener{

                    ques++
                    binding.sound.text = ques.toString()

                    if (activeWeek != it) {
                        when (it.id) {

                            R.id.single -> {
                                start()
                                answer = binding.title1.text.toString()

                                if(answer == list[count].trueAnsTitle)
                                {
                                    countrueAnswers++
                                }
                                else
                                {
                                    wrong--
                                    mediaPlayer?.start()
                                    if(Settings.vibrate)
                                    {
                                        vibrate()
                                    }
                                    binding.liveCount.text = "x$wrong"
                                    if(wrong == 0)
                                    {
                                        binding.sound.text = ""
                                        mediaPlayer?.start()

                                        val bundle = Bundle()
                                        bundle.putString("trueAns", countrueAnswers.toString())
                                        if(a)
                                        {
                                            cancel()
                                        }
                                        findNavController().navigate(R.id.resultFragment, bundle)
                                    }
                                }
                            }
                            R.id.group -> {
                                start()
                                answer = binding.title2.text.toString()


                                if(answer == list[count].trueAnsTitle)
                                {
                                    countrueAnswers++
                                }
                                else
                                {
                                    if(Settings.vibrate)
                                    {
                                        vibrate()
                                    }
                                    wrong--
                                    mediaPlayer?.start()
                                    binding.liveCount.text = "x$wrong"
                                    if(wrong == 0)
                                    {
                                        binding.sound.text = ""
                                        mediaPlayer?.start()

                                        val bundle = Bundle()
                                        bundle.putString("trueAns", countrueAnswers.toString())
                                        if(a)
                                        {
                                            cancel()
                                        }
                                        findNavController().navigate(R.id.resultFragment, bundle)
                                    }
                                }
                            }
                        }
                    }
                    count++
                    setQuestion(count)
                }

                binding.single.setOnClickListener(weekClickListen)
                binding.group.setOnClickListener(weekClickListen)


            }
            override fun onFinish() {
                start()
                wrong--
                ques++
                count++
                mediaPlayer?.start()
                if(Settings.vibrate)
                {
                    vibrate()
                }
                binding.liveCount.text = "x$wrong"
                binding.sound.text = ques.toString()
                if(wrong == 0)
                {

                    mediaPlayer?.start()
                    binding.sound.text = ""
                    val bundle = Bundle()
                    bundle.putString("trueAns", countrueAnswers.toString())
                    if(a)
                    {
                        cancel()
                    }
                    findNavController().navigate(R.id.resultFragment, bundle)
                }
                setQuestion(count)
            }
        }.start()


        binding.rectangle.setOnClickListener {

            findNavController().navigate(R.id.homeFragment)
            timer.cancel()
        }

        return binding.root
}

    private fun vibrate() {
        val vibe = requireActivity().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibe?.vibrate(VibrationEffect.createOneShot(150, 100))
        } else {
            vibe?.vibrate(100)
        }

    }
    private fun setQuestion(count: Int) {

        if (count < list.size-1) {

            list[count].question?.let { binding.image.setImageResource(it) }
            list[count].ans1?.let { binding.ans1.setImageResource(it) }
            list[count].ans2?.let { binding.ans2.setImageResource(it) }
            binding.title1.text = list[count].ansTitle1
            binding.title2.text = list[count].ansTitle2

        } else {
            findNavController().navigate(R.id.resultFragment)
        }
    }


    private fun initListQuestion() {

        list = ArrayList<Question>()


            list.add(
                Question(
                    R.drawable.skate,
                    R.drawable.ic_vector__2_,
                    R.drawable.ic_union__1_,
                    "1", "2",
                    "1"
                )
            )
            list.add(
                Question(
                    R.drawable.ic_frame_2,
                    R.drawable.ic_vector__2_,
                    R.drawable.ic_union__1_,
                    "1", "2",
                    "2"
                )
            )
        list.add(
            Question(
                R.drawable.hokkey,
                R.drawable.ic_vector__2_,
                R.drawable.ic_union__1_,
                "1", "2",
                "1"
            )
        )
        list.add(
            Question(
                R.drawable.hokkey_2,
                R.drawable.ic_vector__2_,
                R.drawable.ic_union__1_,
                "1", "2",
                "1"
            )
        )

        list.add(
            Question(
                R.drawable.gun,
                R.drawable.ic_vector__2_,
                R.drawable.ic_union__1_,
                "1", "2",
                "2"
            )
        )
        list.add(
            Question(
                R.drawable.ic_group_43,
                R.drawable.ic_vector__2_,
                R.drawable.ic_union__1_,
                "1", "2",
                "1"
            )
        )
        list.add(
            Question(
                R.drawable.ic_group_34,
                R.drawable.ic_vector__2_,
                R.drawable.ic_union__1_,
                "1", "2",
                "1"
            )
        )
        list.add(
            Question(
                R.drawable.ic_group_39,
                R.drawable.ic_vector__2_,
                R.drawable.ic_union__1_,
                "1", "2",
                "1"
            )
        )
        list.add(
            Question(
                R.drawable.ic_group_71,
                R.drawable.ic_vector__2_,
                R.drawable.ic_union__1_,
                "1", "2",
                "2"
            )
        )
        list.add(
            Question(
                R.drawable.ic_group_76,
                R.drawable.ic_vector__2_,
                R.drawable.ic_union__1_,
                "1", "2",
                "2"
            )
        )
        list.add(
            Question(
                R.drawable.ic_group_77,
                R.drawable.ic_vector__2_,
                R.drawable.ic_union__1_,
                "1", "2",
                "2"
            )
        )
        list.add(
            Question(
                R.drawable.ic_group_75,
                R.drawable.ic_vector__2_,
                R.drawable.ic_union__1_,
                "1", "2",
                "2"
            )
        )
        list.add(
            Question(
                R.drawable.ic_group_52,
                R.drawable.ic_vector__2_,
                R.drawable.ic_union__1_,
                "1", "2",
                "2"
            )
        )
        list.add(
            Question(
                R.drawable.ic_group_54,
                R.drawable.ic_vector__2_,
                R.drawable.ic_union__1_,
                "1", "2",
                "2"
            )
        )
        list.add(
            Question(
                R.drawable.kamon,
                R.drawable.ic_vector__2_,
                R.drawable.ic_union__1_,
                "1", "2",
                "2"
            )
        )
        list.add(
            Question(
                R.drawable.man,
                R.drawable.ic_vector__2_,
                R.drawable.ic_union__1_,
                "1", "2",
                "2"
            )
        )
        list.add(
            Question(
                R.drawable.ice_fly,
                R.drawable.ic_vector__2_,
                R.drawable.ic_union__1_,
                "1", "2",
                "1"
            )
        )
        list.add(
            Question(
                R.drawable.shvabra,
                R.drawable.ic_vector__2_,
                R.drawable.ic_union__1_,
                "1", "2",
                "1"
            )
        )
        list.add(
            Question(
                R.drawable.tennis,
                R.drawable.ic_vector__2_,
                R.drawable.ic_union__1_,
                "1", "2",
                "2"
            )
        )
        list.add(
            Question(
                R.drawable.ic_frame__2_,
                R.drawable.ic_vector__2_,
                R.drawable.ic_union__1_,
                "1", "2",
                "2"
            )
        )

        println("PlayFragment.initListQuestion")

        list.add(
            Question(
                R.drawable.skate,
                R.drawable.ic_vector__3_,
                R.drawable.ic_vector__4_,
                "1", "2",
                "1"
            )
        )
        list.add(
            Question(
                R.drawable.ic_frame_2,
                R.drawable.ic_vector__3_,
                R.drawable.ic_vector__4_,
                "1", "2",
                "1"
            )
        )
        list.add(
            Question(
                R.drawable.hokkey,
                R.drawable.ic_vector__3_,
                R.drawable.ic_vector__4_,
                "1", "2",
                "2"
            )
        )
        list.add(
            Question(
                R.drawable.hokkey_2,
                R.drawable.ic_vector__3_,
                R.drawable.ic_vector__4_,
                "1", "2",
                "2"
            )
        )

        list.add(
            Question(
                R.drawable.gun,
                R.drawable.ic_vector__3_,
                R.drawable.ic_vector__4_,
                "1", "2",
                "1"
            )
        )
        list.add(
            Question(
                R.drawable.ic_group_43,
                R.drawable.ic_vector__3_,
                R.drawable.ic_vector__4_,
                "1", "2",
                "1"
            )
        )
        list.add(
            Question(
                R.drawable.ic_group_34,
                R.drawable.ic_vector__3_,
                R.drawable.ic_vector__4_,
                "1", "2",
                "2"
            )
        )
        list.add(
            Question(
                R.drawable.ic_group_39,
                R.drawable.ic_vector__3_,
                R.drawable.ic_vector__4_,
                "1", "2",
                "2"
            )
        )
        list.add(
            Question(
                R.drawable.ic_group_71,
                R.drawable.ic_vector__3_,
                R.drawable.ic_vector__4_,
                "1", "2",
                "2"
            )
        )
        list.add(
            Question(
                R.drawable.ic_group_76,
                R.drawable.ic_vector__3_,
                R.drawable.ic_vector__4_,
                "1", "2",
                "2"
            )
        )
        list.add(
            Question(
                R.drawable.ic_group_77,
                R.drawable.ic_vector__3_,
                R.drawable.ic_vector__4_,
                "1", "2",
                "2"
            )
        )
        list.add(
            Question(
                R.drawable.ic_group_75,
                R.drawable.ic_vector__3_,
                R.drawable.ic_vector__4_,
                "1", "2",
                "2"
            )
        )
        list.add(
            Question(
                R.drawable.ic_group_52,
                R.drawable.ic_vector__3_,
                R.drawable.ic_vector__4_,
                "1", "2",
                "1"
            )
        )
        list.add(
            Question(
                R.drawable.ic_group_54,
                R.drawable.ic_vector__3_,
                R.drawable.ic_vector__4_,
                "1", "2",
                "2"
            )
        )
        list.add(
            Question(
                R.drawable.kamon,
                R.drawable.ic_vector__3_,
                R.drawable.ic_vector__4_,
                "1", "2",
                "1"
            )
        )
        list.add(
            Question(
                R.drawable.man,
                R.drawable.ic_vector__3_,
                R.drawable.ic_vector__4_,
                "1", "2",
                "1"
            )
        )
        list.add(
            Question(
                R.drawable.ice_fly,
                R.drawable.ic_vector__3_,
                R.drawable.ic_vector__4_,
                "1", "2",
                "1"
            )
        )
        list.add(
            Question(
                R.drawable.shvabra,
                R.drawable.ic_vector__3_,
                R.drawable.ic_vector__4_,
                "1", "2",
                "2"
            )
        )
        list.add(
            Question(
                R.drawable.tennis,
                R.drawable.ic_vector__3_,
                R.drawable.ic_vector__4_,
                "1", "2",
                "1"
            )
        )
        list.add(
            Question(
                R.drawable.ic_frame__2_,
                R.drawable.ic_vector__3_,
                R.drawable.ic_vector__4_,
                "1", "2",
                "1"
            )
        )
        list.shuffle()

    }
    override fun onPause() {
        super.onPause()
        mediaPlayer?.stop();
        mediaPlayer?.release();
    }
}
