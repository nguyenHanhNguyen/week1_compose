package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.ui.theme.MyTheme

class PetDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pet = intent.extras?.getParcelable<Pet>(KEY_PET)
        setContent {
            MyTheme {
                pet?.let {
                    PetDetailScreen(it)
                }

            }
        }
    }
}

@Composable
fun PetDetailScreen(pet: Pet) {
    Surface(color = MaterialTheme.colors.background) {
        PetDetail(pet)
    }
}

@Composable
fun PetDetail(pet: Pet) {
    Column {
        Image(
            painter = painterResource(id = pet.avatar),
            contentDescription = "Dog",
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(250.dp),
            contentScale = ContentScale.FillWidth
        )

        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = pet.name, fontWeight = FontWeight.Bold, fontSize = 32.sp
            )
            Row {
                Text(
                    text = pet.breed,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(end = 4.dp)

                )
                if (pet.gender.startsWith("F")) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_female),
                        contentDescription = "female",
                        modifier = Modifier
                            .size(20.dp)
                            .padding(start = 4.dp)
                            .align(Alignment.CenterVertically),
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_male),
                        contentDescription = "male",
                        modifier = Modifier
                            .size(20.dp)
                            .padding(start = 4.dp)
                            .align(Alignment.CenterVertically),
                    )
                }

            }
            Text(
                text = "Description", fontWeight = FontWeight.Bold,
                style = TextStyle(textDecoration = TextDecoration.Underline)
            )
            Text(text = pet.desc, fontWeight = FontWeight.Light)
            Spacer(modifier = Modifier.padding(10.dp))
            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Adopt Me")
            }
        }
    }
}
