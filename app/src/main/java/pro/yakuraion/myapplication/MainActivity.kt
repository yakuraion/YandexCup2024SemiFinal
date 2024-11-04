package pro.yakuraion.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.geometry.Size
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import pro.yakuraion.myapplication.presentation.screens.drawing.DrawingScreen
import pro.yakuraion.myapplication.presentation.screens.drawing.DrawingViewModel
import pro.yakuraion.myapplication.presentation.screens.sizedetector.SizeDetectorScreen
import pro.yakuraion.myapplication.presentation.theme.MyApplicationTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "size_detector") {
                    composable(route = "size_detector") {
                        SizeDetectorScreen(
                            onSizeDetected = { size ->
                                navController.navigate("drawing/${size.width.roundToInt()}/${size.height.roundToInt()}") {
                                    popUpTo("size_detector") { inclusive = true }
                                }
                            }
                        )
                    }
                    composable(route = "drawing/{width}/{height}") { entry ->
                        val width = entry.arguments?.getString("width")!!.toFloat()
                        val height = entry.arguments?.getString("height")!!.toFloat()
                        val viewModel = getViewModel<DrawingViewModel>(
                            parameters = { parametersOf(Size(width, height)) }
                        )
                        DrawingScreen(viewModel)
                    }
                }
            }
        }
    }
}
