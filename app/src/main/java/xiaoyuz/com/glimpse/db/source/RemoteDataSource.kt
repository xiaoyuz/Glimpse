package xiaoyuz.com.glimpse.db.source

import xiaoyuz.com.glimpse.net.RetrofitManager

object RemoteDataSource : DataSource {

    override fun getFeed(startId: String) = RetrofitManager.service.getFeed(startId)

    override fun getPost(postId: String) = RetrofitManager.service.getPost(postId)
}