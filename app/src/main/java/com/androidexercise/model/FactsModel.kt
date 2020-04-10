package com.androidexercise.model

data class ResponseFacts(val title: String, val rows: ArrayList<Rows>)

data class Rows(val title: String, val description: String, val imageHref: String)