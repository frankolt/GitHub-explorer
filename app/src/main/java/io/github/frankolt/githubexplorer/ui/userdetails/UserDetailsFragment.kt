package io.github.frankolt.githubexplorer.ui.userdetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import io.github.frankolt.githubexplorer.R
import io.github.frankolt.githubexplorer.databinding.FragmentUserDetailsBinding
import io.github.frankolt.githubexplorer.domain.github.models.User
import io.github.frankolt.githubexplorer.ui.userdetails.events.UserDetailsEvent
import io.github.frankolt.githubexplorer.ui.userdetails.state.UserDetailsState

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    private var _binding: FragmentUserDetailsBinding? = null

    /**
     * This property is only valid after the call to `onCreateView`, but before the call to
     * `onDestroyView`.
     */
    private val binding
        get() = _binding!!

    private val args: UserDetailsFragmentArgs by navArgs()

    private val viewModel: UserDetailsViewModel by viewModels()

    private val userDetailsStateObserver = Observer<UserDetailsState> { state ->
        when (state) {
            is UserDetailsState.Loading -> showLoadingState()
            is UserDetailsState.Loaded -> showUserDetails(state.user)
            is UserDetailsState.Error -> showErrorState()
        }
    }

    private val eventObserver = Observer<UserDetailsEvent> {
        when (it) {
            is UserDetailsEvent.OpenInBrowser -> startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getUserDetails(args.username)
    }

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
        viewModel.userDetailsState.observe(viewLifecycleOwner, userDetailsStateObserver)
        viewModel.events.observe(viewLifecycleOwner, eventObserver)
    }

    private fun setupUi() {
        binding.backNavigationArrow.setOnClickListener { findNavController().navigateUp() }
        binding.viewInBrowserIcon.setOnClickListener { viewModel.openInBrowser() }
        binding.retryButton.setOnClickListener { viewModel.getUserDetails(args.username) }
    }

    private fun showLoadingState() {
        binding.progressBar.isVisible = true
        binding.viewInBrowserIcon.isVisible = false
        binding.avatar.isVisible = false
        binding.name.isVisible = false
        binding.userDetailsScrollView.isVisible = false
        binding.errorStateContainer.isVisible = false
    }

    private fun showUserDetails(user: User) {
        binding.progressBar.isVisible = false
        binding.viewInBrowserIcon.isVisible = true
        binding.avatar.isVisible = true
        binding.name.isVisible = true
        binding.userDetailsScrollView.isVisible = true
        binding.errorStateContainer.isVisible = false

        // TODO: What happens if `avatarUrl` is `null`?
        user.avatarUrl?.let { avatarUrl ->
            Glide.with(this).load(avatarUrl).centerCrop().into(binding.avatar)
        }

        if (user.name != null) {
            // TODO: What happens if `login` is `null`? It probably shouldn't ever be `null`,
            //  though, so perhaps it shouldn't be nullable.
            user.login?.let { login ->
                binding.name.text =
                    getString(R.string.userdetails_format_username_and_name).format(
                        login,
                        user.name
                    )
            }
        } else {
            // TODO: What happens if `login` is `null`? It probably shouldn't ever be `null`,
            //  though, so perhaps it shouldn't be nullable.
            user.login?.let { login ->
                binding.name.text = login
            }
        }

        binding.itemFollowersAndFollowing.text =
            getString(R.string.userdetails_format_followers_and_following).format(
                user.followers ?: 0,
                user.following ?: 0
            )

        user.company?.takeIf { it.isNotBlank() }?.let { company ->
            with(binding.itemCompany) {
                text = company
                isVisible = true
            }
        }

        user.location?.takeIf { it.isNotBlank() }?.let { location ->
            with(binding.itemLocation) {
                text = location
                isVisible = true
            }
        }

        user.email?.takeIf { it.isNotBlank() }?.let { email ->
            with(binding.itemEmail) {
                text = email
                isVisible = true
            }
        }

        user.blog?.takeIf { it.isNotBlank() }?.let { blog ->
            with(binding.itemBlog) {
                text = blog
                isVisible = true
            }
        }

        user.bio?.takeIf { it.isNotBlank() }?.let { bio ->
            binding.containerBio.isVisible = true
            binding.itemBio.text = bio
        }
    }

    private fun showErrorState() {
        binding.progressBar.isVisible = false
        binding.viewInBrowserIcon.isVisible = false
        binding.avatar.isVisible = false
        binding.name.isVisible = false
        binding.userDetailsScrollView.isVisible = false
        binding.errorStateContainer.isVisible = true
    }
}
