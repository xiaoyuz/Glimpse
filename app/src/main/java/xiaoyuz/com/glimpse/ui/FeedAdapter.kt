package xiaoyuz.com.glimpse.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_feed.view.*
import xiaoyuz.com.glimpse.R
import xiaoyuz.com.glimpse.model.FeedResponse
import xiaoyuz.com.glimpse.utils.Configs

class FeedAdapter(private val mfeeds: List<FeedResponse>) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    inner class FeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = FeedViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_feed, parent, false))

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.itemView.title.text = mfeeds[position].title
        Glide.with(holder.itemView.context).load(mfeeds[position].thumbnails.first().url)
                .apply(Configs.glideRequestOptions).into(holder.itemView.thumbnail)
    }

    override fun getItemCount() = mfeeds.size

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}