package com.vs.sample.transparentaccounts.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.vs.sample.transparentaccounts.R
import com.vs.sample.transparentaccounts.viewmodels.FragmentAccountDetailVM

class FragmentAccountDetail : Fragment() {

    companion object {
        fun newInstance() = FragmentAccountDetail()
    }

    private lateinit var viewModel: FragmentAccountDetailVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentAccountDetailVM::class.java)
        // TODO: Use the ViewModel
    }

}
