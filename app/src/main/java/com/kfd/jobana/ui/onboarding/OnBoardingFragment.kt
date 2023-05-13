package com.kfd.jobana.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.kfd.jobana.databinding.FragmentOnBoardingBinding


class OnBoardingFragment : Fragment() {

    private var title: String? = null
    private var desription: String? = null
    private var imageResource = 0

    private lateinit var tvTitle: AppCompatTextView
    private lateinit var tvDescription: AppCompatTextView
    private lateinit var image: AppCompatImageView

    private var _binding: FragmentOnBoardingBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            title = requireArguments().getString(TITLE)
            desription = requireArguments().getString(DESCRIPTION)
            imageResource = requireArguments().getInt(IMAGE_RESOURCE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        val view = binding.root

        tvTitle = binding.onboardingTitle
        tvDescription = binding.onboardingDescription
        image = binding.onboardingImage

        tvTitle.text = title
        tvDescription.text = desription
        image.setImageResource(imageResource)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
        private const val IMAGE_RESOURCE= "imageResource"

        fun newInstance(title: String?, description: String?, imageResource: Int) : OnBoardingFragment {
            val fragment = OnBoardingFragment()
            val args = Bundle()
            args.putString(TITLE, title)
            args.putString(DESCRIPTION, description)
            args.putInt(IMAGE_RESOURCE, imageResource)
            fragment.arguments = args
            return fragment
        }
    }

}