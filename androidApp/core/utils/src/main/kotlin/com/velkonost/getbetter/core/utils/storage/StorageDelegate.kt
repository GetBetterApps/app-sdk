package com.velkonost.getbetter.core.utils.storage

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class StorageDelegate(private val storage: FirebaseStorage = Firebase.storage) {

    sealed class UploadState {
        data class Loading(val progress: Int) : UploadState()
        data class Success(val fileUrl: String) : UploadState()
        data class Failure(val exception: Exception? = null) : UploadState()

        data object Idle : UploadState()
    }

    fun uploadAvatar(file: Uri) =
        callbackFlow {
            val userId = dev.gitlive.firebase.Firebase.auth.currentUser?.uid

            if (userId == null) {
                trySend(UploadState.Failure())
                close()
            }

            val avatarRef = storage.reference.child("avatars/${userId}")
            avatarRef.putFile(file)
                .addOnProgressListener {
                    val progress = (100.0 * it.bytesTransferred) / it.totalByteCount
                    trySend(UploadState.Loading(progress.toInt()))
                }

                .continueWithTask { task ->
                    if (!task.isSuccessful) {
                        trySend(UploadState.Failure(task.exception))
                        close(task.exception)
                    }
                    avatarRef.downloadUrl
                }
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val fileUrl = task.result.toString()
                        trySend(UploadState.Success(fileUrl))
                        close()
                    } else {
                        trySend(UploadState.Failure())
                        close()
                    }
                }
            awaitClose()
        }

}
