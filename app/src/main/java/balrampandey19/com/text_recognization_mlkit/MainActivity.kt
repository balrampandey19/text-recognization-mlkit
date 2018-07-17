package balrampandey19.com.text_recognization_mlkit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.provider.MediaStore
import android.content.Intent
import android.app.Activity
import android.graphics.Bitmap
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.text.FirebaseVisionText


class MainActivity : AppCompatActivity() {

    private val requestCode = 20

    lateinit var image: ImageView
    lateinit var onCloud: Button
    lateinit var onDevice: Button
    lateinit var mGraphicOverlay: GraphicOverlay


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onDevice = findViewById<Button>(R.id.device)
        onCloud = findViewById<Button>(R.id.cloude)
        image = findViewById<ImageView>(R.id.captured_photo)
        val capture = findViewById<Button>(R.id.photo_button)

        capture.setOnClickListener(View.OnClickListener {
            val photoCaptureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(photoCaptureIntent, requestCode)

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (this.requestCode === requestCode && resultCode === Activity.RESULT_OK) {
            val bitmap = data!!.getExtras().get("data") as Bitmap
            image.setImageBitmap(bitmap)
        }
    }

    private fun runTextRecognition(bitmap: Bitmap) {
        val image = FirebaseVisionImage.fromBitmap(bitmap)
        val detector = FirebaseVision.getInstance()
                .visionTextDetector
        detector.detectInImage(image)
                .addOnSuccessListener { texts ->
                    processTextRecognitionResult(texts)
                }
                .addOnFailureListener(
                        object : OnFailureListener {
                            override fun onFailure(e: Exception) {
                                // Task failed with an exception
                                e.printStackTrace()
                            }
                        })
    }
}
