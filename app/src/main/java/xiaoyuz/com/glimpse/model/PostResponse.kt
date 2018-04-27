package xiaoyuz.com.glimpse.model

data class PostResponse(var feed: FeedResponse? = null,
                        var videos: List<VideoResponse> = emptyList())