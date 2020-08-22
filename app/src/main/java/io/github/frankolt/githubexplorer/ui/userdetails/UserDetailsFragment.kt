package io.github.frankolt.githubexplorer.ui.userdetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import io.github.frankolt.githubexplorer.R
import io.github.frankolt.githubexplorer.databinding.FragmentUserDetailsBinding
import io.github.frankolt.githubexplorer.ui.userdetails.events.UserDetailsEvent
import io.github.frankolt.githubexplorer.ui.userdetails.state.UserDetails

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

    private val userDetailsObserver = Observer<UserDetails> {
        // TODO: What happens if `avatarUrl` is `null`?
        it.avatarUrl?.let { avatarUrl ->
            Glide.with(this).load(avatarUrl).centerCrop().into(binding.avatar)
        }
        if (it.name != null) {
            // TODO: What happens if `login` is `null`? It probably shouldn't ever be `null`,
            //  though, so perhaps it shouldn't be nullable.
            it.login?.let { login ->
                binding.name.text =
                    getString(R.string.userdetails_format_username_and_name).format(login, it.name)
            }
        } else {
            // TODO: What happens if `login` is `null`? It probably shouldn't ever be `null`,
            //  though, so perhaps it shouldn't be nullable.
            it.login?.let { login ->
                binding.name.text = login
            }
        }
        binding.itemFollowersAndFollowing.text =
            getString(R.string.userdetails_format_followers_and_following).format(
                it.followersAndFollowing.first,
                it.followersAndFollowing.second
            )
        it.company?.let { company ->
            with(binding.itemCompany) {
                text = company
                visibility = View.VISIBLE
            }
        }
        it.location?.let { location ->
            with(binding.itemLocation) {
                text = location
                visibility = View.VISIBLE
            }
        }
        it.email?.let { email ->
            with(binding.itemEmail) {
                text = email
                visibility = View.VISIBLE
            }
        }
        it.blog?.let { blog ->
            with(binding.itemBlog) {
                text = blog
                visibility = View.VISIBLE
            }
        }
        it.bio?.let { bio ->
            binding.containerBio.visibility = View.VISIBLE
            binding.itemBio.text = bio
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
        viewModel.userDetails.observe(viewLifecycleOwner, userDetailsObserver)
        viewModel.events.observe(viewLifecycleOwner, eventObserver)
    }

    private fun setupUi() {
        binding.backNavigationArrow.setOnClickListener { findNavController().navigateUp() }
        binding.viewInBrowserIcon.setOnClickListener { viewModel.openInBrowser() }
    }
}
