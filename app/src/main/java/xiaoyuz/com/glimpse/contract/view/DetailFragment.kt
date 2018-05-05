package xiaoyuz.com.glimpse.contract.view

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_detail.*
import xiaoyuz.com.glimpse.R
import xiaoyuz.com.glimpse.contract.DetailContract
import xiaoyuz.com.glimpse.model.FeedResponse
import xiaoyuz.com.glimpse.model.PostResponse
import xiaoyuz.com.glimpse.model.VideoType
import xiaoyuz.com.glimpse.utils.RESOURCE_URL_HOST

class DetailFragment : Fragment(), DetailContract.View {

    override lateinit var presenter: DetailContract.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView.settings.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
            javaScriptEnabled = true
            loadsImagesAutomatically = true
            useWideViewPort = true
            loadWithOverviewMode = true
        }
        webView.webChromeClient = WebChromeClient()
        arguments?.let {
            Gson().fromJson<FeedResponse>(it.getString("feed"), FeedResponse::class.java)
        }?.let {
            presenter.loadPost(it)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?)
            = inflater.inflate(R.layout.fragment_detail, container, false)

    override fun showPostDetail(postResponse: PostResponse) {
        postResponse.feed?.let { title.text = it.title }
        val video = postResponse.videos.first()
        if (video.videoType != VideoType.IMAGE.name) {
            webView.loadUrl(RESOURCE_URL_HOST + video.core)
        }
    }
}