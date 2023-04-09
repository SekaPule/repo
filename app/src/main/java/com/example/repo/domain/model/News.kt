package com.example.repo.domain.model

data class News(
    val id: Int,
    val category: List<String>?,
    val title: String?,
    val organization: String?,
    val date: String?,
    val location: String?,
    val phoneNumbers: List<String>?,
    val titleDescription: String?,
    val description: String?,
    val subDescription: String?,
    var isChecked: Boolean = false,
)