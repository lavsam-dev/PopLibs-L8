package lavsam.gb.libs.poplibs.poplibs_l8.mvp.presenter

import android.util.Log
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import lavsam.gb.libs.poplibs.poplibs_l8.*
import lavsam.gb.libs.poplibs.poplibs_l8.mvp.model.GithubUser
import lavsam.gb.libs.poplibs.poplibs_l8.mvp.model.GithubUsersRepo
import lavsam.gb.libs.poplibs.poplibs_l8.mvp.view.IScreens
import lavsam.gb.libs.poplibs.poplibs_l8.mvp.view.UserItemView
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter @Inject constructor(
    private val usersRepo: GithubUsersRepo,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UsersView>() {

    init {
        App.instance.appComponent.inject(this)
    }


    class UsersListPresenter : IUserListPresenter {

        val users = (1..20).map { GithubUser("login $it", 0) }.toMutableList()

        override fun getUserByPosition(position: Int): GithubUser {
            return users[position]
        }

        override var itemClickListener: ((UserItemView, GithubUser) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    private val disposable = usersRepo.subscribeOnGithubUsersData()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
            usersListPresenter.users.clear()
            usersListPresenter.users.addAll(it)
            viewState.updateList()
        }, {
            Log.e("GB_LIBS", it.message, it)
        })

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        usersRepo.loadUserData()
        usersListPresenter.itemClickListener = { _, user ->
            router.navigateTo(
                screens.oneUser(
                    userAvatarUrl = user.avatarUrl,
                    userName = user.login
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}