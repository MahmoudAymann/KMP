package com.mayman.kmp.movie.presentation.movies_list.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.collections.immutable.ImmutableList

data class TabItem(
    val title: String,
    val icon: ImageVector? = null,
)

@Composable
fun AppTabbedPager(
    tabs: ImmutableList<TabItem>,
    selectedIndex: Int,
    onTabSelected: (selectedIndex: Int) -> Unit,
    modifier: Modifier = Modifier,
    swipeEnabled: Boolean = true,
    content: @Composable (pageIndex: Int) -> Unit,
) {
    val pagerState = rememberPagerState(initialPage = selectedIndex) { tabs.size }

    // VM -> Pager (tab click, restored state)
    LaunchedEffect(selectedIndex) {
        if (pagerState.currentPage != selectedIndex) {
            pagerState.animateScrollToPage(selectedIndex)
        }
    }

    // Pager -> VM (user swipe)
    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.settledPage }
            .collect { page -> if (page != selectedIndex) onTabSelected(page) }
    }

    Column(modifier) {
        AppPrimaryTabRow(
            tabs = tabs,
            selectedIndex = selectedIndex,
            onTabSelected = onTabSelected,
        )
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = swipeEnabled,
            modifier = Modifier.weight(1f),
        ) { page ->
            content(page)
        }
    }
}

@Composable
private fun AppPrimaryTabRow(
    tabs: ImmutableList<TabItem>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    PrimaryTabRow(selectedTabIndex = selectedIndex, modifier = modifier) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = selectedIndex == index,
                onClick = { onTabSelected(index) },
                text = { Text(tab.title, maxLines = 1, overflow = TextOverflow.Ellipsis) },
                icon = tab.icon?.let { { Icon(it, contentDescription = null) } },
            )
        }
    }
}

@Preview
@Composable
fun AppPrimaryTabRowPreview() {

}