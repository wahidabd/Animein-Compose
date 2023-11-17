package com.wahidabd.animeku.ui.component.navigation

import com.wahidabd.animeku.R
import com.wahidabd.animeku.ui.screen.destinations.BookmarkScreenDestination
import com.wahidabd.animeku.ui.screen.destinations.Destination
import com.wahidabd.animeku.ui.screen.destinations.HomeScreenDestination
import com.wahidabd.animeku.ui.screen.destinations.MoreScreenDestination
import com.wahidabd.animeku.ui.screen.destinations.SearchScreenDestination
import com.wahidabd.animeku.utils.Constants.BOOKMARK
import com.wahidabd.animeku.utils.Constants.HOME
import com.wahidabd.animeku.utils.Constants.MORE
import com.wahidabd.animeku.utils.Constants.SEARCH


/**
 * Created by wahid on 11/15/2023.
 * Github github.com/wahidabd.
 */


sealed class NavigationItem(val title: String, val icon: Int, val destination: Destination){

    object Home: NavigationItem(
        title = HOME,
        icon = R.drawable.ic_home,
        destination = HomeScreenDestination
    )

    object Search: NavigationItem(
        title = SEARCH,
        icon = R.drawable.ic_search,
        destination = SearchScreenDestination
    )

    object Bookmark: NavigationItem(
        title = BOOKMARK,
        icon = R.drawable.ic_bookmark,
        destination = BookmarkScreenDestination
    )

    object More: NavigationItem(
        title = MORE,
        icon = R.drawable.ic_more,
        destination = MoreScreenDestination
    )
}