package com.example.data.mapper

import com.example.data.model.Filter
import com.example.data.model.FilterEntity

interface FilterMapper : Mapper<FilterEntity, Filter> {

    override fun mapFromEntity(type: FilterEntity): Filter

    override fun mapToEntity(type: Filter): FilterEntity
}