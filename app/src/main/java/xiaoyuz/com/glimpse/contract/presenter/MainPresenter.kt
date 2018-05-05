package xiaoyuz.com.glimpse.contract.presenter

import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import xiaoyuz.com.glimpse.contract.MainContract
import xiaoyuz.com.glimpse.db.source.DataSourceManager
import xiaoyuz.com.glimpse.model.FeedResponse

class MainPresenter(val dataSourceManager: DataSourceManager,
                    val mainView: MainContract.View) : MainContract.Presenter {

    init {
        mainView.presenter = this
    }

    override fun start() {
    }

    override fun loadFeeds(startId: String) {
        dataSourceManager.getFeed(startId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    val response = result.response()?.body()
                    response?.let {
                        val startId = it.pageInfo.nextId
                        val hasMore = it.pageInfo.hasMore
                        val content = it.content
                        content?.let { mainView.showFeeds(it to startId) }
                    }
                },{

                })
    }

    override fun openDetail(item: FeedResponse) {
        mainView.displayDetail(item)
    }
}