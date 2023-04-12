package com.example.repo.data.mapper

import com.example.repo.data.model.NewsEntity
import com.example.repo.domain.model.News

interface NewsMapper : Mapper<NewsEntity, News> {

    override fun mapFromEntity(type: NewsEntity): News

    override fun mapToEntity(type: News): NewsEntity
}