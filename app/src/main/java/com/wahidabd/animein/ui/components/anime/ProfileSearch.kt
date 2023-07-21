package com.wahidabd.animein.ui.components.anime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.wahidabd.animein.screen.destinations.SearchScreenDestination
import com.wahidabd.animein.screen.destinations.VideoPlayerScreenDestination
import com.wahidabd.animein.screen.player.PlayerActivity
import com.wahidabd.animein.ui.components.utils.RoundedIcon
import com.wahidabd.animein.ui.theme.ColorOnPrimary
import com.wahidabd.animein.ui.theme.ColorPrimary


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun ProfileSearch(
    navigator: DestinationsNavigator
) {

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(top = 12.dp, bottom = 4.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RoundedIcon(iconTint = ColorOnPrimary, icon = Icons.Default.Person, background = ColorPrimary){
//            navigator.navigate(VideoPlayerScreenDestination)
//            PlayerActivity.start(context)
        }
        RoundedIcon(iconTint = ColorOnPrimary, icon = Icons.Outlined.Search, background = ColorPrimary){
            navigator.navigate(SearchScreenDestination)
        }
    }
}

@Preview
@Composable
fun ProfileSearchPreview() {
    ProfileSearch(EmptyDestinationsNavigator)
}