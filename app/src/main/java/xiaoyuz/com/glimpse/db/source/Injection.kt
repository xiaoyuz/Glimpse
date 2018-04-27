package xiaoyuz.com.glimpse.db.source

object Injection {
    fun provideDataSource() = DataSourceManager.getInstance(LocalDataSource, RemoteDataSource)
}