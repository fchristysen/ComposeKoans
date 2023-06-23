package org.greenfroyo.composekoans

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import org.greenfroyo.composekoans.insightj.ISJSearchActivity
import org.greenfroyo.composekoans.ui.theme.ComposeKoansTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeKoansTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Screen()
                }
            }
        }
    }
}

@Composable
fun PageList(modifier: Modifier = Modifier, navigate: (NavigationDestination) -> Unit = {} ) {
    Column() {
        Button(onClick = { navigate(NavigationDestination.INSIGHTJ) }) {
            Text(
                text = "Insight Journal",
                modifier = modifier
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Screen(viewModel: MainViewModel = viewModel()) {
    val context = LocalContext.current
    LaunchedEffect(key1 = 1) {
        viewModel.state.collect { next ->
            next.navDes?.run {
                when(this){
                    NavigationDestination.INSIGHTJ -> context.startActivity(Intent(context, ISJSearchActivity::class.java))
                }
            }
        }
    }
    ComposeKoansTheme {
        Box(modifier = Modifier.fillMaxSize())
        PageList(navigate = {dest ->
            viewModel.navigate(dest)
        })
    }
}