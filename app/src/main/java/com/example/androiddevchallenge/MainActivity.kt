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

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.model.Pet
import com.example.androiddevchallenge.ui.theme.MyTheme

var petList = listOf<Pet>()
lateinit var onClick: (petItem: Pet) -> Unit
var KEY_PET = "pet_detail"

class MainActivity : AppCompatActivity() {

    private fun createPetList() {
        val pet = Pet(
            "KiKi",
            "Golden Retriever",
            "Female",
            "Certified best loyal friend" +
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ",
            R.drawable.dog_0
        )
        val pet1 = Pet(
            "Kafka",
            "Corgi",
            "Male",
            "Short, cute legs" +
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ",
            R.drawable.dog_1
        )
        val pet2 = Pet(
            "AiBo",
            "Corgi",
            "Female",
            "Cute and loyal"
                    +
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ",
            R.drawable.dog_2
        )
        val pet3 = Pet(
            "Mame",
            "Shiba",
            "Male",
            "Wink that melts your heart"
                    +
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ",
            R.drawable.dog_3
        )
        val pet4 = Pet(
            "Kay",
            "Shiba",
            "Male",
            "Beautiful tri color fur"
                    +
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ",
            R.drawable.dog_4
        )
        val pet5 = Pet(
            "Dui",
            "Mutt",
            "Male",
            "Super Idol, Instagram famous" +
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ",
            R.drawable.dog_5
        )
        val pet6 = Pet(
            "Albert",
            "Golden Retriever",
            "Male",
            "Calm yet energetic" +
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ",
            R.drawable.dog_6
        )
        val pet7 = Pet(
            "Charles",
            "German Shepherd",
            "Female",
            "Best friend you ever have"
                    +
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ",
            R.drawable.dog_7
        )
        val pet8 = Pet(
            "Tini",
            "Chihuahua",
            "Female",
            "Cute and small"
                    +
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ",
            R.drawable.dog_8
        )
        val pet9 =
            Pet(
                "Blue",
                "Golden Retriever",
                "Male",
                "Will make you smile all day"
                        +
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ",
                R.drawable.dog_9
            )
        petList = listOf(pet, pet1, pet2, pet3, pet4, pet5, pet6, pet7, pet8, pet9)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createPetList()
        onClick = {
            launchPetDetailActivity(it)
        }
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }

    private fun launchPetDetailActivity(pet: Pet) {
        val intent = Intent(this, PetDetailActivity::class.java)
        intent.putExtra(KEY_PET, pet)
        startActivity(intent)
    }
}

@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        PetList(petList = petList, onClick = onClick)
    }
}

@Composable
fun PetList(petList: List<Pet>, onClick: (petItem: Pet) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(petList) { pet ->
            PetItem(pet, onClick = onClick)
        }
    }
}

@Composable
fun PetItem(pet: Pet, onClick: (petItem: Pet) -> Unit) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .clickable(onClick = { onClick(pet) })
            .fillMaxWidth(1f),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .background(MaterialTheme.colors.surface)
        ) {
            Image(
                painter = painterResource(id = pet.avatar),
                contentDescription = "image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .border(1.dp, MaterialTheme.colors.secondary, shape = CircleShape)
                    .clip(shape = CircleShape)
                    .size(64.dp, 64.dp)

            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Row {
                    Text(
                        pet.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    if (pet.gender.startsWith("F")) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_female),
                            contentDescription = "Female",
                            modifier = Modifier
                                .size(20.dp)
                                .padding(start = 4.dp)
                                .align(Alignment.CenterVertically),
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.ic_male),
                            contentDescription = "Male",
                            modifier = Modifier
                                .size(20.dp)
                                .padding(start = 4.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }
                }
                Text(
                    pet.breed,
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
