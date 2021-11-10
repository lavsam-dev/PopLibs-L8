package lavsam.gb.libs.poplibs.poplibs_l8.mvp.presenter

import lavsam.gb.libs.poplibs.poplibs_l8.mvp.model.GithubUser
import lavsam.gb.libs.poplibs.poplibs_l8.mvp.view.UserItemView

interface IUserListPresenter : IListPresenter<UserItemView> {

    fun getUserByPosition(position: Int): GithubUser
}