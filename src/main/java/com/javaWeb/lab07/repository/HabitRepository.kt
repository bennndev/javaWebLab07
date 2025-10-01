package com.javaWeb.lab07.repository

import com.javaWeb.lab07.model.Habit
import com.javaWeb.lab07.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface HabitRepository : JpaRepository<Habit?, Long?> {
    fun findByUser(user: User?): MutableList<Habit?>?
}