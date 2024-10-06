package com.example.courses.data

import androidx.compose.ui.res.stringResource
import com.example.courses.R
import com.example.courses.model.Topic

object DataSource {
    val topics = listOf<Topic>(
        Topic(R.string.architecture, 58, R.drawable.architecture, R.string.architecture_description, 3),
        Topic(R.string.automotive, 69, R.drawable.automotive, R.string.automotive_description, 3),
        Topic(R.string.business, 69, R.drawable.business, R.string.business_description, 2),
        Topic(R.string.crafts, 121, R.drawable.crafts, R.string.crafts_description, 2),
        Topic(R.string.culinary, 118, R.drawable.culinary, R.string.culinary_description, 2),
        Topic(R.string.design, 423, R.drawable.design, R.string.design_description, 3),
        Topic(R.string.drawing, 69, R.drawable.drawing, R.string.drawing_description, 3),
        Topic(R.string.ecology, 69, R.drawable.ecology, R.string.ecology_description, 3),
        Topic(R.string.engineering, 69, R.drawable.engineering, R.string.engineering_description, 3),
        Topic(R.string.fashion, 92, R.drawable.fashion, R.string.fashion_description, 2),
        Topic(R.string.film, 165, R.drawable.film, R.string.film_description, 2),
        Topic(R.string.gaming, 164, R.drawable.gaming, R.string.game_description, 2),
        Topic(R.string.geology, 69, R.drawable.geology, R.string.geology_description, 3),
        Topic(R.string.history, 69, R.drawable.history, R.string.history_description, 3),
        Topic(R.string.journalism, 69, R.drawable.journalism, R.string.journalism_description, 2),
        Topic(R.string.law, 69, R.drawable.law, R.string.law_description, 3),
        Topic(R.string.lifestyle, 305, R.drawable.lifestyle, R.string.lifestyle_description, 1),
        Topic(R.string.music, 212, R.drawable.music, R.string.music_description, 2),
        Topic(R.string.painting, 172, R.drawable.painting, R.string.painting_description, 2),
        Topic(R.string.photography, 321, R.drawable.photography, R.string.photography_description, 2),
        Topic(R.string.physics, 69, R.drawable.physics, R.string.physics_description, 3),
        Topic(R.string.tech, 118, R.drawable.tech, R.string.tech_description, 3)
    )
}