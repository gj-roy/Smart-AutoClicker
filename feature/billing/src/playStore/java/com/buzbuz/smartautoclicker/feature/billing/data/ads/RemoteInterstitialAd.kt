/*
 * Copyright (C) 2024 Kevin Buzeau
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.buzbuz.smartautoclicker.feature.billing.data.ads

import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd

sealed class RemoteInterstitialAd {

    data object SdkNotInitialized : RemoteInterstitialAd()
    data object Initialized : RemoteInterstitialAd()
    data object Loading : RemoteInterstitialAd()
    data class NotShown(val ad: InterstitialAd) : RemoteInterstitialAd()
    data object Showing : RemoteInterstitialAd()
    data class Shown(val shownTimeMs: Long = System.currentTimeMillis()) : RemoteInterstitialAd()

    sealed class Error : RemoteInterstitialAd() {
        abstract val adError: AdError?

        data class LoadingError(override val adError: LoadAdError) : Error()
        data class ShowError(override val adError: AdError? = null) : Error()
    }

}