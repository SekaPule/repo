package com.example.search_feature.presentation.mapper

import com.example.data.model.News
import com.example.search_feature.presentation.model.NewsView

interface NewsViewMapper : Mapper<News, NewsView> {

    override fun mapFromDomainModel(type: News): NewsView

    override fun mapToDomainModel(type: NewsView): News
}