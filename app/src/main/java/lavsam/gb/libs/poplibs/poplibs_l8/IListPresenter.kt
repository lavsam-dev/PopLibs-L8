package lavsam.gb.libs.poplibs.poplibs_l8

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V, GithubUser) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}