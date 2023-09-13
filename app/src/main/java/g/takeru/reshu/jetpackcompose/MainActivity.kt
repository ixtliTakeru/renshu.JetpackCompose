package g.takeru.reshu.jetpackcompose

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import g.takeru.reshu.jetpackcompose.shimmerLoadingList.ShimmerListActivity
import g.takeru.reshu.jetpackcompose.swipeableTabRows.SwipeableTabRowsActivity
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
                    displayList()
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

data class Example(
    val name: String,
    val clazz: Class<*>?
)

@OptIn(ExperimentalUnitApi::class)
@Composable
fun displayList() {
    // data
    val exampleList = mutableListOf<Example>()
    exampleList.add(Example("Shimmer List", ShimmerListActivity::class.java))
    exampleList.add(Example("Swipeable Tab Rows", SwipeableTabRowsActivity::class.java))

    // get context
    val context = LocalContext.current

    // startActivity
    fun goToActivity(clazz: Class<*>?) {
        context.startActivity(Intent(context, clazz))
    }

    // listview
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // add header
        Text(
            text = "Jetpack Compose Example",
            modifier = Modifier.padding(10.dp),
            style = TextStyle(
                color = Color.Black,
                fontSize = TextUnit(value = 20.0F, type = TextUnitType.Sp)
            ),
            fontWeight = FontWeight.Black
        )

        // add list
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(exampleList) { index, example ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { goToActivity(example.clazz) }
                ) {
                    val displayName = "${index + 1}. ${example.name}"
                    Text(displayName, modifier = Modifier.padding(15.dp))
                }
            }
        }
    }
}
