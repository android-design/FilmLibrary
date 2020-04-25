package com.geekbrains.team.filmlibrary.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.team.filmlibrary.R
import com.geekbrains.team.filmlibrary.databinding.SmallActorCardItemBinding
import com.geekbrains.team.filmlibrary.model.ActorView

class SmallActorCardAdapter: RecyclerView.Adapter<SmallActorCardAdapter.SmallActorCardHolder>() {

    private val actors = mutableListOf<ActorView>()

    fun setActor(actors: List<ActorView>) {
        this.actors.clear()
        this.actors.addAll(actors)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmallActorCardHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: SmallActorCardItemBinding = DataBindingUtil.inflate(inflater, R.layout.small_actor_card_item, parent, false)
        return SmallActorCardHolder(binding)
    }

    override fun getItemCount(): Int {
        return actors.size
    }

    override fun onBindViewHolder(holder: SmallActorCardHolder, position: Int) {
        holder.bindActor(actors[position])
    }

    inner class SmallActorCardHolder(val binding: SmallActorCardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindActor(actor: ActorView) {
            binding.actor = actor
            binding.executePendingBindings()
        }

    }
}