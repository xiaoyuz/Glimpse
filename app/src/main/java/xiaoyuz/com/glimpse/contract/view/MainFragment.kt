package xiaoyuz.com.glimpse.contract.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.JsonObject
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import xiaoyuz.com.glimpse.R
import xiaoyuz.com.glimpse.contract.MainContract
import xiaoyuz.com.glimpse.net.RetrofitManager

class MainFragment : Fragment(), MainContract.View {

    override lateinit var presenter: MainContract.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val call = RetrofitManager.service.getFeed()
        call.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
                val o = response
            }

            override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {

            }
        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?)
            = inflater.inflate(R.layout.fragment_main, container, false)
}