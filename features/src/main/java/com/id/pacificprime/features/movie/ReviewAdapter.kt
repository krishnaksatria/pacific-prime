package com.id.pacificprime.features.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.id.pacificprime.commons.extensions.loadImage
import com.id.pacificprime.core.movie.data.reviews.MovieReviewModel
import com.id.pacificprime.features.R
import com.id.pacificprime.features.databinding.ItemReviewBinding
import timber.log.Timber

class ReviewAdapter :
    ListAdapter<MovieReviewModel, ReviewAdapter.ReviewViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding = ItemReviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class ReviewViewHolder(
        private val itemBinding: ItemReviewBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bindView(item: MovieReviewModel) {
            val image = itemView.context.getString(
                R.string.label_image_prefix,
                item.authorDetails.avatarPath
            )
            Timber.tag("testt").d(image)
            itemBinding.ivAuthor.loadImage(image)
            itemBinding.tvAuthorName.text = item.authorDetails.name
            itemBinding.rbAuthor.rating = item.authorDetails.rating
            itemBinding.tvContent.text = item.content
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<MovieReviewModel>() {
            override fun areItemsTheSame(
                oldItem: MovieReviewModel,
                newItem: MovieReviewModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieReviewModel,
                newItem: MovieReviewModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
