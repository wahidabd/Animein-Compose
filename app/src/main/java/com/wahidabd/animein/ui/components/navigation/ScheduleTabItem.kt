package com.wahidabd.animein.ui.components.navigation

import com.wahidabd.animein.screen.schedule.ScheduleTabScreen


/**
 * Created by Wahid on 7/16/2023.
 * Github github.com/wahidabd.
 */


data class ScheduleTabItem(
    val title: String,
    val endpoint: String
)

val scheduleTabs = listOf(
    ScheduleTabItem(
        title = "Sunday",
        endpoint = "schedule?scheduled_day=sunday"
    ),
    ScheduleTabItem(
        title = "Monday",
        endpoint = "schedule?scheduled_day=monday"
    ),
    ScheduleTabItem(
        title = "Tuesday",
        endpoint = "schedule?scheduled_day=tuesday"
    ),
    ScheduleTabItem(
        title = "Wednesday",
        endpoint = "schedule?scheduled_day=wednesday"
    ),
    ScheduleTabItem(
        title = "Thursday",
        endpoint = "schedule?scheduled_day=thursday"
    ),
    ScheduleTabItem(
        title = "Friday",
        endpoint = "schedule?scheduled_day=friday"
    ),
    ScheduleTabItem(
        title = "Saturday",
        endpoint = "schedule?scheduled_day=saturday"
    ),
    ScheduleTabItem(
        title = "Random",
        endpoint = "schedule?scheduled_day=random"
    )
)