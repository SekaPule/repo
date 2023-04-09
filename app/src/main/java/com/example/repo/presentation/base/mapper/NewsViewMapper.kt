package com.example.repo.presentation.base.mapper

import com.example.repo.domain.model.News
import com.example.repo.presentation.base.Mapper
import com.example.repo.presentation.base.model.NewsView
import kotlinx.datetime.*
import javax.inject.Inject

class NewsViewMapper @Inject constructor() : Mapper<News, NewsView> {
    override fun mapFromDomainModel(type: News): NewsView = NewsView(
        id = type.id,
        category = type.category,
        title = type.title,
        organization = type.organization,
        date = type.date,
        location = type.location,
        phoneNumbers = type.phoneNumbers,
        titleDescription = type.titleDescription,
        description = type.description,
        subDescription = type.subDescription,
        daysLeftText = "осталось ${type.date?.toLocalDate()?.let { 
            Clock.System.todayIn(TimeZone.currentSystemDefault()).daysUntil(it) 
        }} дней (${type.date?.toLocalDate()})"
    )

    override fun mapToDomainModel(type: NewsView): News = News(
        id = type.id,
        category = type.category,
        title = type.title,
        organization = type.organization,
        date = type.date,
        location = type.location,
        phoneNumbers = type.phoneNumbers,
        titleDescription = type.titleDescription,
        description = type.description,
        subDescription = type.subDescription
    )
}