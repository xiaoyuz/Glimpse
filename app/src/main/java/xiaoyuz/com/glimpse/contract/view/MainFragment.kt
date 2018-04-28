package xiaoyuz.com.glimpse.contract.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*
import xiaoyuz.com.glimpse.R
import xiaoyuz.com.glimpse.contract.MainContract
import xiaoyuz.com.glimpse.model.FeedResponse
import xiaoyuz.com.glimpse.ui.FeedAdapter

class MainFragment : Fragment(), MainContract.View, SwipeRefreshLayout.OnRefreshListener {

    override lateinit var presenter: MainContract.Presenter
    private val mFeeds: MutableList<FeedResponse> = mutableListOf()

    private var mCurrentStartId: String = ""
    private var mIsLoading: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipe_refresh_layout.setOnRefreshListener(this)
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = FeedAdapter(mFeeds)
        list.adapter.notifyDataSetChanged()
        list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition
                        = (list.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (!mIsLoading && !swipe_refresh_layout.isRefreshing
                        && lastVisibleItemPosition == list.adapter.itemCount - 1) {
                    mIsLoading = true
                    presenter.loadFeeds(mCurrentStartId)
                }
            }
        })
        presenter.loadFeeds(mCurrentStartId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun showFeeds(feedsResultPair: Pair<List<FeedResponse>, String>) {
        mFeeds.addAll(feedsResultPair.first)
        mCurrentStartId = feedsResultPair.second
        list.adapter.notifyDataSetChanged()
        swipe_refresh_layout.isRefreshing = false
        mIsLoading = false
    }

    override fun onRefresh() {
        swipe_refresh_layout.isRefreshing = true
        mFeeds.clear()
        mCurrentStartId = ""
        mIsLoading = true
        presenter.loadFeeds(mCurrentStartId)
    }
}