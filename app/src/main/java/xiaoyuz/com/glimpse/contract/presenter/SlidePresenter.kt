package xiaoyuz.com.glimpse.contract.presenter

import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import xiaoyuz.com.glimpse.contract.SlideContract
import xiaoyuz.com.glimpse.db.source.DataSourceManager

class SlidePresenter(val dataSourceManager: DataSourceManager,
                     val slideView: SlideContract.View) : SlideContract.Presenter {

    init {
        slideView.presenter = this
    }

    override fun start() {
    }

    override fun loadMoreFeeds(startId: String) {
        dataSourceManager.getFeed(startId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    val response = result.response()?.body()
                    response?.let {
                        val startId = it.pageInfo.nextId
                        val hasMore = it.pageInfo.hasMore
                        val content = it.content
                        content?.let { slideView.addFeeds(it to startId) }
                    }
                },{

                })
    }
}