package com.wahidabd.animeku.data.local.anime.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wahidabd.library.base.Model
import com.wahidabd.library.utils.common.emptyString


/**
 * Created by wahid on 11/25/2023.
 * Github github.com/wahidabd.
 */


@Entity(tableName = "anime")
data class AnimeEntity(
    @PrimaryKey
    @ColumnInfo("slug")
    val slug: String,

    @ColumnInfo("poster")
    val poster: String? = emptyString(),

    @ColumnInfo("title")
    val title: String? = emptyString(),

    @ColumnInfo("type")
    val type: String? = emptyString(),

    @ColumnInfo("rating")
    val rating: String? = emptyString(),

    @ColumnInfo("status")
    val status: String? = emptyString(),

    @ColumnInfo("total_episode")
    val totalEpisode: String? = emptyString(),

    @ColumnInfo("release_date")
    val releaseDate: String? = emptyString(),

    @ColumnInfo("studio")
    val studio: String? = emptyString(),

    @ColumnInfo("season")
    val season: String? = emptyString(),

    @ColumnInfo("duration")
    val duration: String? = emptyString(),

    @ColumnInfo("bookmark")
    val bookmark: Boolean? = false
) : Model()