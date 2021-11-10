package lavsam.gb.libs.poplibs.poplibs_l8.mvp.presenter

import lavsam.gb.libs.poplibs.poplibs_l8.mvp.model.GithubUser
import lavsam.gb.libs.poplibs.poplibs_l8.mvp.view.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V, GithubUser) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}