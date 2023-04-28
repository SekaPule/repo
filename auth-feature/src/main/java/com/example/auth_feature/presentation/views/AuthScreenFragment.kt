package com.example.auth_feature.presentation.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.auth_feature.databinding.FragmentAuthScreenBinding
import com.example.auth_feature.di.AuthFeatureComponentViewModel
import com.example.auth_feature.presentation.theme.RepoTheme
import com.example.auth_feature.presentation.vm.AuthScreenViewModel
import javax.inject.Inject


class AuthScreenFragment : Fragment() {

    private lateinit var binding: FragmentAuthScreenBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val authScreeViewModel: AuthScreenViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        ViewModelProvider(this)
            .get<AuthFeatureComponentViewModel>()
            .authFeatureComponent
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthScreenBinding.inflate(inflater)
        return ComposeView(requireContext()).apply {
            setContent {
                RepoTheme {
                    AuthScreen(viewModel = authScreeViewModel)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigationListeners()
    }

    private fun navigationListeners() {
        authScreeViewModel.isAuthorized.observe(viewLifecycleOwner) { isAuthorized ->
            if (isAuthorized) {
                parentFragmentManager.also {
                    it.setFragmentResult(AUTH_KEY, bundleOf(AUTH_BUNDLE_KEY to true))
                }
            }
        }

        authScreeViewModel.close.observe(viewLifecycleOwner) { close ->
            if (close) {
                requireActivity().finish()
            }
        }
    }


    companion object {
        private const val AUTH_KEY = "authKey"
        private const val AUTH_BUNDLE_KEY = "authBundleKey"

        @JvmStatic
        fun newInstance() = AuthScreenFragment()
    }
}