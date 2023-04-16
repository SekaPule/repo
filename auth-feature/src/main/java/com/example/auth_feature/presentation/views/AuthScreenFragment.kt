package com.example.auth_feature.presentation.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.auth_feature.R
import com.example.auth_feature.databinding.FragmentAuthScreenBinding
import com.example.auth_feature.di.AuthFeatureComponentViewModel
import com.example.auth_feature.interactor.model.User
import com.example.auth_feature.presentation.vm.AuthIntent
import com.example.auth_feature.presentation.vm.AuthScreenViewModel
import com.example.auth_feature.presentation.vm.AuthState
import com.jakewharton.rxbinding4.widget.textChangeEvents
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject


class AuthScreenFragment : Fragment() {

    private lateinit var binding: FragmentAuthScreenBinding
    private lateinit var disposable: Disposable
    private lateinit var userObservable: Observable<User>

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureToolBar()
        setObservers()
        setViewListeners()
    }

    private fun configureToolBar() {
        binding.toolBar.title = getString(R.string.auth_title)
        binding.toolBar.setNavigationIcon(R.drawable.ic_arrow_back)
    }

    private fun setViewListeners() {
        binding.toolBar.setNavigationOnClickListener {
            requireActivity().finish()
        }

        binding.authBtn.setOnClickListener {
            parentFragmentManager.also {
                it.setFragmentResult(AUTH_KEY, bundleOf(AUTH_BUNDLE_KEY to true))
            }
        }
    }

    private fun setObservers() {
        val emailObservable = binding.emailEditText
            .textChangeEvents()
            .skipInitialValue()
            .map { it.text.toString() }

        val passwordObservable = binding.passwordEditText
            .textChangeEvents()
            .skipInitialValue()
            .map { it.text.toString() }

        authScreeViewModel.stateObservable.observe(viewLifecycleOwner) { authState ->
            updateView(state = authState)
        }

        userObservable = Observable.combineLatest(emailObservable, passwordObservable) { s1, s2 ->
            User(email = s1, password = s2)
        }

        disposable = userObservable.subscribe { user ->
            authScreeViewModel.obtainIntent(AuthIntent.AuthValidateIntent(user = user))
        }
    }

    private fun updateView(state: AuthState) {
        when (state) {
            AuthState.ValidationError -> binding.authBtn.isEnabled = false
            AuthState.ValidationSuccess -> binding.authBtn.isEnabled = true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    companion object {
        private const val AUTH_KEY = "authKey"
        private const val AUTH_BUNDLE_KEY = "authBundleKey"

        @JvmStatic
        fun newInstance() = AuthScreenFragment()
    }
}