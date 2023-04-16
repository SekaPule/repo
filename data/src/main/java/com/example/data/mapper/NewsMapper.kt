package com.example.data.mapper

import com.example.data.model.News
import com.example.data.model.NewsEntity

interface NewsMapper : Mapper<NewsEntity, News>{

    override fun mapFromEntity(type: NewsEntity): News

    override fun mapToEntity(type: News): NewsEntity
}
