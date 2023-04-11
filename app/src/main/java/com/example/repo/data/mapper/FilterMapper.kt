package com.example.repo.data.mapper

import com.example.repo.data.model.FilterEntity
import com.example.repo.domain.model.Filter

interface FilterMapper : Mapper<FilterEntity, Filter> {

    override fun mapFromEntity(type: FilterEntity): Filter

    override fun mapToEntity(type: Filter): FilterEntity
}