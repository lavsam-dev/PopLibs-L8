package lavsam.gb.libs.poplibs.poplibs_l8.mvp.view

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun oneUser(userName: String, userAvatarUrl: String): Screen
    fun settings() = Unit
}