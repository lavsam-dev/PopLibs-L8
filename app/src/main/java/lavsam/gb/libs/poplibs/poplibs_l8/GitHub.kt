package lavsam.gb.libs.poplibs.poplibs_l8

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface GitHub {

    @GET("users")
    fun loadUsers(): Single<List<GithubUser>>
}