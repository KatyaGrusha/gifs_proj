package com.dev.gifapp.ui.main

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dev.gifapp.R
import com.dev.gifapp.data.dataBase.entity.GifDbModel
import com.dev.gifapp.data.models.MediaModel
import com.dev.gifapp.data.models.abstracts.MediaAbstractModel
import com.dev.gifapp.databinding.CardGifBinding
import com.dev.gifapp.ui.base.BaseRecyclerViewAdapter
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController

class GifsListAdapter(private val itemClickListener: (MediaAbstractModel) -> Unit) :
    BaseRecyclerViewAdapter<MediaAbstractModel, GifsListAdapter.GifViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GifViewHolder(
        DataBindingUtil.inflate(
            getLayoutInflater(parent.context),
            R.layout.card_gif,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        if (data.size > position) {
            val gif = getItem(position)
            holder.bind(gif)
            holder.itemView.setOnClickListener {
                itemClickListener(gif)
            }
        }
    }

    override fun updateListItems(newItems: List<MediaAbstractModel>) {
        val diffCallback = GifDiffUtilCallback(data, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        clear()
        addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    fun addNextData(data: List<MediaAbstractModel>) {
        val curSize: Int = itemCount
        addAll(data)
        notifyItemRangeInserted(curSize, data.size);
    }

    inner class GifViewHolder(private val binding: CardGifBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: MediaAbstractModel) {
            when (model) {
                is MediaModel ->  binding.gifView.setMediaWithId(model.id)
                is GifDbModel -> {
                    val controller: DraweeController =
                        Fresco.newDraweeControllerBuilder().setUri(model.uri)
                            .setAutoPlayAnimations(true).build()
                    binding.gifView.controller = controller
                }
            }
            binding.gifView.setOnClickListener {
                itemClickListener(model)
            }
        }
    }
}