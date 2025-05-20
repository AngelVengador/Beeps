package cl.t273.beepsandvibrations.views

import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.t273.beepsandvibrations.ui.theme.BeepsAndVibrationsTheme

data class VibeOption(val name: String, val toneConstant: Int)
val vibeOptions = listOf(
    VibeOption("DEFAULT_AMPLITUDE", VibrationEffect.DEFAULT_AMPLITUDE),
   // VibeOption("EFFECT_CLICK", VibrationEffect.EFFECT_CLICK),
    VibeOption("EFFECT_DOUBLE_CLICK", VibrationEffect.EFFECT_DOUBLE_CLICK),
    VibeOption("EFFECT_HEAVY_CLICK", VibrationEffect.EFFECT_HEAVY_CLICK),
    VibeOption("EFFECT_TICK", VibrationEffect.EFFECT_TICK),
)

@Composable
fun VibrationScreen() {
    val context = LocalContext.current
    var durationMs by remember { mutableFloatStateOf(150f) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Text("Duración: ${durationMs.toInt()} ms")
        Slider(
            value = durationMs,
            onValueChange = { durationMs = it },
            valueRange = 50f..1000f,
            steps = 10
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn {
            items(vibeOptions) { tone ->
                androidx.compose.material3.ListItem(
                    headlineContent = { Text(tone.name) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            vibrate(context, tone.toneConstant, durationMs.toLong())
                        }
                        .padding(16.dp)
                )
                HorizontalDivider()
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun VibrationPreview() {
    BeepsAndVibrationsTheme {
        VibrationScreen()
    }
}

fun vibrate(context: Context, effect: Int, duration: Long) {
    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager =
            context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(VIBRATOR_SERVICE) as Vibrator
    }
    vibrator.vibrate(
        VibrationEffect.createOneShot(duration, effect)
    )
}
