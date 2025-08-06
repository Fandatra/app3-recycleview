package com.smkth.app3_recycleview.utils

import com.smkth.app3_recycleview.model.Student

object DummyData{
    fun getStudentList(): List<Student> {
        return listOf(
            Student("Andi", "1001", "XII TJKT 1"),
            Student("Budi", "1002", "XII TJKT 1"),
            Student("Citra", "1003", "XII TJKT 1"),
            Student("Doni", "1004", "XII TJKT 1"),
            Student("Eka", "1005", "XII TJKT 1"),
            Student("Fani", "1006", "XII TJKT 1"),
            Student("Gilang", "1007", "XII TJKT 1"),
            Student("Hani", "1008", "XII TJKT 1"),
            Student("Iqbal", "1009", "XII TJKT 1"),
            Student("Joko", "1010", "XII TJKT 1")
        )
    }
}