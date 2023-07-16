package com.wahidabd.animein.ui.components.navigation

import com.wahidabd.animein.R
import com.wahidabd.animein.screen.destinations.BookmarkScreenDestination
import com.wahidabd.animein.screen.destinations.Destination
import com.wahidabd.animein.screen.destinations.HomeScreenDestination
import com.wahidabd.animein.screen.destinations.MoreScreenDestination
import com.wahidabd.animein.screen.destinations.ScheduleScreenDestination


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */


sealed class BottomNavItem(val title: String, val icon: Int, val destination: Destination) {

    object Home : BottomNavItem(
        title = "Home",
        icon = R.drawable.ic_home,
        destination = HomeScreenDestination
    )

    object Schedule : BottomNavItem(
        title = "Schedule",
        icon = R.drawable.ic_schedule,
        destination = ScheduleScreenDestination
    )

    object Bookmark : BottomNavItem(
        title = "Bookmark",
        icon = R.drawable.ic_bookmark,
        destination = BookmarkScreenDestination
    )


    object More : BottomNavItem(
        title = "More",
        icon = R.drawable.ic_more,
        destination = MoreScreenDestination
    )

}