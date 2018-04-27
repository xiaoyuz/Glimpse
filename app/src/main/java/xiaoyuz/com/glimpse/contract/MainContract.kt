package xiaoyuz.com.glimpse.contract

import xiaoyuz.com.glimpse.base.BasePresenter
import xiaoyuz.com.glimpse.base.BaseView
import xiaoyuz.com.glimpse.model.FeedResponse

interface MainContract {

    interface View : BaseView<Presenter> {

        fun showFeeds(feeds: List<FeedResponse>)
    }

    interface Presenter : BasePresenter {

        fun loadFeeds(startId: String)
    }
}