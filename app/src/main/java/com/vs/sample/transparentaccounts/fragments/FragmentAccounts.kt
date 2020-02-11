package com.vs.sample.transparentaccounts.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vs.sample.transparentaccounts.R
import com.vs.sample.transparentaccounts.viewmodels.FragmentAccountsVM

class FragmentAccounts : Fragment() {

    companion object {
        fun newInstance() = FragmentAccounts()
    }

    private lateinit var viewModel: FragmentAccountsVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_accounts, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentAccountsVM::class.java)
        // TODO: Use the ViewModel
    }

}
