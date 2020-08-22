package io.github.frankolt.githubexplorer.ui.repositorysearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.frankolt.githubexplorer.databinding.FragmentRepositorySearchBinding
import io.github.frankolt.githubexplorer.ui.extensions.update
import io.github.frankolt.githubexplorer.ui.repositorysearch.adapters.RepositorySearchAdapter
import io.github.frankolt.githubexplorer.ui.repositorysearch.events.RepositorySearchEvent

@AndroidEntryPoint
class RepositorySearchFragment : Fragment() {

    private var _binding: FragmentRepositorySearchBinding? = null

    /**
     * This property is only valid after the call to `onCreateView`, but before the call to
     * `onDestroyView`.
     */
    private val binding
        get() = _binding!!

    private val viewModel: RepositorySearchViewModel by viewModels()

    private val eventsObserver = Observer<RepositorySearchEvent> {
        when (it) {
            is RepositorySearchEvent.OpenUserDetails -> findNavController().navigate(
                RepositorySearchFragmentDirections
                    .actionRepositorySearchFragmentToUserDetailsFragment(it.username)
            )
            is RepositorySearchEvent.OpenRepositoryDetails -> findNavController().navigate(
                RepositorySearchFragmentDirections
                    .actionRepositorySearchFragmentToRepositoryDetailsFragment(it.owner, it.repo)
            )
        }
    }

    private val searchAdapter = RepositorySearchAdapter().apply {
        setOnAvatarClickListener { viewModel.openUserDetails(it) }
        setOnRepositoryClickListener { owner, repo -> viewModel.openRepositoryDetails(owner, repo) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.searchTimber()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositorySearchBinding.inflate(inflater, container, false)
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
        viewModel.query.observe(viewLifecycleOwner) { binding.queryInputField.update(it) }
        viewModel.searchResultItems.observe(viewLifecycleOwner) { searchAdapter.data = it }
        viewModel.events.observe(viewLifecycleOwner, eventsObserver)
    }

    private fun setupUi() {
        binding.queryInputField.addTextChangedListener { viewModel.search(it.toString()) }
        binding.queryResultList.layoutManager = LinearLayoutManager(context)
        binding.queryResultList.adapter = searchAdapter
    }
}
