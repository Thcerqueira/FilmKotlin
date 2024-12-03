import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.myapp.MainViewModel

@Composable
fun Series(modifier: Modifier = Modifier, navController: NavController, motCle: String) {
    val viewModel: MainViewModel = viewModel()
    val seriesState = viewModel.series.collectAsState()
    val series = seriesState.value

    LaunchedEffect(motCle) {
        if (motCle.isBlank()) {
            viewModel.weekSeries()
        } else {
            viewModel.searchSeries(motCle)
        }
    }


    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Séries",
                fontSize = 38.sp,
                color = Color.Black,
                modifier = Modifier.padding(end = 8.dp)
            )
            Icon(
                imageVector = Icons.Filled.TrendingUp,
                contentDescription = "graphique",
                tint = Color.Black,
                modifier = Modifier.size(40.dp)
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            modifier = Modifier.padding(8.dp),
            content = {
                items(series.size) { index ->
                    val serie = series[index]
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(width = 160.dp, height = 240.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(BorderStroke(1.dp, Color.Gray))
                            .background(Color(0xFF2B59C3))
                            .padding(8.dp)
                            .clickable {
                                navController.navigate("detailSerie/${serie.id}")
                            },
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val imageUrl = "https://image.tmdb.org/t/p/w500" + serie.poster_path
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = serie.name,
                            modifier = Modifier
                                .width(140.dp)
                                .height(160.dp)
                                .padding(4.dp),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = serie.name ?: "Titre inconnu",
                            fontSize = 16.sp,
                            color = Color.White,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        )
    }
}

