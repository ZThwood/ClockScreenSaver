package com.example.clockscreensaver

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.clockscreensaver.ui.theme.ClockScreenSaverTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        让应用的内容扩展到屏幕边缘，充分利用屏幕空间
        enableEdgeToEdge()
//        设置 Compose 的 UI 内容（Compose 是传统 Android View 系统的替代品）
        setContent {
            ClockScreenSaverTheme {
                // Scaffold：提供一种规范的应用布局结构，支持顶部栏、底部栏、浮动按钮等组件。在这里，它用于填充整个屏幕，并将 innerPadding 传递给内容
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(getString(R.string.app_name), Modifier.padding(innerPadding))
//                    Counter(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClockScreenSaverTheme {
        Greeting("Android")
    }
    ClockScreenSaverTheme {
        Counter()
    }
}



@Composable
fun Counter(modifier: Modifier = Modifier) {
    var count by remember { mutableIntStateOf(0) }
    Button(onClick = { count++ }) {
        Text(
            text = "Click count: $count",
            modifier = modifier
        )

    }
}
