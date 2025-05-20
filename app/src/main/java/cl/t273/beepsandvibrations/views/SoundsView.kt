package cl.t273.beepsandvibrations.views

import android.media.AudioManager
import android.media.ToneGenerator
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
import androidx.compose.material3.ListItem
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.t273.beepsandvibrations.ui.theme.BeepsAndVibrationsTheme

data class ToneOption(val name: String, val toneConstant: Int)
val toneOptions = listOf(
    ToneOption("TONE_CDMA_ABBR_ALERT", ToneGenerator.TONE_CDMA_ABBR_ALERT),
    ToneOption("TONE_CDMA_ABBR_INTERCEPT", ToneGenerator.TONE_CDMA_ABBR_INTERCEPT),
    ToneOption("TONE_CDMA_ABBR_REORDER", ToneGenerator.TONE_CDMA_ABBR_REORDER),
    ToneOption("TONE_CDMA_ALERT_AUTOREDIAL_LITE", ToneGenerator.TONE_CDMA_ALERT_AUTOREDIAL_LITE),
    ToneOption("TONE_CDMA_ALERT_CALL_GUARD", ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD),
    ToneOption("TONE_CDMA_ALERT_INCALL_LITE", ToneGenerator.TONE_CDMA_ALERT_INCALL_LITE),
    ToneOption("TONE_CDMA_ALERT_NETWORK_LITE", ToneGenerator.TONE_CDMA_ALERT_NETWORK_LITE),
    ToneOption("TONE_CDMA_ANSWER", ToneGenerator.TONE_CDMA_ANSWER),
    ToneOption("TONE_CDMA_CALLDROP_LITE", ToneGenerator.TONE_CDMA_CALLDROP_LITE),
    ToneOption("TONE_CDMA_CALL_SIGNAL_ISDN_INTERGROUP", ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_INTERGROUP),
    ToneOption("TONE_CDMA_CALL_SIGNAL_ISDN_NORMAL", ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_NORMAL),
    ToneOption("TONE_CDMA_CALL_SIGNAL_ISDN_PAT3", ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_PAT3),
    ToneOption("TONE_CDMA_CALL_SIGNAL_ISDN_PAT5", ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_PAT5),
    ToneOption("TONE_CDMA_CALL_SIGNAL_ISDN_PAT6", ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_PAT6),
    ToneOption("TONE_CDMA_CALL_SIGNAL_ISDN_PAT7", ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_PAT7),
    ToneOption("TONE_CDMA_CALL_SIGNAL_ISDN_PING_RING", ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_PING_RING),
    ToneOption("TONE_CDMA_CALL_SIGNAL_ISDN_SP_PRI", ToneGenerator.TONE_CDMA_CALL_SIGNAL_ISDN_SP_PRI),
    ToneOption("TONE_CDMA_CONFIRM", ToneGenerator.TONE_CDMA_CONFIRM),
    ToneOption("TONE_CDMA_DIAL_TONE_LITE", ToneGenerator.TONE_CDMA_DIAL_TONE_LITE),
    ToneOption("TONE_CDMA_EMERGENCY_RINGBACK", ToneGenerator.TONE_CDMA_EMERGENCY_RINGBACK),
    ToneOption("TONE_CDMA_HIGH_L", ToneGenerator.TONE_CDMA_HIGH_L),
    ToneOption("TONE_CDMA_HIGH_PBX_L", ToneGenerator.TONE_CDMA_HIGH_PBX_L),
    ToneOption("TONE_CDMA_HIGH_PBX_SLS", ToneGenerator.TONE_CDMA_HIGH_PBX_SLS),
    ToneOption("TONE_CDMA_HIGH_PBX_SS", ToneGenerator.TONE_CDMA_HIGH_PBX_SS),
    ToneOption("TONE_CDMA_HIGH_PBX_SSL", ToneGenerator.TONE_CDMA_HIGH_PBX_SSL),
    ToneOption("TONE_CDMA_HIGH_PBX_S_X4", ToneGenerator.TONE_CDMA_HIGH_PBX_S_X4),
    ToneOption("TONE_CDMA_HIGH_SLS", ToneGenerator.TONE_CDMA_HIGH_SLS),
    ToneOption("TONE_CDMA_HIGH_SS", ToneGenerator.TONE_CDMA_HIGH_SS),
    ToneOption("TONE_CDMA_HIGH_SSL", ToneGenerator.TONE_CDMA_HIGH_SSL),
    ToneOption("TONE_CDMA_HIGH_SS_2", ToneGenerator.TONE_CDMA_HIGH_SS_2),
    ToneOption("TONE_CDMA_HIGH_S_X4", ToneGenerator.TONE_CDMA_HIGH_S_X4),
    ToneOption("TONE_CDMA_INTERCEPT", ToneGenerator.TONE_CDMA_INTERCEPT),
    ToneOption("TONE_CDMA_KEYPAD_VOLUME_KEY_LITE", ToneGenerator.TONE_CDMA_KEYPAD_VOLUME_KEY_LITE),
    ToneOption("TONE_CDMA_LOW_L", ToneGenerator.TONE_CDMA_LOW_L),
    ToneOption("TONE_CDMA_LOW_PBX_L", ToneGenerator.TONE_CDMA_LOW_PBX_L),
    ToneOption("TONE_CDMA_LOW_PBX_SLS", ToneGenerator.TONE_CDMA_LOW_PBX_SLS),
    ToneOption("TONE_CDMA_LOW_PBX_SS", ToneGenerator.TONE_CDMA_LOW_PBX_SS),
    ToneOption("TONE_CDMA_LOW_PBX_SSL", ToneGenerator.TONE_CDMA_LOW_PBX_SSL),
    ToneOption("TONE_CDMA_LOW_PBX_S_X4", ToneGenerator.TONE_CDMA_LOW_PBX_S_X4),
    ToneOption("TONE_CDMA_LOW_SLS", ToneGenerator.TONE_CDMA_LOW_SLS),
    ToneOption("TONE_CDMA_LOW_SS", ToneGenerator.TONE_CDMA_LOW_SS),
    ToneOption("TONE_CDMA_LOW_SSL", ToneGenerator.TONE_CDMA_LOW_SSL),
    ToneOption("TONE_CDMA_LOW_SS_2", ToneGenerator.TONE_CDMA_LOW_SS_2),
    ToneOption("TONE_CDMA_LOW_S_X4", ToneGenerator.TONE_CDMA_LOW_S_X4),
    ToneOption("TONE_CDMA_MED_L", ToneGenerator.TONE_CDMA_MED_L),
    ToneOption("TONE_CDMA_MED_PBX_L", ToneGenerator.TONE_CDMA_MED_PBX_L),
    ToneOption("TONE_CDMA_MED_PBX_SLS", ToneGenerator.TONE_CDMA_MED_PBX_SLS),
    ToneOption("TONE_CDMA_MED_PBX_SS", ToneGenerator.TONE_CDMA_MED_PBX_SS),
    ToneOption("TONE_CDMA_MED_PBX_SSL", ToneGenerator.TONE_CDMA_MED_PBX_SSL),
    ToneOption("TONE_CDMA_MED_PBX_S_X4", ToneGenerator.TONE_CDMA_MED_PBX_S_X4),
    ToneOption("TONE_CDMA_MED_SLS", ToneGenerator.TONE_CDMA_MED_SLS),
    ToneOption("TONE_CDMA_MED_SS", ToneGenerator.TONE_CDMA_MED_SS),
    ToneOption("TONE_CDMA_MED_SSL", ToneGenerator.TONE_CDMA_MED_SSL),
    ToneOption("TONE_CDMA_MED_SS_2", ToneGenerator.TONE_CDMA_MED_SS_2),
    ToneOption("TONE_CDMA_MED_S_X4", ToneGenerator.TONE_CDMA_MED_S_X4),
    ToneOption("TONE_CDMA_NETWORK_BUSY", ToneGenerator.TONE_CDMA_NETWORK_BUSY),
    ToneOption("TONE_CDMA_NETWORK_BUSY_ONE_SHOT", ToneGenerator.TONE_CDMA_NETWORK_BUSY_ONE_SHOT),
    ToneOption("TONE_CDMA_NETWORK_CALLWAITING", ToneGenerator.TONE_CDMA_NETWORK_CALLWAITING),
    ToneOption("TONE_CDMA_NETWORK_USA_RINGBACK", ToneGenerator.TONE_CDMA_NETWORK_USA_RINGBACK),
    ToneOption("TONE_CDMA_ONE_MIN_BEEP", ToneGenerator.TONE_CDMA_ONE_MIN_BEEP),
    ToneOption("TONE_CDMA_PIP", ToneGenerator.TONE_CDMA_PIP),
    ToneOption("TONE_CDMA_PRESSHOLDKEY_LITE", ToneGenerator.TONE_CDMA_PRESSHOLDKEY_LITE),
    ToneOption("TONE_CDMA_REORDER", ToneGenerator.TONE_CDMA_REORDER),
    ToneOption("TONE_CDMA_SIGNAL_OFF", ToneGenerator.TONE_CDMA_SIGNAL_OFF),
    ToneOption("TONE_CDMA_SOFT_ERROR_LITE", ToneGenerator.TONE_CDMA_SOFT_ERROR_LITE),
    ToneOption("TONE_DTMF_0", ToneGenerator.TONE_DTMF_0),
    ToneOption("TONE_DTMF_1", ToneGenerator.TONE_DTMF_1),
    ToneOption("TONE_DTMF_2", ToneGenerator.TONE_DTMF_2),
    ToneOption("TONE_DTMF_3", ToneGenerator.TONE_DTMF_3),
    ToneOption("TONE_DTMF_4", ToneGenerator.TONE_DTMF_4),
    ToneOption("TONE_DTMF_5", ToneGenerator.TONE_DTMF_5),
    ToneOption("TONE_DTMF_6", ToneGenerator.TONE_DTMF_6),
    ToneOption("TONE_DTMF_7", ToneGenerator.TONE_DTMF_7),
    ToneOption("TONE_DTMF_8", ToneGenerator.TONE_DTMF_8),
    ToneOption("TONE_DTMF_9", ToneGenerator.TONE_DTMF_9),
    ToneOption("TONE_DTMF_A", ToneGenerator.TONE_DTMF_A),
    ToneOption("TONE_DTMF_B", ToneGenerator.TONE_DTMF_B),
    ToneOption("TONE_DTMF_C", ToneGenerator.TONE_DTMF_C),
    ToneOption("TONE_DTMF_D", ToneGenerator.TONE_DTMF_D),
    ToneOption("TONE_DTMF_P", ToneGenerator.TONE_DTMF_P),
    ToneOption("TONE_DTMF_S", ToneGenerator.TONE_DTMF_S),
    ToneOption("TONE_PROP_ACK", ToneGenerator.TONE_PROP_ACK),
    ToneOption("TONE_PROP_BEEP", ToneGenerator.TONE_PROP_BEEP),
    ToneOption("TONE_PROP_BEEP2", ToneGenerator.TONE_PROP_BEEP2),
    ToneOption("TONE_PROP_NACK", ToneGenerator.TONE_PROP_NACK),
    ToneOption("TONE_PROP_PROMPT", ToneGenerator.TONE_PROP_PROMPT),
    ToneOption("TONE_SUP_BUSY", ToneGenerator.TONE_SUP_BUSY),
    ToneOption("TONE_SUP_CALL_WAITING", ToneGenerator.TONE_SUP_CALL_WAITING),
    ToneOption("TONE_SUP_CONFIRM", ToneGenerator.TONE_SUP_CONFIRM),
    ToneOption("TONE_SUP_CONGESTION", ToneGenerator.TONE_SUP_CONGESTION),
    ToneOption("TONE_SUP_CONGESTION_ABBREV", ToneGenerator.TONE_SUP_CONGESTION_ABBREV),
    ToneOption("TONE_SUP_DIAL", ToneGenerator.TONE_SUP_DIAL),
    ToneOption("TONE_SUP_ERROR", ToneGenerator.TONE_SUP_ERROR),
    ToneOption("TONE_SUP_INTERCEPT", ToneGenerator.TONE_SUP_INTERCEPT),
    ToneOption("TONE_SUP_INTERCEPT_ABBREV", ToneGenerator.TONE_SUP_INTERCEPT_ABBREV),
    ToneOption("TONE_SUP_PIP", ToneGenerator.TONE_SUP_PIP),
    ToneOption("TONE_SUP_RADIO_ACK", ToneGenerator.TONE_SUP_RADIO_ACK),
    ToneOption("TONE_SUP_RADIO_NOTAVAIL", ToneGenerator.TONE_SUP_RADIO_NOTAVAIL),
    ToneOption("TONE_SUP_RINGTONE", ToneGenerator.TONE_SUP_RINGTONE),
)

@Composable
fun SoundsScreen() {
    var durationMs by remember { mutableFloatStateOf(150f) }
    var volume by remember { mutableFloatStateOf(100f) }

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

        Spacer(modifier = Modifier.height(16.dp))

        Text("Volumen: ${volume.toInt()}%")
        Slider(
            value = volume,
            onValueChange = { volume = it },
            valueRange = 0f..100f,
            steps = 9
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn {
            items(toneOptions) { tone ->
                ListItem(
                    headlineContent = { Text(tone.name) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            playBeep(tone.toneConstant, volume.toInt(), durationMs.toInt())
                        }
                        .padding(16.dp)
                )
                HorizontalDivider()
            }
        }
    }

}

fun playBeep(tone: Int, volume:Int, duration: Int) {
    val toneGen = ToneGenerator(AudioManager.STREAM_MUSIC, volume)
    toneGen.startTone(tone, duration)
}

@Preview(showBackground = true)
@Composable
fun SoundsScreenPreview() {
    BeepsAndVibrationsTheme {
        SoundsScreen()
    }
}