package com.example.studenthardlife

data class Task(val title: String, val subject: String, val description: String) {
    var id: Int = 0

    constructor(id: Int, title: String, subject: String, description: String) : this(title, subject, description) {
        this.id = id
    }
}
