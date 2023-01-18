package g.takeru.reshu.jetpackcompose.shimmerloadinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import g.takeru.reshu.jetpackcompose.ui.theme.JetpackComposeTheme
import kotlinx.coroutines.delay

class ShimmerListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                var isLoading by remember {
                    mutableStateOf(true)
                }
                LaunchedEffect(key1 = true) {
                    delay(5000)
                    isLoading = false
                }
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(20) {
                        ShimmerListItem(
                            loading = isLoading,
                            contentAfterLoading = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Home,
                                        contentDescription = null,
                                        modifier = Modifier.size(100.dp)
                                    )
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Column(
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text(
                                            text = "This is a demo for shimmer loading effect for list. This is a demo for shimmer loading effect for list."
                                        )
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)

                        )
                    }
                }
            }
        }
    }
}
