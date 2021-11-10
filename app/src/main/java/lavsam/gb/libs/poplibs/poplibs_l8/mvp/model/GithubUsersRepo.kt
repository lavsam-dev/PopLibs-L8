package lavsam.gb.libs.poplibs.poplibs_l8.mvp.model

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import lavsam.gb.libs.poplibs.poplibs_l8.User
import lavsam.gb.libs.poplibs.poplibs_l8.UserDao

class GithubUsersRepo(
    val client: GitHub,
    val db: UserDao
) {

    private val bs = BehaviorSubject.create<Unit>()

    fun subscribeOnGithubUsersData(): Observable<List<GithubUser>> {
        return bs
            .observeOn(Schedulers.io())
            .switchMap {
                Observable.combineLatest(
                    Observable.just(db.getAll().mapToUser()),
                    client.loadUsers().onErrorReturn { listOf() }.toObservable(),
                    { fromDatabase, fromNetwork ->
                        if (fromNetwork.isEmpty()) {
                            return@combineLatest fromDatabase
                        } else {
                            saveUsersToDB(fromNetwork)
                            fromNetwork
                        }
                    }
                )
            }
    }

    fun loadUserData() {
        bs.onNext(Unit)
    }

    private fun saveUsersToDB(list: List<GithubUser>) {
        db.insertAll(
            *(list.map {
                User(uid = it.id, firstName = it.login, lastName = null)
            }.toTypedArray())
        )
    }

    private fun List<User>.mapToUser(): List<GithubUser> {
        return this.map { GithubUser(login = it.firstName.orEmpty(), id = it.uid) }
    }
}
