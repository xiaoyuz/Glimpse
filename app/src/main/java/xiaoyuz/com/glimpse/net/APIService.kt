package xiaoyuz.com.glimpse.net

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    @GET("feed")
    fun getFeed(): Call<JsonObject>
}