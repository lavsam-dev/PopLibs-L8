package lavsam.gb.libs.poplibs.poplibs_l8.mvp.view

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users(): Screen {
        return FragmentScreen { UsersFragment.newInstance() }
    }

    override fun oneUser(userName: String, userAvatarUrl: String): Screen {
        return FragmentScreen { OneUserFragment.newInstance(userName, userAvatarUrl) }
    }
}