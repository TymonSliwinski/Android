package com.example.studentcrime

data class CrimeData(val id: Int, val crimeName: String, val crimeDescription: String, val studentId: Int, val isSolved: Boolean) {
}

class sampleData() {
    val names = arrayOf("Crime 1","Crime 2","Crime 3","Crime 4","Crime 5","Crime 6","Crime 7","Crime 8","Crime 9","Crime 10")
    val descriptions = arrayOf(
        "Description of crime 1",
        "Description of crime 2",
        "Description of crime 3",
        "Description of crime 4",
        "Description of crime 5",
        "Description of crime 6",
        "Description of crime 7",
        "Description of crime 8",
        "Description of crime 9",
        "Description of crime 10"
    )
    val studentIds = arrayOf(100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110)
    val areSolved = arrayOf(true, false, true, false, false, false, true, true, false, false)

    val data: ArrayList<CrimeData> = ArrayList((0..9).map { CrimeData(it, names[it], descriptions[it], studentIds[it], areSolved[it]) })
}