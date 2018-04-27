package xiaoyuz.com.glimpse.contract.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*
import xiaoyuz.com.glimpse.R
import xiaoyuz.com.glimpse.contract.MainContract
import xiaoyuz.com.glimpse.model.FeedResponse
import xiaoyuz.com.glimpse.ui.FeedAdapter

class MainFragment : Fragment(), MainContract.View {

    override lateinit var presenter: MainContract.Presenter
    private val mFeeds: MutableList<FeedResponse> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = FeedAdapter(mFeeds)
        list.adapter.notifyDataSetChanged()
        list.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun showFeeds(feeds: List<FeedResponse>) {
        mFeeds.addAll(feeds)
        list.adapter.notifyDataSetChanged()
    }
}