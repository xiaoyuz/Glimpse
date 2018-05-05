package xiaoyuz.com.glimpse.contract.presenter

import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import xiaoyuz.com.glimpse.contract.DetailContract
import xiaoyuz.com.glimpse.db.source.DataSourceManager
import xiaoyuz.com.glimpse.model.FeedResponse

class DetailPresenter(val dataSourceManager: DataSourceManager,
                      val detailView: DetailContract.View) : DetailContract.Presenter {

    init {
        detailView.presenter = this
    }

    override fun start() {
    }

    override fun loadPost(feedResponse: FeedResponse) {
        dataSourceManager.getPost(feedResponse.id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    val response = result.response()?.body()
                    response?.let {
                        val content = it.content
                        content?.let { detailView.showPostDetail(it) }
                    }
                },{

                })
    }
}