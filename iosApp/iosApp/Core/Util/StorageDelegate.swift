//
//  StorageDelegate.swift
//  iosApp
//
//  Created by velkonost on 25.09.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import FirebaseCore
import FirebaseAuth
import FirebaseStorage

enum UploadState: Equatable {
    case Idle
    case Loading(Int)
    case Success(String)
    case Failure(String?)
}

class StorageDelegate {
    let storage = Storage.storage()
    let userId = Auth.auth().currentUser?.uid
    
    @Binding private var uploadState: UploadState
    
    init(uploadState: Binding<UploadState>) {
        self._uploadState = uploadState
    }
    
    
    func uploadAvatar(file: Data, onSuccess: @escaping (String) -> Void) {
        if userId == nil {
            self.uploadState = UploadState.Failure(nil)
            return
        }
        
        let avatarRef = storage.reference().child("avatars/\(userId!)")
        
        let metadata = StorageMetadata()
        metadata.contentType = "image/png"
        
        let uploadTask = avatarRef.putData(file, metadata: metadata) { (metadata, error) in
            guard let metadata = metadata else {
                self.uploadState = UploadState.Failure(nil)
                return
            }
            
            let size = metadata.size
            
            avatarRef.downloadURL { (url, error) in
                guard let downloadUrl = url else {
                    self.uploadState = UploadState.Failure(nil)
                    return
                }
                self.uploadState = UploadState.Success(downloadUrl.absoluteString)
                onSuccess(downloadUrl.absoluteString)
            }
        }
        
        uploadTask.observe(.progress) { snapshot in
            let percentComplete = 100.0 * Double(snapshot.progress!.completedUnitCount) / Double(snapshot.progress!.totalUnitCount)
            self.uploadState = UploadState.Loading(Int(percentComplete))
        }
        
        
    }
}
