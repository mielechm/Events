package com.mielechm.events

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mielechm.events.ui.theme.EventsTheme
import com.mielechm.events.ui.views.TabLayout
import com.mielechm.events.ui.views.playback.PlaybackScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "events_list",
                    ) {

                        composable("events_list") {
                            TabLayout(navController = navController)
                        }
                        composable("playback_screen/{videoUrl}", arguments = listOf(
                            navArgument("videoUrl") {
                                type = NavType.StringType
                                nullable = true
                            }
                        )) {
                            val videoUrl: String? = remember {
                                it.arguments?.getString("videoUrl")
                            }
                            videoUrl?.let { url ->
                                PlaybackScreen(videoUrl = url)
                            }
                        }
                    }
                }
            }
        }
    }
}
