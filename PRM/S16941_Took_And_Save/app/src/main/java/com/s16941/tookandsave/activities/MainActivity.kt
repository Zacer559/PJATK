package com.s16941.tookandsave.activities

import android.Manifest
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.s16941.tookandsave.MainViewModel
import com.s16941.tookandsave.R
import com.s16941.tookandsave.helpers.ImageAdapter
import com.s16941.tookandsave.services.LocationService
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


private const val REQUEST_CODE_PERMISSIONS_CAMERA = 10
private const val REQUEST_CODE_PERMISSIONS_LOCATION = 11
const val EXTRA_IMAGE_URI = "image_uri_extra"

class MainActivity : AppCompatActivity() {

    companion object {
        var ACTIVITY: Activity? = null
    }

    private lateinit var mainViewModel: MainViewModel

    @Suppress("PrivatePropertyName")
    private val REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        ACTIVITY = this

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        this.mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // DB observer for new pictures saved
        mainViewModel.imageObjects.observe(this, androidx.lifecycle.Observer {
            Log.d("db image count", it.size.toString())
            myAdapter.submitList(it)
            myAdapter.notifyDataSetChanged()
        })

        initRecyclerView()
        // Photo fab button
        fab_add_photo.setOnClickListener { view ->
            if (this.allPermissionsGranted) {
                dispatchTakePictureIntent()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    REQUIRED_PERMISSIONS,
                    REQUEST_CODE_PERMISSIONS_CAMERA
                )
            }
        }

        ActivityCompat.requestPermissions(
            this,
            REQUIRED_PERMISSIONS,
            REQUEST_CODE_PERMISSIONS_LOCATION
        )

        startLocationService()

    }

    override fun onResume() {
        super.onResume()
        //added reloading from db
        mainViewModel.launchSelectFromDb(this)
    }

    private lateinit var myAdapter: ImageAdapter

    //recycele view init
    private fun initRecyclerView() {
        image_list.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            myAdapter = ImageAdapter(this@MainActivity)
            adapter = myAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun startLocationService() {
        if (!isLocationServiceRunning()) {
            val serviceIntent = Intent(this, LocationService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(serviceIntent)
            } else {
                startService(serviceIntent)
            }
        }
    }

    private fun isLocationServiceRunning(): Boolean {
        val manager: ActivityManager =
            getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if ("com.s16941.tookandsave.services.LocationService" == service.service.className) {
                return true
            }
        }
        return false
    }

    private var allPermissionsGranted = false

    // request perrmision
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS_CAMERA) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                this.allPermissionsGranted = true
            }
            if (this.allPermissionsGranted) {
                dispatchTakePictureIntent()
            } else {
                Toast.makeText(
                    this,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        if (requestCode == REQUEST_CODE_PERMISSIONS_LOCATION) {
            startLocationService()
        }
    }

    // start activity on itemView click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                this.startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

// Operation with camera

    @Suppress("PrivatePropertyName")
    private val REQUEST_TAKE_PHOTO = 5
    private var photoUri: Uri? = null

    // Take picture
    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).let { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    Log.e("main activity", "error creating file $ex")
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.s16941.prm_02.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    this.photoUri = photoURI
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    private lateinit var currentPhotoPath: String

    //Create image rom picture
    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss",Locale.US).format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // If all ok start new activity
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == Activity.RESULT_OK) {
            if (this.photoUri != null) {
                Log.d("photouri", this.photoUri.toString())

                val intent = Intent(this, NoteActivity::class.java)
                intent.putExtra(EXTRA_IMAGE_URI, photoUri)
                startActivity(intent)

                this.photoUri = null
            } else {
                Toast.makeText(this, "Can't get photo URI", Toast.LENGTH_SHORT).show()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}