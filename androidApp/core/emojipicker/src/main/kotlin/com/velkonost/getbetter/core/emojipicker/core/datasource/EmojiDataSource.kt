package com.velkonost.getbetter.core.emojipicker.core.datasource

import com.velkonost.getbetter.core.emojipicker.core.model.NetworkEmoji

interface EmojiDataSource {
    suspend fun getAllEmojis(): List<NetworkEmoji>
}
