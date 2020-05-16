package com.geekbrains.team.filmlibrary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.util.LoadState


class ProgressAdapter :
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.landscape_progress_item, parent, false)
        return ProgressViewHolder(view)
    }

    internal class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}

    private fun displayLoadStateAsItem(loadState: LoadState): Boolean {
        return loadState is LoadState.Loading || loadState is LoadState.Error
    }
}