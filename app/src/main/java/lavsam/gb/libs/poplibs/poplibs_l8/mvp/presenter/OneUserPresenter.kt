package lavsam.gb.libs.poplibs.poplibs_l8.mvp.presenter

import com.github.terrakok.cicerone.Router
import lavsam.gb.libs.poplibs.poplibs_l8.App
import lavsam.gb.libs.poplibs.poplibs_l8.OneUserView
import lavsam.gb.libs.poplibs.poplibs_l8.mvp.view.IScreens
import moxy.MvpPresenter
import javax.inject.Inject

class OneUserPresenter @Inject constructor(
    private val router: Router,
    private val screens: IScreens,
) :
    MvpPresenter<OneUserView>() {

    init {
        App.instance.appComponent.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        router.backTo(screen = screens.users())
        return true
    }

    fun updateUserInfo(userName: String, userAvatarUrl: String) {
        viewState.updateUserData(userName, userAvatarUrl)
    }
}