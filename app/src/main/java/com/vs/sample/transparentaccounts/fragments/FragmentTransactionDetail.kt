package com.vs.sample.transparentaccounts.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import com.vs.sample.transparentaccounts.R
import com.vs.sample.transparentaccounts.databinding.FragmentTransactionDetailBinding
import com.vs.sample.transparentaccounts.databinding.FragmentTransactionsBinding

class FragmentTransactionDetail : Fragment() {

    val args: FragmentTransactionDetailArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentTransactionDetailBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.transaction = args.transaction

        return binding.root
    }

}
