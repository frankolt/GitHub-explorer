package io.github.frankolt.githubexplorer.ui.repositorysearch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.frankolt.githubexplorer.databinding.ItemPaginationStateBinding
import io.github.frankolt.githubexplorer.databinding.ItemRepositoryBinding
import io.github.frankolt.githubexplorer.domain.github.models.Repository
import io.github.frankolt.githubexplorer.ui.repositorysearch.state.PaginationState

class RepositorySearchAdapter(
    initialData: List<Repository> = listOf()
) : RecyclerView.Adapter<RepositorySearchViewHolder>() {

    var data: List<Repository> = initialData
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var paginationState: PaginationState = PaginationState.None
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onAvatarClickListener: ((String) -> Unit)? = null
    private var onRepositoryClickListener: ((String, String) -> Unit)? = null
    private var onRetryClickListener: (() -> Unit)? = null

    override fun getItemCount() = data.size + 1 // Additional item for showing the pagination state.

    override fun getItemViewType(position: Int) = if (position == data.size) {
        VIEW_TYPE_PAGINATION_STATE_ITEM
    } else {
        VIEW_TYPE_REPOSITORY_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        VIEW_TYPE_REPOSITORY_ITEM -> RepositorySearchViewHolder.RepositoryItemViewHolder(
            parent.context,
            ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        VIEW_TYPE_PAGINATION_STATE_ITEM -> RepositorySearchViewHolder.PaginationStateItemViewHolder(
            ItemPaginationStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        else -> throw IllegalStateException("View type not supported.")
    }

    override fun onBindViewHolder(holder: RepositorySearchViewHolder, position: Int) {
        when (holder) {
            is RepositorySearchViewHolder.RepositoryItemViewHolder -> holder.bind(
                data[position],
                onAvatarClickListener,
                onRepositoryClickListener
            )
            is RepositorySearchViewHolder.PaginationStateItemViewHolder -> holder.bind(
                paginationState,
                onRetryClickListener
            )
        }
    }

    fun setOnAvatarClickListener(listener: (String) -> Unit) {
        onAvatarClickListener = listener
    }

    fun setOnRepositoryClickListener(listener: (String, String) -> Unit) {
        onRepositoryClickListener = listener
    }

    fun setOnRetryClickListener(listener: () -> Unit) {
        onRetryClickListener = listener
    }
}
