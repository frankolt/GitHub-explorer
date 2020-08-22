package io.github.frankolt.githubexplorer.ui.repositorysearch.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.frankolt.githubexplorer.databinding.ViewRepositoryItemBinding
import io.github.frankolt.githubexplorer.domain.github.models.Repository

class RepositorySearchAdapter(
    initialData: List<Repository> = listOf()
) : RecyclerView.Adapter<RepositorySearchAdapter.ViewHolder>() {

    var data: List<Repository> = initialData
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onAvatarClickListener: ((String) -> Unit)? = null
    private var onRepositoryClickListener: ((String, String) -> Unit)? = null

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            parent.context,
            ViewRepositoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setOnAvatarClickListener(listener: (String) -> Unit) {
        onAvatarClickListener = listener
    }

    fun setOnRepositoryClickListener(listener: (String, String) -> Unit) {
        onRepositoryClickListener = listener
    }

    inner class ViewHolder(
        private val context: Context,
        private val binding: ViewRepositoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Repository) {
            // TODO: Handle cases where some fields are `null`.
            item.owner?.avatarUrl?.let {
                Glide.with(context).load(it).centerCrop().into(binding.repositoryOwnerThumbnail)
            }
            binding.repositoryOwnerThumbnail.setOnClickListener { onAvatarClickListener?.invoke(item.owner!!.login!!) }
            binding.repositoryName.text = item.fullName
            binding.repositoryName.setOnClickListener { onRepositoryClickListener?.invoke(item.owner!!.login!!, item.name!!) }
            binding.stars.text = item.stargazersCount.toString()
            binding.forks.text = item.forksCount.toString()
            binding.issues.text = item.openIssuesCount.toString()
        }
    }
}