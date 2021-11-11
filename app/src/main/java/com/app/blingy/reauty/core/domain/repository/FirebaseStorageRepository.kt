package com.app.blingy.reauty.core.domain.repository

import com.app.blingy.reauty.core.util.ResultOf

interface FirebaseStorageRepository {

    suspend fun updateUSerProfilePic() : ResultOf<Boolean>
}