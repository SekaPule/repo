package com.example.repo.presentation.base.mapper

import com.example.repo.domain.model.News
import com.example.repo.presentation.base.Mapper
import com.example.repo.presentation.base.model.NewsView

interface NewsViewMapper : Mapper<News, NewsView> {

    override fun mapFromDomainModel(type: News): NewsView

    override fun mapToDomainModel(type: NewsView): News
}