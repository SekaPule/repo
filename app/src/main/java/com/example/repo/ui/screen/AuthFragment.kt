package com.example.repo.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.repo.R
import com.example.repo.databinding.FragmentAuthBinding
import com.jakewharton.rxbinding4.widget.textChangeEvents
import io.reactivex.rxjava3.core.Observable


class AuthFragment : Fragment() {
    private lateinit var binding: FragmentAuthBinding
    private lateinit var observable: Observable<Boolean>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolBar.title = getString(R.string.auth_title)
        binding.toolBar.setNavigationIcon(R.drawable.ic_arrow_back)
        binding.toolBar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.authBtn.setOnClickListener {
            parentFragmentManager.also {
                it.setFragmentResult(AUTH_KEY, bundleOf(AUTH_BUNDLE_KEY to true))
                it.commit {
                    replace(
                        R.id.screenContainer,
                        CategoriesOfHelpingFragment.newInstance()
                    )
                }
            }
        }

        val emailObservable = binding.emailEditText
            .textChangeEvents()
            .skipInitialValue()
            .map { it.text.toString() }

        val passwordObservable = binding.passwordEditText
            .textChangeEvents()
            .skipInitialValue()
            .map { it.text.toString() }

        observable = Observable.combineLatest(emailObservable, passwordObservable) { s1, s2 ->
            isValidForm(s1, s2)
        }

        observable.subscribe({
            updateButton(it)
        }, {
            it.localizedMessage?.let { it1 -> Log.e("Error", it1) }
        })
    }

    private fun isValidForm(email: String, password: String): Boolean {
        Log.e("TAGG", "$email - $password")
        if (email.length > 5 && password.length > 5) {
            return true
        }
        return false
    }

    private fun updateButton(valid: Boolean) {
        binding.authBtn.isEnabled = valid
    }

    companion object {
        private const val AUTH_KEY = "authKey"
        private const val AUTH_BUNDLE_KEY = "authBundleKey"

        @JvmStatic
        fun newInstance() = AuthFragment()
    }
}