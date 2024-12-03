import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import com.example.myapp.ActeursScreen
import com.example.myapp.MainViewModel
import com.example.myapp.SeriesScreen

@Composable
fun DetailActeur(modifier: Modifier = Modifier, navController: NavController, actorId: Int) {
    val viewModel: MainViewModel = viewModel()
    val detailActeurState = viewModel.detailActor.collectAsState()
    val detailActeur = detailActeurState.value

    println("Détails acteur : $detailActeur")

    LaunchedEffect(actorId) {
        if (actorId != 0) {
            viewModel.actorDetail(actorId)
        }
    }

    Column(modifier = Modifier
        .padding(16.dp)
        .verticalScroll(rememberScrollState())
        ) {
        Button(
            onClick = { navController.navigate(ActeursScreen()) },
            modifier = modifier
        ) {
            Row {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Retour",
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = detailActeur?.name ?: "Nom inconnu",
                fontSize = 38.sp,
                color = Color.Black,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
        val imageUrl = "https://image.tmdb.org/t/p/w500" + detailActeur?.profile_path
        AsyncImage(
            model = imageUrl,
            contentDescription = detailActeur?.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(4.dp),
            contentScale = ContentScale.Crop
        )
        if (!detailActeur?.biography.isNullOrBlank()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Biographie",
                    fontSize = 38.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
            Text(
                text = detailActeur?.biography ?: "Biographie inconnue",
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
        if (!detailActeur?.birthday.isNullOrBlank()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = detailActeur?.birthday ?: "Date de naissance inconnue",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }
        if (!detailActeur?.place_of_birth.isNullOrBlank()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = detailActeur?.place_of_birth ?: "Lieu de naissance inconnu",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }
        }


    }
}

