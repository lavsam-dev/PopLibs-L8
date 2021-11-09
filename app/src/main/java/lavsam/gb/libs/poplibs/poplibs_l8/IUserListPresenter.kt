package lavsam.gb.libs.poplibs.poplibs_l8

interface IUserListPresenter : IListPresenter<UserItemView> {

    fun getUserByPosition(position: Int): GithubUser
}