package com.s16941.tookandsave.helpers

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.s16941.tookandsave.R
import com.s16941.tookandsave.activities.ImageActivity
import com.s16941.tookandsave.db.Image
import kotlinx.android.synthetic.main.image_item.view.*
import java.io.File

// Converts data to recycler view
class ImageAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Image> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MyViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(list: List<Image>) {
        items = list
    }

    class MyViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.picture_image_item
        private val date = itemView.date_image_item
        private val loc = itemView.localization_image_item

        fun bind(imageParam: Image) {
            date.text = imageParam.date
            loc.text = imageParam.loc
            val imageFile: File = File(imageParam.imageLocation)
            val imageBitmap = MediaStore.Images.Media.getBitmap(
                itemView.context.contentResolver,
                Uri.fromFile(imageFile)
            )
            image.setImageBitmap(imageBitmap)

            itemView.setOnClickListener { v ->
                Log.v("aaa", "item clicked")
                val intent = Intent(v.context, ImageActivity::class.java)
                intent.putExtra(ImageActivity.EXTRA_IMAGE_PATH, imageParam.imageLocation)
                intent.putExtra(ImageActivity.EXTRA_NOTE_TEXT, imageParam.note)
                intent.putExtra(ImageActivity.EXTRA_DATE_TEXT, imageParam.date)
                intent.putExtra(ImageActivity.EXTRA_LOC_TEXT, imageParam.loc)
                startActivity(v.context, intent, null)
            }
        }
    }
}