import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShowChart
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
import coil.compose.rememberImagePainter
import com.example.myapp.MainViewModel

@Composable
fun Films(modifier: Modifier = Modifier, navController: NavController, motCle: String) {
    val viewModel: MainViewModel = viewModel()
    val moviesState = viewModel.movies.collectAsState()
    val movies = moviesState.value

    LaunchedEffect(motCle) {
        if (motCle.isBlank()) {
            viewModel.weekMovies()
        } else {
            viewModel.searchMovies(motCle)
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
                text = "Films",
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
            columns = GridCells.Adaptive(minSize = 180.dp),
            modifier = Modifier.padding(8.dp),
            content = {
                items(movies.size) { index ->
                    val movie = movies[index]
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(width = 160.dp, height = 240.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(BorderStroke(1.dp, Color.Gray))
                            .background(Color(0xFF2B59C3))
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = rememberImagePainter(
                                movie.poster_path?.let {
                                    "https://image.tmdb.org/t/p/w500$it"
                                } ?: ""
                            ),
                            contentDescription = "Affiche du film ${movie.original_title}",
                            modifier = Modifier
                                .width(140.dp)
                                .height(160.dp)
                                .padding(4.dp),
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            text = movie.original_title ?: "Titre inconnu",
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
