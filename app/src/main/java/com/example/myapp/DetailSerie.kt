import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.myapp.MainViewModel

@Composable
fun DetailSerie(modifier: Modifier = Modifier, navController: NavController, serieId: Int) {
    val viewModel: MainViewModel = viewModel()
    val detailSerieState = viewModel.detailSerie.collectAsState()
    val detailSerie = detailSerieState.value

    LaunchedEffect(serieId) {
        if (serieId != 0) {
            viewModel.serieDetail(serieId)
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Détail Série",
            fontSize = 38.sp,
            color = Color.Black,
            modifier = Modifier.padding(end = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = detailSerie?.name ?: "Titre inconnu",
                fontSize = 38.sp,
                color = Color.Black,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
        val imageUrl = "https://image.tmdb.org/t/p/w500" + detailSerie?.backdrop_path
        AsyncImage(
            model = imageUrl,
            contentDescription = detailSerie?.name,
            modifier = Modifier.padding(end=16.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Synopsis",
                fontSize = 38.sp,
                color = Color.Black,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
        Text(
            text = detailSerie?.overview ?: "description inconnue",
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(end = 8.dp)
        )
    }
}
