package txp.prottoy.shafee.tanveer.gyroscopesensorapplication

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private lateinit var gyroSensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        if(gyroSensor == null) {
            Toast.makeText(applicationContext, "No gyroscope sensor detected", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(sensorEvent: SensorEvent) {
        val stringValue: String = sensorEvent.values[0].toString() + " " + sensorEvent.values[1].toString() + " " + sensorEvent.values[2].toString()
        main_text.text = stringValue
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}
