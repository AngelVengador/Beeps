package cl.t273.beepsandvibrations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cl.t273.beepsandvibrations.ui.theme.BeepsAndVibrationsTheme
import cl.t273.beepsandvibrations.ui.theme.Grey
import cl.t273.beepsandvibrations.ui.theme.Navy
import cl.t273.beepsandvibrations.views.SoundsScreen
import cl.t273.beepsandvibrations.views.VibrationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BeepsAndVibrationsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .consumeWindowInsets(innerPadding)
                    ) {
                        MainScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val selectedColor = Grey
    val unselectedColor = Grey.copy(alpha = 0.6f)
    val tabs = listOf("Sonidos \uD83C\uDFB5", "Vibración \uD83D\uDCF3")
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column {
        TabRow(
            selectedTabIndex = selectedTabIndex,
            backgroundColor = Navy,
            contentColor = selectedColor
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title, color = if (selectedTabIndex == index) selectedColor else unselectedColor) }
                )
            }
        }
        when (selectedTabIndex) {
            0 -> SoundsScreen()
            1 -> VibrationScreen()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    BeepsAndVibrationsTheme {
        MainScreen()
    }
}