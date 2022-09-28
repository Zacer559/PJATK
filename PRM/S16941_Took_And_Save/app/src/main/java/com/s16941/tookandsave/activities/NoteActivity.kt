package com.s16941.tookandsave.activities

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.s16941.tookandsave.NoteViewModel
import com.s16941.tookandsave.R
import com.s16941.tookandsave.databinding.ActivityNoteBinding
import com.s16941.tookandsave.db.Image
import com.s16941.tookandsave.models.SettingsModel
import com.s16941.tookandsave.services.LocationService
import kotlinx.android.synthetic.main.activity_note.*
import java.io.File
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {


    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)

        button_back_note.setOnClickListener {
            finish()
        }

        val noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        var noteText: String = ""

        DataBindingUtil.setContentView<ActivityNoteBinding>(this, R.layout.activity_note).apply {
            this.lifecycleOwner = this@NoteActivity
            this.viewModel = noteViewModel
        }

        noteViewModel.note.observe(this, Observer {
            Log.d("note changed", it.toString())
            noteText = it
        })

        // Adding watermark
        val extras = intent.extras
        var newUri: Uri? = null

        if (extras != null) {
            newUri = intent.extras?.get("image_uri_extra") as Uri?
        }

        val imageBitmap = MediaStore.Images.Media.getBitmap(contentResolver, newUri)

        val newImageBitmap = Bitmap.createBitmap(
            imageBitmap.width,
            imageBitmap.height,
            imageBitmap.config
        )

        val settingsSize = SettingsModel().size
        val settingsColor = SettingsModel().getNewColor()

        val canvas = Canvas(newImageBitmap)
        val paint = Paint().apply {
            color = settingsColor
            textSize = settingsSize.toFloat()
            style = Paint.Style.FILL
        }
        canvas.drawPaint(paint)

        canvas.drawBitmap(imageBitmap, 0F, 0F, null)

        //Getting GPS data
        val currentLocationLat = LocationService.currentLocation?.latitude
        val currentLocationLon = LocationService.currentLocation?.longitude

        var lat = ""
        if (currentLocationLat != null) {
            lat = currentLocationLat.toString()
        }
        var lon = ""
        if (currentLocationLon != null) {
            lon = currentLocationLon.toString()
        }

        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses: List<Address> = geocoder.getFromLocation(lat.toDouble(), lon.toDouble(), 1)
        val cityName: String = addresses[0].locality
        val countryName: String = addresses[0].countryName

        val currentAdressString = "$cityName, $countryName"

        val currentAddress = LocationService.currentAddress
        if (currentAddress != null) {
            canvas.drawText(currentAdressString, 10F, canvas.height.toFloat() - 100F, paint)
        } else {
            canvas.drawText("unknown location", 10F, canvas.height.toFloat() - 100F, paint)
        }

        val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        canvas.drawText(date, 10F, canvas.height.toFloat() - 30F, paint)

        findViewById<ImageView>(R.id.imageView).setImageBitmap(newImageBitmap)

        // save image and data in db and files
        findViewById<Button>(R.id.button_save_note).apply {
            setOnClickListener {
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss",Locale.US).format(Date())
                val filename = "ready_image_$timeStamp.jpg"
                val file_path2: String =
                    Environment.getExternalStorageDirectory().absolutePath.toString() +
                            "/TookAndSave/"
                Files.createDirectories(Paths.get(file_path2));
                val createdFile = File.createTempFile(filename, "", filesDir).apply {
                    val out = FileOutputStream(this)
                    newImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                    out.flush()
                    out.close()
                    Files.copy(
                        this.toPath(),
                        Paths.get(file_path2 + "Took_and_save_$timeStamp.jpg")
                    )
                }

                val imageRow: Image = Image(
                    0,
                    createdFile.absolutePath,
                    noteText,
                    date,
                    currentAdressString,
                    lat,
                    lon
                )


                noteViewModel.launchInsertToDb(this@NoteActivity, imageRow);


                finish()
            }
        }
    }


}

