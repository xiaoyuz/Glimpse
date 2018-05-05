package xiaoyuz.com.glimpse.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.item_feed.view.*
import xiaoyuz.com.glimpse.R
import xiaoyuz.com.glimpse.contract.MainContract
import xiaoyuz.com.glimpse.model.FeedResponse
import xiaoyuz.com.glimpse.utils.RESOURCE_URL_HOST

class FeedAdapter(private val mfeeds: List<FeedResponse>,
                  private val mPresenter: MainContract.Presenter) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    inner class FeedViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            view.layout.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            mPresenter.openDetail(v.tag as FeedResponse)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = FeedViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_feed, parent, false))

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
                .load(RESOURCE_URL_HOST + mfeeds[position].thumbnails.first().url)
                .asBitmap()
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.itemView.thumbnail)
//        val params = holder.itemView.thumbnail.layoutParams
//        val vw = holder.itemView.thumbnail.width - holder.itemView
//                .thumbnail.paddingLeft - holder.itemView.thumbnail.paddingRight
//        val d = mfeeds[position].thumbnails.first().let {
//            it.width!!.toDouble() / it.height!!
//        }
//        val vh = (vw.toDouble() / d).toInt()
//        params.height = vh + holder.itemView.thumbnail.paddingTop + holder
//                .itemView.thumbnail.paddingBottom
//        holder.itemView.thumbnail.layoutParams = params
//        holder.itemView.minimumHeight = params.height

        holder.itemView.title.text = mfeeds[position].title
        holder.itemView.tag = mfeeds[position]
    }

    override fun getItemCount() = mfeeds.size

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}