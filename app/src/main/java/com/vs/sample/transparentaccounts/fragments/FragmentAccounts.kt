package com.vs.sample.transparentaccounts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.vs.sample.transparentaccounts.R
import com.vs.sample.transparentaccounts.utils.InjectorUtils
import com.vs.sample.transparentaccounts.viewmodels.FragmentAccountsVM

class FragmentAccounts : Fragment() {

    private val viewModel: FragmentAccountsVM by viewModels {
        InjectorUtils.provideAccountsVMFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAccounts()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        viewModel.accounts.observe(viewLifecycleOwner) {

        }

        return inflater.inflate(R.layout.fragment_accounts, container, false)
    }
}
