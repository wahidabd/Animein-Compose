package com.wahidabd.animein.ui.components.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wahidabd.animein.screen.search.SearchViewModel
import com.wahidabd.animein.ui.theme.ColorPrimary
import com.wahidabd.library.utils.common.emptyString
import kotlinx.coroutines.delay


/**
 * Created by Wahid on 7/17/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun AnimeSearchBar(
    autoFocus: Boolean,
    onSearch: () -> Unit,
    onBackClick: () -> Unit,
    viewModel: SearchViewModel
) {
    var searchInput by remember { mutableStateOf("") }
    var focusManager = LocalFocusManager.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(key1 = searchInput) {
        if (viewModel.searchParam.value.trim()
                .isNotEmpty() && viewModel.searchParam.value.trim().length != viewModel.previousSearch.value.trim().length
        ) {
            delay(550)
            onSearch()
            viewModel.previousSearch.value = searchInput.trim()
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(ColorPrimary)
            .padding(start = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RoundedIcon(
            icon = Icons.Default.KeyboardArrowLeft,
            background = ColorPrimary,
            navigator = onBackClick,
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color = Color.White)
                .fillMaxWidth()
                .height(38.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .align(Alignment.CenterVertically),
                value = searchInput,
                onValueChange = { value ->
                    searchInput = if (value.trim().isNotEmpty()) value else emptyString()
                    viewModel.searchParam.value = searchInput
                },
                textStyle = TextStyle.Default.copy(fontSize = 12.sp),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = if (searchInput.isEmpty()) Arrangement.SpaceBetween else Arrangement.End
                    ) {
                        if (searchInput.isEmpty()) {
                            Text(
                                text = "Search..",
                                color = Color.DarkGray,
                                fontSize = 12.sp
                            )
                        }

                        Icon(
                            imageVector = Icons.Outlined.Search,
                            tint = ColorPrimary,
                            modifier = Modifier.size(20.dp),
                            contentDescription = "search"
                        )
                    }
                    innerTextField.invoke()
                },
                singleLine = true
            )
        }
    }
}
