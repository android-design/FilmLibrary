package com.geekbrains.team.filmlibrary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.util.LoadState
import kotlinx.android.synthetic.main.landscape_progress_item.view.*


class ProgressAdapter(val onRetryClickListener: (() -> Unit)? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var loadState: LoadState = LoadState.Done
        set(loadState) {
            if (field != loadState) {
                val displayOldItem = displayLoadStateAsItem(field)
                val displayNewItem = displayLoadStateAsItem(loadState)

                if (displayOldItem && !displayNewItem) {
                    notifyItemRemoved(0)
                } else if (displayNewItem && !displayOldItem) {
                    notifyItemInserted(0)
                } else if (displayOldItem && displayNewItem) {
                    notifyItemChanged(0)
                }
                field = loadState
            }
        }

    override fun getItemViewType(position: Int): Int = 0

    override fun getItemCount(): Int = if (displayLoadStateAsItem(loadState)) 1 else 0

    private fun displayLoadStateAsItem(loadState: LoadState) =
        loadState is LoadState.Loading || loadState is LoadState.Error

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.landscape_progress_item, parent, false)
        return ProgressViewHolder(view)
    }

    inner class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            itemView.retry_button.setOnClickListener {
                onRetryClickListener?.invoke()

                itemVisibility(itemView, true)
            }

            when (loadState) {
                is LoadState.Loading -> itemVisibility(itemView, true)
                else -> itemVisibility(itemView, false)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ProgressViewHolder)?.bind()
    }

    private fun itemVisibility(itemView: View, showPB: Boolean) {
        if (showPB) {
            itemView.retry_button.visibility = View.INVISIBLE
            itemView.progressBar.visibility = View.VISIBLE
        } else {
            itemView.retry_button.visibility = View.VISIBLE
            itemView.progressBar.visibility = View.INVISIBLE
        }
    }
}