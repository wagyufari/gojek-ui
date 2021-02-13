package com.mayburger.gojekuiclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.mayburger.gojekuiclone.databinding.ActivityFlipBinding
import com.mayburger.gojekuiclone.util.ext.ViewUtils.flipX

class FlipActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFlipBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        val card = binding.card

        card.flipX(1000)

    }
}