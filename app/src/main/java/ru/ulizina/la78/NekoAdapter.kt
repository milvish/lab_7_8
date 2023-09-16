package ru.ulizina.la78

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.ulizina.la78.databinding.NekoItemBinding
import ru.ulizina.la78.retrofit.Neko
import ru.ulizina.la78.retrofit.NekoResponse

class NekoAdapter: ListAdapter<Neko, NekoAdapter.NekoHolder>(Comparator()) {

    class NekoHolder (view: View): RecyclerView.ViewHolder(view){
        private val binding = NekoItemBinding.bind(view)
        fun bind(neko: Neko) {
            val context = itemView.context
            Glide.with(context)
                .asGif()
                .load(neko.url)
                .into(binding.animeImage1)
            binding.animeName.text = neko.anime_name
        }
    }

    class Comparator: DiffUtil.ItemCallback<Neko>(){
        override fun areItemsTheSame(oldItem: Neko, newItem: Neko): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Neko, newItem: Neko): Boolean {
            return oldItem.url == newItem.url
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NekoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.neko_item, parent, false)
        return NekoHolder(view)
    }

    override fun onBindViewHolder(holder: NekoHolder, position: Int) {
        holder.bind(getItem(position))
    }

}