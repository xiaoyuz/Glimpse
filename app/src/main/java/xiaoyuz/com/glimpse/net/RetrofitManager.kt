package xiaoyuz.com.glimpse.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import xiaoyuz.com.glimpse.engine.GlimpseApplication
import java.util.concurrent.TimeUnit

object RetrofitManager {
    private val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS).addInterceptor {
                it.proceed(it.request().newBuilder()
                        .header("Device-Id", GlimpseApplication.DEVICE_ID).build())
            }.build()

    private val retrofit = Retrofit.Builder().client(okHttpClient)
            .baseUrl("http://47.75.66.123:18182/content/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build()

    val service = retrofit.create(APIService::class.java)
}