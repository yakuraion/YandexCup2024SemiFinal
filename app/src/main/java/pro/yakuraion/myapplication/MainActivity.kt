package pro.yakuraion.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import pro.yakuraion.myapplication.presentation.screens.drawing.DrawingScreen
import pro.yakuraion.myapplication.presentation.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                DrawingScreen()
            }
        }
    }
}
