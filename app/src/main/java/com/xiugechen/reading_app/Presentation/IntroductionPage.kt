package com.xiugechen.reading_app.Presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.xiugechen.reading_app.Data.Config
import com.xiugechen.reading_app.Data.DataManager
import com.xiugechen.reading_app.R
import kotlinx.android.synthetic.main.introduction_page.*

class IntroductionPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("IntroductionPage", "onCreate: Called")
        super.onCreate(savedInstanceState)

        if (Config.isBlackMode) {
            if (Config.isHorizontallySwipe) {
                setTheme(R.style.DarkTheme_SwipeRead)
            }
            else {
                setTheme(R.style.DarkTheme_VerticallyRead)
            }
        }
        else {
            if (Config.isHorizontallySwipe) {
                setTheme(R.style.LightTheme_SwipeRead)
            }
            else {
                setTheme(R.style.LightTheme_VerticallyRead)
            }
        }
        setContentView(R.layout.introduction_page)

        addListener()
        setIntroductionText()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.theme_settings) {
            Config.isBlackMode = !Config.isBlackMode
            finish()
            startActivity(intent)
            return true
        }
        else if (item.itemId == R.id.read_mode_settings) {
            Config.isHorizontallySwipe = !Config.isHorizontallySwipe
            finish()
            startActivity(intent)
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun addListener() {
        val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrate_interval = this.resources.getInteger(R.integer.vibrate_interval).toLong()

        nextButton.setOnClickListener {
            vibrator.vibrate(VibrationEffect.createOneShot(vibrate_interval, VibrationEffect.DEFAULT_AMPLITUDE))
            startActivity(Intent(this, AgreementPage::class.java))
        }

        backButton.setOnClickListener {
            vibrator.vibrate(VibrationEffect.createOneShot(vibrate_interval, VibrationEffect.DEFAULT_AMPLITUDE))
            startActivity(Intent(this, StartPage::class.java))
        }
    }

    private fun setIntroductionText() {
        val readText = DataManager.mDataReader.mIntro
        introductionTextTitle.text = readText.first
        introductionTextBody.text = readText.second
    }
}