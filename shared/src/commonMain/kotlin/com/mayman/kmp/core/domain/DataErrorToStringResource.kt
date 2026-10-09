package com.mayman.kmp.core.domain

import com.mayman.kmp.movie.domain.DataError
import com.mayman.kmp.core.presentation.UiText
import kmp.shared.generated.resources.Res
import kmp.shared.generated.resources.error_disk_full
import kmp.shared.generated.resources.error_no_internet
import kmp.shared.generated.resources.error_request_timeout
import kmp.shared.generated.resources.error_serialization
import kmp.shared.generated.resources.error_server
import kmp.shared.generated.resources.error_too_many_request
import kmp.shared.generated.resources.error_unknown

//
// Created by Mahmoud Ayman Mostafa on 09/10/2026.
//


fun DataError.toUiText(): UiText {
    val stringRes = when(this){
        DataError.Local.DISK_FULL -> Res.string.error_disk_full
        DataError.Local.UNKNOWN -> Res.string.error_unknown
        DataError.Remote.REQUEST_TIMEOUT -> Res.string.error_request_timeout
        DataError.Remote.TOO_MANY_REQUEST -> Res.string.error_too_many_request
        DataError.Remote.NO_INTERNET -> Res.string.error_no_internet
        DataError.Remote.SERVER_ERROR -> Res.string.error_server
        DataError.Remote.SERIALIZATION -> Res.string.error_serialization
        DataError.Remote.UNKNOWN -> Res.string.error_unknown
    }
    return UiText.StringResourceId(stringRes)
}