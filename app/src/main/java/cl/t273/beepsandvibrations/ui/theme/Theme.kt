package cl.t273.beepsandvibrations.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Grey,
    secondary = Cream,
    background = Navy,
    surface = NavyDark,
    onPrimary = Navy,
    onSecondary = Navy,
    onBackground = Cream,
    inversePrimary = NavyDark,
    onSurface = Grey
)

@Composable
fun BeepsAndVibrationsTheme(
    darkTheme: Boolean = true, // o usa isSystemInDarkTheme()
    content: @Composable () -> Unit
) {

    val colorScheme = DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
