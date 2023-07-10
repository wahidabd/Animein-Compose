package com.wahidabd.animein.ui.components.bottomNavigation

import com.wahidabd.animein.R
import com.wahidabd.animein.ui.screen.destinations.BookmarkScreenDestination
import com.wahidabd.animein.ui.screen.destinations.Destination
import com.wahidabd.animein.ui.screen.destinations.HistoryScreenDestination
import com.wahidabd.animein.ui.screen.destinations.HomeScreenDestination
import com.wahidabd.animein.ui.screen.destinations.MoreScreenDestination


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

    object Bookmark : BottomNavItem(
        title = "Bookmark",
        icon = R.drawable.ic_bookmark,
        destination = BookmarkScreenDestination
    )

    object History : BottomNavItem(
        title = "History",
        icon = R.drawable.ic_history,
        destination = HistoryScreenDestination
    )

    object More : BottomNavItem(
        title = "More",
        icon = R.drawable.ic_more,
        destination = MoreScreenDestination
    )

}