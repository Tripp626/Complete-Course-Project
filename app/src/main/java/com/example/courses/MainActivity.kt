package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.courses.data.DataSource
import com.example.courses.model.Topic
import com.example.courses.ui.theme.CoursesTheme

enum class Screens(){
    MainGrid,
    Description
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoursesTheme {
                    ChangeScreen()

            }
        }
    }
}



@Composable
fun ChangeScreen(
    navHostController: NavHostController = rememberNavController(),
    courseViewModel: CourseViewModel = viewModel()
){
    NavHost(
        navController = navHostController,
        startDestination = Screens.MainGrid.name
    ) {
        composable(
            route = Screens.MainGrid.name
        ){
        CoursesList(
            courseViewModel = courseViewModel,
            onClick = {course, value ->
                courseViewModel.selectCourse(course, value)
                navHostController.navigate(Screens.Description.name)
            }
        )
        }

        composable(
            route = Screens.Description.name,
        ){
            CourseDescription(
                courseViewModel = courseViewModel,
                onBackSelected = { newValue ->
                    // Set the selected value and navigate back
                    courseViewModel.setSelectedValue(newValue)
                    navHostController.popBackStack()
                }
            )
        }
    }
}



