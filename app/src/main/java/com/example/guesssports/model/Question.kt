package com.example.guesssports.model

data class Question(
    var question: Int? = null,
    var ans1: Int? = null,
    var ans2: Int? = null,
    var ansTitle1: String? = null,
    var ansTitle2: String? = null,
    var trueAnsTitle: String? = null)