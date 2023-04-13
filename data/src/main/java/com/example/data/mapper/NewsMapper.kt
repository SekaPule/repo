package com.example.data.mapper

import com.example.data.model.News
import com.example.data.model.NewsEntity
import javax.inject.Inject

class NewsMapper @Inject constructor() : Mapper<NewsEntity, News> {
    override fun mapFromEntity(type: NewsEntity): News = News(
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

    override fun mapToEntity(type: News): NewsEntity = NewsEntity(
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