package com.example

import org.ktorm.database.Database

val database = Database.connect("jdbc:postgresql://localhost:5432/postgres", user = "hannes", password = "secret")
