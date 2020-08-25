package io.github.frankolt.githubexplorer.ui.repositorysearch.adapters

import android.content.Context
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.frankolt.githubexplorer.databinding.ItemPaginationStateBinding
import io.github.frankolt.githubexplorer.databinding.ItemRepositoryBinding
import io.github.frankolt.githubexplorer.domain.github.models.Repository
import io.github.frankolt.githubexplorer.ui.repositorysearch.state.PaginationState

sealed class RepositorySearchViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    class RepositoryItemViewHolder(
        private val context: Context,
        private val binding: ItemRepositoryBinding
    ) : RepositorySearchViewHolder(binding.root) {

        fun bind(
            item: Repository,
            onAvatarClickListener: ((String) -> Unit)?,
            onRepositoryClickListener: ((String, String) -> Unit)?
        ) {
            binding.root.setOnClickListener {
                onRepositoryClickListener?.invoke(item.owner.login, item.name)
            }
            item.owner.avatarUrl?.let {
                Glide.with(context).load(it).centerCrop().into(binding.repositoryOwnerThumbnail)
            }
            binding.repositoryOwnerThumbnail.setOnClickListener {
                onAvatarClickListener?.invoke(item.owner.login)
            }
            binding.repositoryName.text = item.fullName
            binding.stars.text = (item.stargazersCount ?: 0).toString()
            binding.forks.text = (item.forksCount ?: 0).toString()
            binding.issues.text = (item.openIssuesCount ?: 0).toString()
        }
    }

    class PaginationStateItemViewHolder(
        private val binding: ItemPaginationStateBinding
    ) : RepositorySearchViewHolder(binding.root) {

        fun bind(item: PaginationState, onRetryClickListener: (() -> Unit)?) {
            when (item) {
                is PaginationState.None -> binding.root.isVisible = false
                is PaginationState.Loading -> {
                    binding.root.isVisible = true
                    binding.progressBar.isVisible = true
                    binding.errorStateContainer.isVisible = false
                }
                is PaginationState.Error -> {
                    binding.root.isVisible = true
                    binding.progressBar.isVisible = false
                    binding.errorStateContainer.isVisible = true
                    binding.retryButton.setOnClickListener { onRetryClickListener?.invoke() }
                }
            }
        }
    }
}
