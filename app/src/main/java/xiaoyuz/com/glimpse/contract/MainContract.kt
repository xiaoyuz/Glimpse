package xiaoyuz.com.glimpse.contract

import xiaoyuz.com.glimpse.base.BasePresenter
import xiaoyuz.com.glimpse.base.BaseView

interface MainContract {

    interface View : BaseView<Presenter> {
    }

    interface Presenter : BasePresenter {
    }
}