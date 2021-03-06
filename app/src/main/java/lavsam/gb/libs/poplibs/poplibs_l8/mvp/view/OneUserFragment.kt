package lavsam.gb.libs.poplibs.poplibs_l8.mvp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import lavsam.gb.libs.poplibs.poplibs_l8.App
import lavsam.gb.libs.poplibs.poplibs_l8.mvp.presenter.OneUserPresenter
import lavsam.gb.libs.poplibs.poplibs_l8.OneUserView
import lavsam.gb.libs.poplibs.poplibs_l8.R
import lavsam.gb.libs.poplibs.poplibs_l8.databinding.FragmentOneUserBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class OneUserFragment : MvpAppCompatFragment(), OneUserView, BackButtonListener {
    companion object {

        private const val USER_NAME = "userName"
        private const val USER_AVATAR_URL = "userAvatarUrl"

        fun newInstance(userName: String, userAvatarUrl: String) = OneUserFragment()
            .apply {
                arguments = Bundle().apply {
                    this.putString(USER_NAME, userName)
                    this.putString(USER_AVATAR_URL, userAvatarUrl)
                }
                App.instance.appComponent.inject(this)
            }
    }

    @Inject
    lateinit var somePresenter: OneUserPresenter

    private val presenter: OneUserPresenter by moxyPresenter {
        somePresenter
    }

    private var vb: FragmentOneUserBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): ConstraintLayout {
        this.arguments?.run {
            presenter.updateUserInfo(
                this.getString(USER_NAME).orEmpty(),
                this.getString(USER_AVATAR_URL).orEmpty()
            )
        }
        return FragmentOneUserBinding.inflate(inflater, container, false).also {
            vb = it
        }.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init() {
    }

    override fun updateUserData(userName: String, userAvatarUrl: String) {
        vb?.apply {
            Glide.with(this.image)
                .load(userAvatarUrl)
                .placeholder(R.drawable.progger00_300)
                .into(this.image)
            this.userName.text = userName
        }

    }

    override fun backPressed() = presenter.backPressed()
}
