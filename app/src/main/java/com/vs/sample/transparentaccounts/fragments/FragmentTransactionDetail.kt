package com.vs.sample.transparentaccounts.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.vs.sample.transparentaccounts.R
import com.vs.sample.transparentaccounts.viewmodels.FragmentTransactionDetailVM

class FragmentTransactionDetail : Fragment() {

    companion object {
        fun newInstance() = FragmentTransactionDetail()
    }

    private lateinit var viewModel: FragmentTransactionDetailVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transaction_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentTransactionDetailVM::class.java)
        // TODO: Use the ViewModel
    }

}
