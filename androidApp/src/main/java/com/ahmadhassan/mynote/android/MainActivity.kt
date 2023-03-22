package com.ahmadhassan.mynote.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ahmadhassan.mynote.ui.NoteRootComponent
import com.ahmadhassan.mynote.Application
import com.ahmadhassan.mynote.di.initKoin
import com.arkivanov.decompose.defaultComponentContext
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.stopKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initKoin {
            androidContext(applicationContext)
//            androidLogger()
        }

        val root = NoteRootComponent(defaultComponentContext())
        setContent {
            Application(component = root)
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
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
