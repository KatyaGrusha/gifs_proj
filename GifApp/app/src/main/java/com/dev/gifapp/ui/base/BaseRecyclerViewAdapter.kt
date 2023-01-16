package com.dev.gifapp.ui.base

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<TData : Any, VIEW_HOLDER : RecyclerView.ViewHolder>(
    items: List<TData> = listOf()
) : RecyclerView.Adapter<VIEW_HOLDER>() {

    protected val data: MutableList<TData> = items.toMutableList()
    private lateinit var layoutInflater: LayoutInflater

    protected fun getLayoutInflater(context: Context): LayoutInflater =
        if (::layoutInflater.isInitialized) {
            layoutInflater
        } else {
            LayoutInflater.from(context).apply {
                layoutInflater = this
            }
        }

    override fun getItemCount() = data.size

    @Throws(ArrayIndexOutOfBoundsException::class)
    fun getItem(position: Int): TData = data[position]

    fun isEmpty() = data.isEmpty()

    fun isNotEmpty() = data.isNotEmpty()

    fun add(item: TData) = data.add(item)

    fun replace(
        oldPosition: Int,
        newPosition: Int
    ) = data.add(newPosition, removeByPosition(oldPosition))

    fun remove(item: TData) = data.remove(item)

    fun removeByPosition(position: Int): TData = data.removeAt(position)

    fun clear() {
        data.clear()
    }

    fun addAll(collection: Collection<TData>) = data.addAll(collection)

    fun getItemPosition(item: TData) = data.indexOf(item)

    fun insert(item: TData, position: Int) {
        data.add(position, item)
    }

    fun insertAll(items: Collection<TData>, position: Int) =
        data.addAll(position, items)

    abstract fun updateListItems(newItems: List<TData>)
}
