package balrampandey19.com.text_recognization_mlkit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val onDevice = findViewById<Button>(R.id.device)
        val onCloud = findViewById<Button>(R.id.cloude)
        val image = findViewById<ImageView>(R.id.captured_photo)
        val capture = findViewById<Button>(R.id.photo_button)


    }
}
