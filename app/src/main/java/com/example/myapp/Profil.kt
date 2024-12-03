import androidx.compose.foundation.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.example.myapp.FilmsScreen
import com.example.myapp.PlaylistsScreen
import com.example.myapp.R

@Composable
fun Profil(
    firstName: String,
    lastName: String,
    modifier: Modifier = Modifier,
    windowClass: WindowSizeClass,
    navController: NavController
) {
    when (windowClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Box(
                        modifier = Modifier.height(300.dp),
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Image(
                                painterResource(R.drawable.istockphoto12),
                                contentDescription = "Un paysage",
                                modifier = Modifier
                                    .size(150.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                            Column(
                                modifier = Modifier.height(80.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly,
                            ) {
                                Text(
                                    text = "$firstName $lastName",
                                    modifier = modifier,
                                    fontSize = 32.sp
                                )
                                Text(
                                    text = "Étudiant en BUT MMI à Castres",
                                    modifier = modifier
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier.height(80.dp),
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.email),
                                    contentDescription = "icone email",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .padding(end = 8.dp)
                                )
                                Text(
                                    text = "thomas.crqr@gmail.com",
                                    modifier = modifier,
                                )
                            }
                            Row(
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(R.drawable.linkedin),
                                    contentDescription = "icone email",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .padding(end = 8.dp)
                                )
                                Text(
                                    text = "$firstName $lastName",
                                    modifier = modifier,
                                )
                            }
                        }
                    }
                    Button(onClick = { navController.navigate(PlaylistsScreen()) }) {
                        Text(
                            text = "Démarrer",
                            modifier = modifier
                        )
                    }
                }
            }
        }
        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            painterResource(R.drawable.istockphoto12),
                            contentDescription = "Un paysage",
                            modifier = Modifier
                                .size(150.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly,
                        ) {
                            Text(
                                text = "$firstName $lastName",
                                modifier = modifier,
                                fontSize = 32.sp
                            )
                            Text(
                                text = "Étudiant en BUT MMI à Castres",
                                modifier = modifier
                            )
                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                painter = painterResource(R.drawable.email),
                                contentDescription = "icone email",
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(end = 8.dp)
                            )
                            Text(
                                text = "thomas.crqr@gmail.com",
                                modifier = modifier,
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(R.drawable.linkedin),
                                contentDescription = "icone email",
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(end = 8.dp)
                            )
                            Text(
                                text = "$firstName $lastName",
                                modifier = modifier,
                            )
                        }
                        Button(onClick = { navController.navigate(PlaylistsScreen()) }) {
                            Text(
                                text = "Démarrer",
                                modifier = modifier
                            )
                        }
                    }
                }
            }
        }
    }
}
