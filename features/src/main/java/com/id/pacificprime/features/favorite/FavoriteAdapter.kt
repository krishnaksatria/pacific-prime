package com.id.pacificprime.features.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.id.pacificprime.commons.extensions.loadImage
import com.id.pacificprime.features.R
import com.id.pacificprime.features.databinding.ItemFavoriteBinding
import com.id.pacificprime.room.data.FavoriteMovieData

class FavoriteAdapter :
    ListAdapter<FavoriteMovieData, FavoriteAdapter.FavoriteViewHolder>(COMPARATOR) {

    private var movieClickListener: ((FavoriteAdapter, FavoriteMovieData) -> Unit)? =
        null

    fun setOnMovieClickListener(listener: (FavoriteAdapter, FavoriteMovieData) -> Unit) {
        movieClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemFavoriteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return FavoriteViewHolder(binding) { _, position ->
            movieClickListener?.invoke(this, getItem(position))
        }
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class FavoriteViewHolder(
        private val itemBinding: ItemFavoriteBinding,
        listener: (FavoriteAdapter.FavoriteViewHolder, Int) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        init {
            itemView.setOnClickListener {
                listener.invoke(this, absoluteAdapterPosition)
            }
        }

        fun bindView(item: FavoriteMovieData) {
            val image = itemView.context.getString(R.string.label_image_prefix, item.image)
            itemBinding.imageMovie.loadImage(image)
            itemBinding.textMovieTitle.text = item.title
            itemBinding.textMovieDescription.text = item.description
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<FavoriteMovieData>() {
            override fun areItemsTheSame(
                oldItem: FavoriteMovieData,
                newItem: FavoriteMovieData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavoriteMovieData,
                newItem: FavoriteMovieData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
