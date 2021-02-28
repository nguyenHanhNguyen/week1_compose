/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
                    PetDetailScreen(it) { onBackPressed() }
                }
            }
        }
    }
}

@Composable
fun PetDetailScreen(pet: Pet, backPressed: () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Scaffold(topBar = { AppBar(pet = pet, backPressed) }) {
            PetDetail(pet)
        }
    }
}

@Composable
private fun AppBar(pet: Pet, backPressed: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .clickable(onClick = backPressed)
            )
        },
        title = {
            Text(text = "Hello, I'm ${pet.name}")
        },
        backgroundColor = MaterialTheme.colors.primary
    )
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
