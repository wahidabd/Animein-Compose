package com.wahidabd.animeku.ui.screen.more

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.wahidabd.animeku.R
import com.wahidabd.animeku.ui.component.IconTextItem
import com.wahidabd.animeku.ui.component.more.AccountLogin
import com.wahidabd.animeku.utils.rememberForeverLazyListState


/**
 * Created by wahid on 11/15/2023.
 * Github github.com/wahidabd.
 */


@Destination
@Composable
fun MoreScreen(
    navigator: DestinationsNavigator
) {

    val state = rememberForeverLazyListState(key = "MoreScreen")
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .scrollable(
                state = state,
                orientation = Orientation.Vertical
            )
    ) {
        AccountLogin(navigator = navigator)
        Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(12.dp))
        IconTextItem(
            icon = R.drawable.ic_history,
            text = context.getString(R.string.label_history),
            onClick = {}
        )
    }
}

@Preview
@Composable
fun MoreScreenPreview() {
    MoreScreen(navigator = EmptyDestinationsNavigator)
}