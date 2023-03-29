package com.ahmadhassan.mynote.android

import android.os.Bundle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ahmadhassan.mynote.Application
import com.ahmadhassan.mynote.di.initKoin
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.stopKoin

class MainActivity : PreComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKoin {
            androidContext(applicationContext)
//            androidLogger()
        }

        setContent {
            Application()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopKoin()
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    GreetingView("Hello, Android!")
}
