package xiaoyuz.com.glimpse.model

import com.google.gson.annotations.SerializedName

data class FeedResponse(var id: String, var title: String? = null, var description: String? = null,
                        var link: String? = null, var tag: String? = null,
                        var thumbnails: List<ThumbnailResponse> = emptyList(), var source: String? = null,
                        @SerializedName("media_type") var mediaType: String? = null,
                        @SerializedName("create_time") val createTime: Long? = null)