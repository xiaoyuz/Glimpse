package xiaoyuz.com.glimpse.contract

import xiaoyuz.com.glimpse.base.BasePresenter
import xiaoyuz.com.glimpse.base.BaseView
import xiaoyuz.com.glimpse.model.FeedResponse

interface SlideContract {

    interface View : BaseView<Presenter> {

        fun addFeeds(feedsResultPair: Pair<List<FeedResponse>, String>)
    }

    interface Presenter : BasePresenter {

        fun loadMoreFeeds(startId: String)
    }
}