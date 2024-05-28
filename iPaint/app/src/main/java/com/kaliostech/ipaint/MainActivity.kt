package com.kaliostech.ipaint

import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kaliostech.ipaint.PaintView.Companion.colorlist
import com.kaliostech.ipaint.PaintView.Companion.currentBrush
import com.kaliostech.ipaint.PaintView.Companion.pathlist
import com.kaliostech.ipaint.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object {
        var path = Path()
        var paintBrush = Paint()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.btnPink.setOnClickListener {
            paintBrush.color=ContextCompat.getColor(applicationContext, R.color.pink)
            currentColor(paintBrush.color)
        }
        binding.btnYellow.setOnClickListener {
            paintBrush.color=ContextCompat.getColor(applicationContext, R.color.yellow)
            currentColor(paintBrush.color)
        }
        binding.btnOrange.setOnClickListener {
            paintBrush.color=ContextCompat.getColor(applicationContext, R.color.orange)
            currentColor(paintBrush.color)
        }
        binding.btnPeach.setOnClickListener {
            paintBrush.color=ContextCompat.getColor(applicationContext, R.color.light_peach)
            currentColor(paintBrush.color)
        }
        binding.btnRed.setOnClickListener {
            paintBrush.color=ContextCompat.getColor(applicationContext, R.color.red)
            currentColor(paintBrush.color)
        }

        binding.btnBrown.setOnClickListener {
            paintBrush.color=ContextCompat.getColor(applicationContext, R.color.brown)
            currentColor(paintBrush.color)
        }
        binding.btnLightGreen.setOnClickListener {
            paintBrush.color= ContextCompat.getColor(applicationContext, R.color.light_green)
            currentColor(paintBrush.color)
        }
        binding.btnDeepGreen.setOnClickListener {
            paintBrush.color=ContextCompat.getColor(applicationContext, R.color.deep_green)
            currentColor(paintBrush.color)
        }
        binding.btnSkyBlue.setOnClickListener {
            paintBrush.color=ContextCompat.getColor(applicationContext, R.color.sky_blue)
            currentColor(paintBrush.color)
        }
        binding.btnDeepBlue.setOnClickListener {
            paintBrush.color=ContextCompat.getColor(applicationContext, R.color.deep_blue)
            currentColor(paintBrush.color)
        }
        binding.btnViolet.setOnClickListener {
            paintBrush.color=ContextCompat.getColor(applicationContext, R.color.violet)
            currentColor(paintBrush.color)
        }
        binding.btnBlack.setOnClickListener {
            paintBrush.color=ContextCompat.getColor(applicationContext, R.color.black)
            currentColor(paintBrush.color)
        }
        binding.btnWhite.setOnClickListener {
            paintBrush.color=ContextCompat.getColor(applicationContext, R.color.white)
            currentColor(paintBrush.color)
        }
        binding.btnEraser.setOnClickListener {
            pathlist.clear()
            colorlist.clear()
            path.reset()
        }
    }
    private fun currentColor(color: Int) {
        currentBrush = color
        path = Path()
    }

}