package xiaoyuz.com.glimpse.net

import retrofit2.adapter.rxjava.Result
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable
import xiaoyuz.com.glimpse.model.ApiResponse
import xiaoyuz.com.glimpse.model.FeedResponse
import xiaoyuz.com.glimpse.model.PostResponse

interface APIService {

    @GET("feed")
    fun getFeed(@Query("start_id") startId: String): Observable<Result<ApiResponse<List<FeedResponse>>>>

    @GET("post/{postId}")
    fun getPost(@Path("postId") postId: String): Observable<Result<ApiResponse<PostResponse>>>
}