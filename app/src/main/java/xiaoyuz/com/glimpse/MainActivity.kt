package xiaoyuz.com.glimpse

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import xiaoyuz.com.glimpse.contract.presenter.MainPresenter
import xiaoyuz.com.glimpse.contract.view.MainFragment
import xiaoyuz.com.glimpse.db.source.Injection
import xiaoyuz.com.glimpse.utils.addFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainFragment().also {
            MainPresenter(Injection.provideDataSource(), it)
            addFragment(it)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        super.onKeyDown(keyCode, event)
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (supportFragmentManager.backStackEntryCount == 1) {
                finish()
            }
            return supportFragmentManager.backStackEntryCount >= 1
        }
        return false
    }
}
