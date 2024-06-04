package com.kaliostech.ipaint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Path
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Canvas
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kaliostech.ipaint.PaintView.Companion.colorlist
import com.kaliostech.ipaint.PaintView.Companion.currentBrush
import com.kaliostech.ipaint.PaintView.Companion.currentStroke
import com.kaliostech.ipaint.PaintView.Companion.pathlist
import com.kaliostech.ipaint.PaintView.Companion.strokelist
import com.kaliostech.ipaint.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        var path = Path()
        var paintBrush = Paint()
    }
    private var clicked = false
    private var changecanvas = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.canvasColorChange.setOnClickListener {
            changecanvascolor(changecanvas)
            changecanvas = !changecanvas
        }


        binding.btnPink.setOnClickListener {
            paintBrush.color = ContextCompat.getColor(applicationContext, R.color.pink)
            currentColor(paintBrush.color)
        }
        binding.btnYellow.setOnClickListener {
            paintBrush.color = ContextCompat.getColor(applicationContext, R.color.yellow)
            currentColor(paintBrush.color)
        }
        binding.btnOrange.setOnClickListener {
            paintBrush.color = ContextCompat.getColor(applicationContext, R.color.orange)
            currentColor(paintBrush.color)
        }
        binding.btnPeach.setOnClickListener {
            paintBrush.color = ContextCompat.getColor(applicationContext, R.color.light_peach)
            currentColor(paintBrush.color)
        }
        binding.btnRed.setOnClickListener {
            paintBrush.color = ContextCompat.getColor(applicationContext, R.color.red)
            currentColor(paintBrush.color)
        }
        binding.btnBrown.setOnClickListener {
            paintBrush.color = ContextCompat.getColor(applicationContext, R.color.brown)
            currentColor(paintBrush.color)
        }
        binding.btnLightGreen.setOnClickListener {
            paintBrush.color = ContextCompat.getColor(applicationContext, R.color.light_green)
            currentColor(paintBrush.color)
        }
        binding.btnDeepGreen.setOnClickListener {
            paintBrush.color = ContextCompat.getColor(applicationContext, R.color.deep_green)
            currentColor(paintBrush.color)
        }
        binding.btnSkyBlue.setOnClickListener {
            paintBrush.color = ContextCompat.getColor(applicationContext, R.color.sky_blue)
            currentColor(paintBrush.color)
        }
        binding.btnDeepBlue.setOnClickListener {
            paintBrush.color = ContextCompat.getColor(applicationContext, R.color.deep_blue)
            currentColor(paintBrush.color)
        }
        binding.btnViolet.setOnClickListener {
            paintBrush.color = ContextCompat.getColor(applicationContext, R.color.violet)
            currentColor(paintBrush.color)
        }
        binding.btnBlack.setOnClickListener {
            paintBrush.color = ContextCompat.getColor(applicationContext, R.color.black)
            currentColor(paintBrush.color)
        }
        binding.btnWhite.setOnClickListener {
            paintBrush.color = ContextCompat.getColor(applicationContext, R.color.white)
            currentColor(paintBrush.color)
        }
        binding.btnEraser.setOnClickListener {
            pathlist.clear()
            colorlist.clear()
            strokelist.clear()
            path.reset()
        }
        // Floating Action Button
        binding.fabTools.setOnClickListener {
            setVisibility(clicked)
            clicked = !clicked
        }
        binding.fabPencil.setOnClickListener {
            Toast.makeText(this, "Pencil", Toast.LENGTH_SHORT).show()
            changeStrokeWidth(5f)
        }
        binding.fabBrush.setOnClickListener {
            Toast.makeText(this, "Brush", Toast.LENGTH_SHORT).show()
            changeStrokeWidth(30f)
        }
        binding.fabMarker.setOnClickListener {
            Toast.makeText(this, "Marker", Toast.LENGTH_SHORT).show()
            changeStrokeWidth(20f)
        }
        binding.btnShare.setOnClickListener {
            val bitmap = getBitmapFromView(binding.relativeLayout)
            val uri = saveBitmapToCache(bitmap)
            shareImageUri(uri)
        }
        binding.btnSaveCanvas.setOnClickListener{
            val bitmap = getBitmapFromView(binding.relativeLayout)
            saveBitmapToGallery(this, bitmap)
        }
    }
    private fun currentColor(color: Int) {
        currentBrush = color
        path = Path()
    }

    private fun changeStrokeWidth(width: Float) {
        currentStroke = width
        path = Path()
    }

    private fun setVisibility(clicked: Boolean) {
        if (!clicked) {
            binding.fabPencil.visibility = View.VISIBLE
            binding.fabBrush.visibility = View.VISIBLE
            binding.fabMarker.visibility = View.VISIBLE
        } else {
            binding.fabPencil.visibility = View.INVISIBLE
            binding.fabBrush.visibility = View.INVISIBLE
            binding.fabMarker.visibility = View.INVISIBLE
        }
    }
    private fun changecanvascolor(changecanvas:Boolean){
        if(changecanvas) {
            binding.main.setBackgroundColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.black
                )
            )
            binding.relativeLayout.setBackgroundColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.black
                )
            )
            binding.canvasColorChange.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_dark_canvas))
            binding.canvasColorChange.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.dark_bg))

        }
        else{
            binding.main.setBackgroundColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.white
                )
                )
            binding.relativeLayout.setBackgroundColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.white
                )
            )
            binding.canvasColorChange.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_white_canvas))
            binding.canvasColorChange.setBackgroundDrawable(ContextCompat.getDrawable(this,R.drawable.main_fab_bg))

        }
    }
    private fun getBitmapFromView(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = android.graphics.Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    private fun saveBitmapToCache(bitmap: Bitmap): Uri? {
        val cachePath = File(applicationContext.cacheDir, "images")
        cachePath.mkdirs()
        val file = File(cachePath, "image.png")
        try {
            val stream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return FileProvider.getUriForFile(applicationContext, "$packageName.provider", file)
    }

    private fun shareImageUri(uri: Uri?) {
        if (uri == null) {
            Toast.makeText(this, "Error sharing image", Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.type = "image/png"
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(Intent.createChooser(intent, "Share Image"))
    }
    private fun saveBitmapToGallery(context: Context, bitmap: Bitmap): Uri? {
        val filename = "image_${System.currentTimeMillis()}.png"
        val mimeType = "image/png"
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
            put(MediaStore.MediaColumns.MIME_TYPE, mimeType)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/iPaint")
                put(MediaStore.MediaColumns.IS_PENDING, 1)
            }
        }

        val resolver = context.contentResolver
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        if (uri != null) {
            var outputStream: OutputStream? = null
            try {
                outputStream = resolver.openOutputStream(uri)
                if (outputStream != null) {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                    outputStream.flush()
                }
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(context, "Error saving image: ${e.message}", Toast.LENGTH_SHORT).show()
                return null
            } finally {
                outputStream?.close()
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                contentValues.clear()
                contentValues.put(MediaStore.MediaColumns.IS_PENDING, 0)
                resolver.update(uri, contentValues, null, null)
            }

            Toast.makeText(context, "Image saved to gallery", Toast.LENGTH_SHORT).show()

            // Notify media scanner about the new file so it is immediately available to the user.
            val scanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            scanIntent.data = uri
            context.sendBroadcast(scanIntent)

            return uri
        } else {
            Toast.makeText(context, "Error saving image", Toast.LENGTH_SHORT).show()
            return null
        }
    }
}
