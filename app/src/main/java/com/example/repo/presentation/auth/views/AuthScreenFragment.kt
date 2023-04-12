package com.example.repo.presentation.auth.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.repo.R
import com.example.repo.databinding.FragmentAuthBinding
import com.example.repo.di.app.MainApplication.Companion.appComponent
import com.example.repo.domain.model.User
import com.example.repo.presentation.auth.vm.AuthIntent
import com.example.repo.presentation.auth.vm.AuthScreenViewModel
import com.example.repo.presentation.auth.vm.AuthState
import com.example.repo.presentation.categorieslist.views.CategoriesScreenFragment
import com.jakewharton.rxbinding4.widget.textChangeEvents
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject


class AuthScreenFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private lateinit var disposable: Disposable
    private lateinit var userObservable: Observable<User>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val authScreeViewModel: AuthScreenViewModel by viewModels { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appComponent().inject(this)
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
                it.commit {
                    replace(
                        R.id.screenContainer,
                        CategoriesScreenFragment.newInstance()
                    )
                }
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