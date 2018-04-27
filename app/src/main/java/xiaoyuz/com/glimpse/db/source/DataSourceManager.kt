package xiaoyuz.com.glimpse.db.source

import rx.Observable

class DataSourceManager(val localDataSource: LocalDataSource,
                        val remoteDataSource: RemoteDataSource) : DataSource {

    companion object {
        private var INSTANCE: DataSourceManager? = null

        @JvmStatic fun getInstance(localDataSource: LocalDataSource,
                                   remoteDataSource: RemoteDataSource)
                = INSTANCE ?: DataSourceManager(localDataSource, remoteDataSource)
                .apply { INSTANCE = this }

        @JvmStatic fun destroyInstance() {
            INSTANCE = null
        }
    }

    override fun getFeed(startId: String) = Observable.concat(localDataSource.getFeed(startId),
            remoteDataSource.getFeed(startId)).first()
}