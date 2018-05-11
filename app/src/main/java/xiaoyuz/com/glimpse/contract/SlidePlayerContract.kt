package xiaoyuz.com.glimpse.contract

import xiaoyuz.com.glimpse.base.BasePresenter
import xiaoyuz.com.glimpse.base.BaseView
import xiaoyuz.com.glimpse.model.FeedResponse
import xiaoyuz.com.glimpse.model.PostResponse

interface SlidePlayerContract {

    interface View : BaseView<Presenter> {

        fun showPostDetail(postResponse: PostResponse)
    }

    interface Presenter : BasePresenter {

        fun loadPost(feedResponse: FeedResponse)
    }
}