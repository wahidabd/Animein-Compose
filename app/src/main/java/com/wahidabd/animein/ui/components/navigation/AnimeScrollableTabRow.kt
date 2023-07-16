package com.wahidabd.animein.ui.components.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRowDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wahidabd.animein.ui.theme.ColorDarkGray
import com.wahidabd.animein.ui.theme.ColorOrange
import com.wahidabd.animein.ui.theme.ColorPrimary


/**
 * Created by Wahid on 7/16/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun AnimeScrollableTabRow(
    tabs: List<ScheduleTabItem>,
    selectedTabIndex: Int,
    onTabClick: (Int) -> Unit
) {
    val density = LocalDensity.current
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }

    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        contentColor = Color.White,
        backgroundColor = ColorPrimary,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabIndicatorOffset(
                    currentTabPosition = tabPositions[selectedTabIndex],
                    tabWidth = tabWidths[selectedTabIndex]
                )
            )
        }
    ) {
        tabs.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabClick(index) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.DarkGray,
                text = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 12.sp,
                        color = Color.White,
                        onTextLayout = { textLayoutResult ->
                            tabWidths[index] = with(density) {
                                textLayoutResult.size.width.toDp()
                            }
                        }
                    )
                }
            )
        }
    }
}

fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition,
    tabWidth: Dp
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "customTabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = tabWidth,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    val indicatorOffset by animateDpAsState(
        targetValue = ((currentTabPosition.left + currentTabPosition.right - tabWidth) / 2),
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}