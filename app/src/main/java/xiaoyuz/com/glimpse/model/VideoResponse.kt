package xiaoyuz.com.glimpse.model

import com.google.gson.annotations.SerializedName

data class VideoResponse(var id: String, var title: String? = null, var description: String? = null,
                         var link: String? = null, var duration: Int? = null,
                         var width: Int? = null, var height: Int? = null,
                         @SerializedName("video_type") var videoType: String? = null,
                         @SerializedName("source_type") var sourceType: String? = null,
                         var thumbnails: List<ThumbnailResponse> = emptyList(),
                         @SerializedName("create_time") val createTime: Long? = null,
                         var core: String)