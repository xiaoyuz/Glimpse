package xiaoyuz.com.glimpse.ui.widget

import android.content.Context
import android.util.AttributeSet
import cn.jzvd.JZVideoPlayerStandard
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import xiaoyuz.com.glimpse.R
import xiaoyuz.com.glimpse.contract.SlidePlayerContract
import xiaoyuz.com.glimpse.model.PostResponse
import xiaoyuz.com.glimpse.model.VideoType
import xiaoyuz.com.glimpse.utils.RESOURCE_URL_HOST

class SlidePlayer(context: Context, attr: AttributeSet) : JZVideoPlayerStandard(context, attr),
        SlidePlayerContract.View {

    override lateinit var presenter: SlidePlayerContract.Presenter

    override fun getLayoutId() = R.layout.player_layout

    override fun showPostDetail(postResponse: PostResponse) {
        val video = postResponse.videos.first()
        if (video.videoType != VideoType.IMAGE.name) {
            setUp(RESOURCE_URL_HOST + video.core,
                    JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, video.title)
            Glide.with(context)
                    .load(RESOURCE_URL_HOST + video.thumbnails.first().url)
                    .asBitmap()
                    .priority(Priority.HIGH)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(thumbImageView)
        }
    }

    override fun startWindowFullscreen() {
    }
}