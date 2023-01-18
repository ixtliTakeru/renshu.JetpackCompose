package g.takeru.reshu.jetpackcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import g.takeru.reshu.jetpackcompose.shimmerloadinglist.ShimmerListActivity
import g.takeru.reshu.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .fillMaxSize()
                    ) {
                        Greeting("Android")
                        ShimmerEffectButton()
                    }
                }
            }
        }
    }

    @Composable
    fun ShimmerEffectButton() {
        SimpleButton(
            btnText = "Shimmer effect list",
            clickEvent = {
                startActivity(Intent(this, ShimmerListActivity::class.java))
            })
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeTheme {
        Greeting("Android")
    }
}

@Composable
fun SimpleButton(btnText: String, clickEvent: () -> Unit) {
    Button(
        onClick = clickEvent,
        modifier = Modifier.padding(20.dp, 10.dp)
    ) {
        Text(
            text = btnText,
            color = Color.White,
            fontSize = 18.sp,
        )
    }
}