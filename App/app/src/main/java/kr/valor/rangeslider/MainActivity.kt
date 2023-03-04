package kr.valor.rangeslider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.core.util.toClosedRange
import androidx.core.util.toRange
import com.google.android.material.slider.RangeSlider
import kr.valor.rangeslider.databinding.ActivityMainBinding
import kotlin.math.roundToInt

private const val SEPARATION_VALUE = 0.0001F

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding
            .inflate(layoutInflater)
            .apply {
                noSeparationSlider.init()
                separationSlider.init(SEPARATION_VALUE)
            }
            .root
            .let(::setContentView)
    }
}

private const val DEBUG_TAG = "TAG-SLIDER"

private val RangeSlider.activeThumbName: String
    get() = if (activeThumbIndex == 0) "FIRST" else "SECOND"

private fun RangeSlider.init(separation: Float = 0F) {
    setMinSeparationValue(separation)
    setLabelFormatter { value -> value.roundToInt().toString() }
    addOnChangeListener { slider, _, _ ->
        Log.d(DEBUG_TAG, "Values : ${slider.values.map(Float::roundToInt).distinct()}")
        Log.d(DEBUG_TAG, "Active : ${slider.activeThumbName}")
    }

    values = listOf(20F, 50F)
}