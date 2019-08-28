package com.github.jakaarl.mands.server

import java.net.URL
import java.util.UUID

data class Play(
    val id: UUID,
    val title: String,
    val scenes: List<Scene> = listOf()
)

data class Scene(
    val title: String?,
    val backgrounds: List<Background> = listOf(),
    val tracks: List<Track> = listOf()
)

enum class DisplayMode {
    STRETCH,
    CENTER
}

data class Background(
    val href: URL,
    val width: UShort?,
    val height: UShort?,
    val displayMode: DisplayMode = DisplayMode.STRETCH
)

enum class AudioType {
    MP3,
    OGG,
    WAV
}

data class Track(
    val title: String,
    val href: URL,
    val type: AudioType
)
