import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapp.ProfilScreen

@Composable
fun Film(modifier: Modifier = Modifier, navController: NavController) {
    Column {
        Button(onClick = { navController.navigate(ProfilScreen()) }) {
            Text(
                text = "Retour",
                modifier = modifier
            )
        }
        Text(
            text = "Page 2",
            modifier = modifier,
            fontSize = 32.sp
        )
    }

}