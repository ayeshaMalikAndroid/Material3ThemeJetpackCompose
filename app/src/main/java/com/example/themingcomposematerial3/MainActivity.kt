package com.example.themingcomposematerial3

import android.os.Bundle
import androidx.activity.compose.setContent
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.themingcomposematerial3.data.LocalEmailsDataProvider
import com.example.themingcomposematerial3.ui.*


class MainActivity : ComponentActivity() {

    private val viewModel: ReplyHomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val uiState by viewModel.uiState.collectAsState()
            AppTheme {
                Surface(tonalElevation = 5.dp) {
                    ReplyApp(uiState)
                }
            }
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun ReplyAppPreview() {
    AppTheme {
        ReplyApp(
            replyHomeUIState = ReplyHomeUIState(
                emails = LocalEmailsDataProvider.allEmails
            )
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun ChatMessagePreview() {
    AppTheme {
        Column {
            var showMore by rememberSaveable { mutableStateOf(false) }
            Text(
                text = "My Text Chat App",
                style = MaterialTheme.typography.bodyLarge
                    .copy(color = MaterialTheme.colorScheme.primary),
            )
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Send")
            }

            Text(
                text = "My Text Chat App dsnlkdsnsa lkdsanldskndas" +
                        "dsalkdsaksa" +
                        " dsaksalksdlkdsa" +
                        "dsak dsalkndskdsa" +
                        " dsakdsalkdsalkdsa" +
                        " dsakdsalkdsa'lsakdsa" +
                        " mdsa ldsa'dsa'lmdsa" +
                        " dsalk sadlk dsalkdsa " +
                        " salmdsa;ldsam;dsalmdsa" +
                        ";lkdsa;ldsands" +
                        " dsa;lmdsal;dsa",
                modifier = androidx.compose.ui.Modifier
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioLowBouncy,
                            stiffness = Spring.StiffnessLow

                        )
                    )
                    .clickable {
                        showMore = !showMore
                    },
                style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
                maxLines = if (!showMore) 2 else 8,
                overflow = TextOverflow.Ellipsis,
//                onTextLayout = {
//                    if(it.hasVisualOverflow){
//                        // show Button...//
//                        showMore = true
//                    }
//                }

            )

            if (!showMore) {
                Button(onClick = { showMore = !showMore }) {
                    Text(text = "More")
                }
            }

        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun ComposeDrawingCanvas() {
    AppTheme {
        Canvas(modifier = androidx.compose.ui.Modifier.fillMaxSize(), onDraw = {
            drawCircle(
                Color.Magenta,
                center = Offset(200.dp.toPx(), 100.dp.toPx()),
                radius = 60.dp.toPx()
            )
        })
    }
}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun ComposeDrawingCanvasScale() {
    AppTheme {
        Canvas(modifier = androidx.compose.ui.Modifier.fillMaxSize(), onDraw = {
            scale(scaleX = 5f, scaleY = 8f) {
                drawCircle(
                    Color.Blue,
                    radius = 40.dp.toPx()
                )
            }
        })
    }
}


@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    name = "DefaultPreviewLight"
)
@Composable
fun ComposeDrawingCanvasGraph() {
    AppTheme {
        Box(
            modifier = androidx.compose.ui.Modifier
                .background(md_theme_light_outlineVariant)
                .fillMaxSize()
        ) {
            Canvas(modifier = androidx.compose.ui.Modifier
                .padding(8.dp)
                .aspectRatio(3 / 2f)
                .fillMaxSize()
                .drawWithCache {
                    //val path = generatePath(graphData,size)
                    onDrawBehind {
                        //drawPath(path,Color.Green,
                        //style = Stroke(2.dp.toPx()))
                    }
                }) {

                // draw main rectangle.
                val barWidthPx = 1.dp.toPx()
                drawRect(md_theme_light_error, style = Stroke(barWidthPx))

                // draw vertical Lines..//
                val verticalLines = 4
                val verticalSize = size.width / (verticalLines + 1)
                repeat(verticalLines) { i ->
                    val startX = verticalSize * (i + 1)
                    drawLine(
                        md_theme_dark_primary,
                        start = Offset(startX, 0f),
                        end = Offset(startX, size.height),
                        strokeWidth = barWidthPx
                    )
                }

                // draw horizontal lines
                val horizontalLines = 3
                val sectionSize = size.height / (horizontalLines + 1)
                repeat(horizontalLines) { i ->
                    val startY = sectionSize * (i + 1)
                    drawLine(
                        md_theme_dark_onTertiaryContainer,
                        start = Offset(0f, startY),
                        end = Offset(size.width, startY),
                        strokeWidth = barWidthPx
                    )
                }

            }
        }
    }
}

//fun generatePath(data:List<Balance>, size: Size): Path {
//    val path = Path()
//
//    data.forEachIndexed { i,balance ->
//
//        path.lineTo(x,y)
//    }
//    return path
//}


