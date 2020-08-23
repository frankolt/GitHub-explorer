package io.github.frankolt.githubexplorer.ui.repositorydetails

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
import io.github.frankolt.githubexplorer.databinding.FragmentRepositoryDetailsBinding
import io.github.frankolt.githubexplorer.domain.github.models.Repository
import io.github.frankolt.githubexplorer.ui.repositorydetails.events.RepositoryDetailsEvent
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class RepositoryDetailsFragment : Fragment() {

    private var _binding: FragmentRepositoryDetailsBinding? = null

    /**
     * This property is only valid after the call to `onCreateView`, but before the call to
     * `onDestroyView`.
     */
    private val binding
        get() = _binding!!

    private val args: RepositoryDetailsFragmentArgs by navArgs()

    private val viewModel: RepositoryDetailsViewModel by viewModels()

    private val repositoryObserver = Observer<Repository> { repository ->
        repository.owner?.avatarUrl?.let { url ->
            Glide.with(this).load(url).centerCrop().into(binding.repositoryOwnerThumbnail)
        }

        repository.name?.let { name -> binding.name.text = name }

        val formatter = SimpleDateFormat("LLLL dd, yyyy", Locale.US)
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
        val formattedCreatedAt = repository.createdAt?.let { createdAt ->
            parser.parse(createdAt)?.let { formatter.format(it) }
        }
        val formattedUpdatedAt = repository.updatedAt?.let { updatedAt ->
            parser.parse(updatedAt)?.let { formatter.format(it) }
        }
        if (formattedCreatedAt != null && formattedUpdatedAt != null) {
            with(binding.itemCreatedAtAndUpdatedAt) {
                text =
                    getString(R.string.repositorydetails_format_created_at_and_updated_at).format(
                        formattedCreatedAt,
                        formattedUpdatedAt
                    )
                isVisible = true
            }
        } else if (formattedCreatedAt != null) {
            with(binding.itemCreatedAtAndUpdatedAt) {
                text = getString(R.string.repositorydetails_format_created_at).format(
                    formattedCreatedAt
                )
                isVisible = true
            }
        } else if (formattedUpdatedAt != null) {
            with(binding.itemCreatedAtAndUpdatedAt) {
                text = getString(R.string.repositorydetails_format_updated_at).format(
                    formattedUpdatedAt
                )
                isVisible = true
            }
        }

        repository.language?.let { language ->
            with(binding.itemLanguage) {
                text = getString(R.string.repositorydetails_format_language).format(language)
                isVisible = true
            }
        }

        repository.stargazersCount?.let { count ->
            with(binding.itemStars) {
                text = getString(R.string.repositorydetails_format_stars).format(count)
                isVisible = true
            }
        }

        repository.subscribersCount?.let { count ->
            with(binding.itemWatchers) {
                text = getString(R.string.repositorydetails_format_watchers).format(count)
                isVisible = true
            }
        }

        repository.forksCount?.let { count ->
            with(binding.itemForks) {
                text = getString(R.string.repositorydetails_format_forks).format(count)
                isVisible = true
            }
        }

        repository.openIssuesCount?.let { count ->
            with(binding.itemIssues) {
                text = getString(R.string.repositorydetails_format_issues).format(count)
                isVisible = true
            }
        }

        repository.license?.name?.let { license ->
            with(binding.itemLicense) {
                text = license
                isVisible = true
            }
        }

        repository.organization?.name?.let { organization ->
            with(binding.itemOrganization) {
                text = organization
                isVisible = true
            }
        }

        repository.description?.let { description ->
            binding.containerDescription.isVisible = true
            binding.itemDescription.text = description
        }
    }

    private val eventObserver = Observer<RepositoryDetailsEvent> {
        when (it) {
            is RepositoryDetailsEvent.OpenInBrowser -> startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(it.url))
            )
            is RepositoryDetailsEvent.OpenUserDetails -> findNavController().navigate(
                RepositoryDetailsFragmentDirections
                    .actionRepositoryDetailsFragmentToUserDetailsFragment(it.username)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getRepositoryDetails(args.owner, args.repo)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryDetailsBinding.inflate(inflater, container, false)
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
        viewModel.repository.observe(viewLifecycleOwner, repositoryObserver)
        viewModel.events.observe(viewLifecycleOwner, eventObserver)
    }

    private fun setupUi() {
        binding.backNavigationArrow.setOnClickListener { findNavController().navigateUp() }
        binding.viewInBrowserIcon.setOnClickListener { viewModel.openInBrowser() }
        binding.repositoryOwnerThumbnail.setOnClickListener { viewModel.openUserDetails() }
    }
}
