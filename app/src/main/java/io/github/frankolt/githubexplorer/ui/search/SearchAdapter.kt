package io.github.frankolt.githubexplorer.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.frankolt.githubexplorer.databinding.ViewSearchResultItemBinding
import io.github.frankolt.githubexplorer.domain.github.models.Repository

class SearchAdapter(
    initialData: List<Repository> = listOf()
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var data: List<Repository> = initialData
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        parent.context,
        ViewSearchResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ViewHolder(
        private val context: Context,
        private val binding: ViewSearchResultItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Repository) {
            // TODO: Handle cases where some fields are `null`.
            item.owner?.avatarUrl?.let {
                Glide.with(context).load(it).centerCrop().into(binding.repositoryOwnerThumbnail)
            }
            binding.repositoryName.text = item.fullName
            binding.watchers.text = item.watchersCount.toString()
            binding.forks.text = item.forksCount.toString()
            binding.issues.text = item.openIssuesCount.toString()
        }
    }
}