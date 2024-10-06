package com.example.courses

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.courses.model.Topic

class CourseViewModel: ViewModel() {
    private val _selectedCourse = MutableLiveData<Topic?>()
    val selectedCourse: LiveData<Topic?> get() = _selectedCourse

    private val _selectedValue = MutableLiveData<Int>()
    val selectedValue: LiveData<Int> get() = _selectedValue


    // Function to set the selected course
    fun selectCourse(course: Topic, value: Int) {
        _selectedCourse.value = course
        _selectedValue.value = value
    }

    fun resetValue(){
        _selectedValue.value = 0
    }

    fun setSelectedValue(value: Int) {
        _selectedValue.value = value
    }
}