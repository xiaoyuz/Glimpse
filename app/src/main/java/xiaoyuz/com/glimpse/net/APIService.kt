package xiaoyuz.com.glimpse.net

import retrofit2.adapter.rxjava.Result
import retrofit2.http.GET
import rx.Observable
import xiaoyuz.com.glimpse.model.ApiResponse
import xiaoyuz.com.glimpse.model.FeedResponse

interface APIService {

    @GET("feed")
    fun getFeed(): Observable<Result<ApiResponse<List<FeedResponse>>>>
}