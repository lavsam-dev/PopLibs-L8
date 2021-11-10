package lavsam.gb.libs.poplibs.poplibs_l8.mvp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String = ""
) : Parcelable