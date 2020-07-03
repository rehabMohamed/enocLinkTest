package com.coolshop.codechallenge.enoclink.profile

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import com.coolshop.codechallenge.enoclink.EnocLinkApp.Companion.appComponent
import com.coolshop.codechallenge.enoclink.R
import com.coolshop.codechallenge.enoclink.base.view.BaseActivity
import com.coolshop.codechallenge.enoclink.data.LoggedInUser
import com.coolshop.codechallenge.enoclink.profile.di.ProfileModule
import com.coolshop.codechallenge.enoclink.utils.loadImage
import com.github.dhaval2404.imagepicker.ImagePicker
import kotlinx.android.synthetic.main.activity_profile.*
import javax.inject.Inject


class ProfileActivity : BaseActivity(), ProfileView {

    @Inject
    lateinit var presenter: ProfilePresenter<ProfileView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        presenter.apply {
            onAttach(this@ProfileActivity)
            getUserProfile()
        }

        avatar.setOnClickListener {
            changeAvatar()
        }
    }

    private fun changeAvatar() {
        ImagePicker.with(this)
                .compress(MAX_AVATAR_SIZE)
                .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {

            val filePath = ImagePicker.getFilePath(data)

            filePath?.let {
                val image = BitmapFactory.decodeFile(it)
                // apply grayscale filter
                val grayScaleImage = presenter.applyGrayScaleFilter(image)
                loadImage(this, grayScaleImage, avatar)
                presenter.uploadAvatar(grayScaleImage)
            }
        }
    }

    override fun performDI() {
        appComponent.profileComponent(ProfileModule()).inject(this)
    }

    override fun updateProfile(user: LoggedInUser?) {
        user?.let {
            email.text = it.email
            password.text = it.password
            loadImage(this, it.avatarUrl, avatar, R.drawable.profile)
        }
    }

    override fun showAvatarUpdated() {
        showToast(R.string.update_avatar_success)
    }

    override fun showAvatarUploadFailed() {
        showToast(R.string.update_avatar_failed)
    }

    override fun showError(error: String?) {
        showToast(error)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    companion object {
        // 1024 * 3/4 base64 encoding represents each 3 bits in 4 chars
        private val MAX_AVATAR_SIZE = 768
    }
}
