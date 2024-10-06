package com.example.courses

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.courses.data.DataSource
import com.example.courses.model.Topic



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CoursesList(
    courseViewModel: CourseViewModel,
    onClick:(Topic, Int) -> Unit,
    topics: List<Topic> = DataSource.topics
) {
    val selectedValue by courseViewModel.selectedValue.observeAsState()
    var totalCreditUnits: Int = 0

    selectedValue?.let {
    totalCreditUnits = it
    }

    Column(modifier = Modifier
        .statusBarsPadding()
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        val deactivatedButtonColor : ButtonColors = ButtonColors(containerColor = Color.Gray, contentColor = Color.White, disabledContentColor = Color.White, disabledContainerColor = Color.Gray)
        val activatedButtonColor : ButtonColors = ButtonColors(containerColor = Color.Blue, contentColor = Color.White, disabledContentColor = Color.White, disabledContainerColor = Color.Blue)

        Row() {
            Spacer(modifier = Modifier
                .width(340.dp)
                .height(20.dp))
        }

        Surface(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp), shadowElevation = 0.dp) {
            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .fillMaxWidth()
                    .background(color = Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.total_credit_units) + " " + totalCreditUnits.toString() + "/25",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

        }


        LazyColumn(modifier = Modifier
            .padding(0.dp)
            .statusBarsPadding()
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .statusBarsPadding()
                        .height(900.dp)
                ) {
                    items(topics) { topic ->
                        CoursesCard(goToDescription = onClick, topic = topic,totalCreditUnits = totalCreditUnits, modifier = Modifier.padding(8.dp))
                    }
                }
            }

            item {
                val context = LocalContext.current

                Button(onClick = {
                    if (totalCreditUnits >= 25) {
                        Toast.makeText(context, "Good Luck on Your Studies", Toast.LENGTH_SHORT).show();
                        courseViewModel.resetValue() }
                                 }
                    , colors = if (totalCreditUnits > 25) activatedButtonColor else deactivatedButtonColor) {
                    Text(text = "Submit")
                }
            }

            item {
                Text(
                    text = stringResource(id = R.string.disclaimer)
                    , fontSize = 15.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }

            item {
                Spacer(modifier = Modifier
                    .width(340.dp)
                    .height(40.dp))
            }
        }
    }
}


@Composable
fun CoursesCard(goToDescription:(Topic, Int) -> Unit, topic: Topic,totalCreditUnits: Int, modifier: Modifier = Modifier){
    Card(modifier = modifier
        .height(68.dp)
        .clip(RoundedCornerShape(12.dp))
        .clickable { goToDescription(topic, totalCreditUnits) }) {
        Row(){
            Image(
                painter = painterResource(id = topic.imageResourceId),
                contentDescription = stringResource(id = topic.stringResourceId),
                modifier = Modifier.size(68.dp)
            )

            Column {
                Text(
                    text = stringResource(id = topic.stringResourceId),
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp, bottom = 0.dp),
                    fontSize = 15.sp
                )

                Row(modifier = Modifier.height(28.dp), verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.circle_60),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp)
                    )

                    Text(
                        text = topic.courseNumber.toString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }
    }
}

