/*
 * Nextcloud - Android Client
 *
 * SPDX-FileCopyrightText: 2025 Your Name <you@example.com>
 * SPDX-License-Identifier: AGPL-3.0-or-later OR GPL-2.0-only
 */
package com.owncloud.android.ui.preview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nextcloud.client.account.User
import com.owncloud.android.datamodel.OCFile
import com.owncloud.android.utils.MimeTypeUtil

/**
 * Minimal pager adapter for swiping through video files in PreviewMediaActivity.
 * Uses PreviewMediaFragment for each page (video playback).
 */
class VideoPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val videos: List<OCFile>,
    private val user: User
) : FragmentStateAdapter(fragmentActivity) {

    fun getFileAt(position: Int): OCFile? = videos.getOrNull(position)

    fun getFilePosition(file: OCFile): Int = videos.indexOf(file)

    override fun getItemCount(): Int = videos.size

    override fun createFragment(position: Int): Fragment {
        val file = getFileAt(position)
        return if (file != null && MimeTypeUtil.isVideo(file)) {
            PreviewMediaFragment.newInstance(file, user, 0, true, false)
        } else {
            PreviewImageErrorFragment.newInstance()
        }
    }
}


