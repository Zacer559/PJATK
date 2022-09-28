package com.s16941.tookandsave.activities

import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import com.s16941.tookandsave.R
import kotlinx.android.synthetic.main.activity_image.*
import java.io.File

class ImageActivity : AppCompatActivity() {
    //Class responsible for displaying picture and data
    companion object {
        const val EXTRA_IMAGE_PATH = "image_path_extra"
        const val EXTRA_NOTE_TEXT = "note_text_extra"
        const val EXTRA_DATE_TEXT = "date_text_extra"
        const val EXTRA_LOC_TEXT = "loc_text_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val noteText = intent.extras?.get(EXTRA_NOTE_TEXT) as String
        val imagePath = intent.extras?.get(EXTRA_IMAGE_PATH) as String
        val date = intent.extras?.get(EXTRA_DATE_TEXT) as String
        val loc = intent.extras?.get(EXTRA_LOC_TEXT) as String

        note_image_activity.text = noteText
        date_image_activity.text = date
        loc_image_activity.text = loc

        val imageFile = File(imagePath)
        val imageBitmap =
            MediaStore.Images.Media.getBitmap(contentResolver, Uri.fromFile(imageFile))
        image_image_activity.setImageBitmap(imageBitmap)

        button_back_from_image.setOnClickListener {
            finish()
        }
    }
}
