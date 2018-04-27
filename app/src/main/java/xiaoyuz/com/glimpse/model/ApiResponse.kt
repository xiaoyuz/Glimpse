package xiaoyuz.com.glimpse.model

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(var result: Result = Result(),
                          @SerializedName("page_info") var pageInfo: PageInfo = PageInfo(),
                          var content: T? = null,
                          @SerializedName("request_id") var requestId: String? = null) {

    data class Result(var message: String = "success", var code: Int = 0)

    data class PageInfo(@SerializedName("has_more") var hasMore: Boolean = false,
                        @SerializedName("next_id") var nextId: String = "",
                        @SerializedName("total_count") var totalCount: Int = 0)
}