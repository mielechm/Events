package com.mielechm.events.ui.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.SportsScore
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.SportsScore
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.mielechm.events.ui.views.eventslist.EventsListScreen
import com.mielechm.events.ui.views.schedule.ScheduleScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayout(navController: NavController) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabItems = listOf(
        TabItem(
            title = "Events",
            unselectedIcon = Icons.Outlined.SportsScore,
            selectedIcon = Icons.Filled.SportsScore
        ),
        TabItem(
            title = "Schedule",
            unselectedIcon = Icons.Outlined.CalendarMonth,
            selectedIcon = Icons.Filled.CalendarMonth
        )
    )

    val pagerState = rememberPagerState {
        tabItems.size
    }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {index ->
            Column(modifier = Modifier.fillMaxSize()) {
                if (index == 0) {
                    EventsListScreen(navController = navController)
                } else {
                    ScheduleScreen(navController = navController)
                }
            }

        }
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabItems.forEachIndexed { index, tab ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = tab.title) },
                    icon = {
                        Icon(
                            imageVector = if (index == selectedTabIndex)
                                tab.selectedIcon else
                                tab.unselectedIcon,
                            contentDescription = tab.title
                        )
                    }


                )
            }
        }
    }
}

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)