package io.github.frankolt.githubexplorer.ui.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.frankolt.githubexplorer.databinding.FragmentUserDetailsBinding

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private var _binding: FragmentUserDetailsBinding? = null

    /**
     * This property is only valid after the call to `onCreateView`, but before the call to
     * `onDestroyView`.
     */
    private val binding
        get() = _binding!!

    private val viewModel: UserDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
        setupUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
    }

    private fun setupUi() {
    }
}
