package xiaoyuz.com.glimpse.db.source

import xiaoyuz.com.glimpse.net.RetrofitManager

object RemoteDataSource : DataSource {

    override fun getFeed(startId: String) = RetrofitManager.service.getFeed()
}