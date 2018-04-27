package xiaoyuz.com.glimpse.db.source

import retrofit2.adapter.rxjava.Result
import rx.Observable
import xiaoyuz.com.glimpse.model.ApiResponse
import xiaoyuz.com.glimpse.model.FeedResponse

interface DataSource {

    fun getFeed(startId: String): Observable<Result<ApiResponse<List<FeedResponse>>>> = Observable.empty()
}