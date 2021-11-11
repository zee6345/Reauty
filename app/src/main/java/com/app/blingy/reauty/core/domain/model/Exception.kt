package com.app.blingy.reauty.core.domain.model

import java.io.IOException

/**
 * custom exception class
 */
class NetworkUnavailableException(
    message: String = "Network Unavailable, check your internet connection!"
) : IOException(message)

class RemoteNetworkException(message: String) : Exception(message)