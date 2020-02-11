package com.vs.sample.transparentaccounts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.vs.sample.transparentaccounts.databinding.FragmentAccountDetailBinding

class FragmentAccountDetail : Fragment() {


    val args: FragmentAccountDetailArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentAccountDetailBinding.inflate(inflater, container, false)

        binding.account = args.account

        return binding.root
    }

}
