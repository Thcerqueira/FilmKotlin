import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.myapp.MainViewModel
import com.example.myapp.ProfilScreen

@Composable
fun Film(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel: MainViewModel = viewModel()

    val moviesState = viewModel.movies.collectAsState()

    val movies = moviesState.value
    
    if (movies.isEmpty()) {
        viewModel.weekMovies()
    }

    Column {
        Button(onClick = { navController.navigate("ProfilScreen") }) {
            Text(
                text = "Retour",
                modifier = modifier
            )
        }
        Text(
            text = "Page 2",
            modifier = modifier,
            fontSize = 32.sp,
        )

        LazyColumn {
            items(movies) { movie ->
                Text(text = movie.original_title)
            }
        }
    }

}