/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.devbyteviewer.repository

import com.example.android.devbyteviewer.database.VideosDatabase
import com.example.android.devbyteviewer.network.Network
import com.example.android.devbyteviewer.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// TODO (01) Create a VideosRepository class that takes a VideosDatabase argument.
class VideosRepository(private val database: VideosDatabase) {
    suspend fun refreshVideos() {
// TODO (02) Define a suspend refreshVideos() function that gets data from the network and
// inserts it into the database.
        withContext(Dispatchers.IO) {
            val playlist = Network.devbytes.getPlaylist().await()

// TODO (03) Define a Transformations.map  to convert the DatabaseVideo list to a list of Video.
            database.videoDao.insertAll(*playlist.asDatabaseModel())
        }
    }
}

