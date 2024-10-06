package com.example.courses

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.courses.data.DataSource
import com.example.courses.model.Topic

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CourseDescription(
    courseViewModel: CourseViewModel,
    onBackSelected: (Int) -> Unit,
)
{
    val course = courseViewModel.selectedCourse.observeAsState()
    val value = courseViewModel.selectedValue.observeAsState()

    var totalCreditUnitsDisplayed by remember {
        mutableIntStateOf(0)
    }

    value.value?.let {
        totalCreditUnitsDisplayed = it
    }

    course.value?.let {


        val creditUnits: Int = it.creditUnits
        val courseImage: Int = it.imageResourceId
        val courseName: Int = it.stringResourceId
        val courseNumber: Int = it.courseNumber
        val courseDescription: Int = it.courseDescription


        var added by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
        }

        LazyColumn(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            stickyHeader {
                Column(
                    modifier = Modifier
                        .statusBarsPadding()
                        .fillMaxWidth()
                        .height(100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(shadowElevation = 15.dp) {
                        Column(
                            modifier = Modifier
                                .statusBarsPadding()
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row() {
                                Image(
                                    painter = painterResource(id = R.drawable.go_back_2),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(45.dp)
                                        .clip(CircleShape)
                                        .clickable {
                                            onBackSelected(totalCreditUnitsDisplayed)
                                        }
                                )

                                Spacer(modifier = Modifier.width(340.dp))
                            }

                            Text(
                                text = stringResource(id = R.string.total_credit_units) + " " + totalCreditUnitsDisplayed.toString() + "/25",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )
                        }
                    }
                }

            }

            item{

            }

            item {


            }

            item {
                Surface(
                    shadowElevation = 15.dp
                ) {
                    Image(
                        painter = painterResource(id = courseImage),
                        contentDescription = null,
                        modifier = Modifier
                            .size(300.dp)
                            .padding(16.dp)
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.padding(6.dp))
            }

            item {
                Text(
                    text = stringResource(id = courseName),
                    fontSize = 35.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(8.dp)
                )
            }

            item {
                Text(
                    text = stringResource(id = courseDescription),
                    //textAlign = androidx.compose.ui.text.style.TextAlign.Justify,
                    overflow = TextOverflow.Visible,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }

            item {
                Spacer(modifier = Modifier.padding(6.dp))
            }

            item{
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Surface(tonalElevation = 15.dp, shape = RoundedCornerShape(20.dp)) {
                        Column {
                            Text(
                                text = stringResource(id = R.string.number_of_students) + " " + courseNumber.toString(),
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(8.dp)
                            )

                            Text(
                                text = stringResource(id = R.string.credit_units) + " " + creditUnits.toString(),
                                fontSize = 25.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                }
            }

            item {
                Spacer(modifier = Modifier.padding(10.dp))
            }

            item {

                val context = LocalContext.current
                Box(
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.purple_500),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clip(RoundedCornerShape(20.dp))
                        .width(200.dp)
                        .height(60.dp)
                        .clickable {
                            if (!added) {
                                added = true
                                totalCreditUnitsDisplayed += creditUnits
                                Toast
                                    .makeText(context, "Course has been added", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                added = false
                                totalCreditUnitsDisplayed -= creditUnits
                                Toast
                                    .makeText(
                                        context,
                                        "Course has been removed",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        },
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = if (added) stringResource(id = R.string.remove_course) else stringResource(id = R.string.add_course),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.padding(20.dp))
            }

            item {
                Spacer(modifier = Modifier.padding(20.dp))
            }
        }

    }



}

@Preview
@Composable
fun CourseDescriptionPreview(){
    //CourseDescription()
}
