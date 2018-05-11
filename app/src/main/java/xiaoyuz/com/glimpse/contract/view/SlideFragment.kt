package xiaoyuz.com.glimpse.contract.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.jzvd.JZVideoPlayer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_slide.*
import xiaoyuz.com.glimpse.R
import xiaoyuz.com.glimpse.contract.SlideContract
import xiaoyuz.com.glimpse.contract.presenter.SlidePlayerPresenter
import xiaoyuz.com.glimpse.db.source.Injection
import xiaoyuz.com.glimpse.model.FeedResponse
import xiaoyuz.com.glimpse.ui.widget.SlidePlayer

class SlideFragment : Fragment(), SlideContract.View {

    override lateinit var presenter: SlideContract.Presenter

    private lateinit var mFeeds: MutableList<FeedResponse>
    private lateinit var mCurrentStartId: String
    private var mCurrentPos: Int = 0

    inner class SlideAdapter(private val mfeeds: List<FeedResponse>,
                       private val mContext: Context) : PagerAdapter() {

        private var mPlayerPresenter: SlidePlayerPresenter? = null

        override fun getCount() = mfeeds.size

        override fun isViewFromObject(view: View, obj: Any) = view == obj

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val pageView = LayoutInflater.from(mContext).inflate(R.layout.fragment_detail, null)
            val player = pageView.findViewById<SlidePlayer>(R.id.player)
            mPlayerPresenter = SlidePlayerPresenter(Injection.provideDataSource(), player)
                    .apply { loadPost(mfeeds[position]) }
            container.addView(pageView, 0)
            return pageView
        }

        override fun setPrimaryItem(container: ViewGroup, position: Int, obj: Any) {
            super.setPrimaryItem(container, position, obj)
            if (position >= mfeeds.size - 5) {
                presenter.loadMoreFeeds(mCurrentStartId)
            }
        }

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            (obj as View).findViewById<SlidePlayer>(R.id.player)?.release()
            mPlayerPresenter = null
            container.removeView(obj)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            mFeeds = Gson().fromJson<MutableList<FeedResponse>>(it.getString("feeds"),
                    object : TypeToken<MutableList<FeedResponse>>() {}.type)
            mCurrentStartId = it.getString("currentStartId")
            mCurrentPos = it.getInt("currentPos")
        }
        viewPager.adapter = SlideAdapter(mFeeds, context!!)
        viewPager.currentItem = mCurrentPos
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?)
            = inflater.inflate(R.layout.fragment_slide, container, false)

    override fun addFeeds(feedsResultPair: Pair<List<FeedResponse>, String>) {
        mFeeds.addAll(feedsResultPair.first)
        mCurrentStartId = feedsResultPair.second
        viewPager.adapter?.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
        JZVideoPlayer.releaseAllVideos()
    }
}