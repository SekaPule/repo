package com.example.repo.data.mapper

import com.example.repo.data.model.NewsEntity
import com.example.repo.domain.model.News
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