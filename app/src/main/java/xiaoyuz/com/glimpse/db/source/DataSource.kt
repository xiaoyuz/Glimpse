package xiaoyuz.com.glimpse.db.source

import retrofit2.adapter.rxjava.Result
import rx.Observable
import xiaoyuz.com.glimpse.model.ApiResponse
import xiaoyuz.com.glimpse.model.FeedResponse
import xiaoyuz.com.glimpse.model.PostResponse

interface DataSource {

    fun getFeed(startId: String): Observable<Result<ApiResponse<List<FeedResponse>>>> = Observable.empty()

    fun getPost(postId: String): Observable<Result<ApiResponse<PostResponse>>> = Observable.empty()
}