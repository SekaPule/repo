package com.example.repo.data.repository

import com.example.repo.data.DataProvider
import com.example.repo.data.internet.Api
import com.example.repo.model.News
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class Repository(
    private val api: Api,
    private val dataProvider: DataProvider
) {
    fun getNews(): Single<List<News>> {
        return Single.create { emitter ->
            api.getNewsFromServer()
                .subscribeOn(Schedulers.io())
                .subscribe({ newsList ->
                    emitter.onSuccess(newsList.news)
                }, {
                    emitter.onSuccess(dataProvider.getNewsFromAssets())
                })
        }
    }

    fun getFilters(): Single<String> {
        return Single.create { emitter->
            api.getFiltersFromServer()
                .subscribeOn(Schedulers.io())
                .subscribe({ json->
                    emitter.onSuccess(json)
                },{
                    emitter.onSuccess(dataProvider.getFilterItemsFromAssetsJson())
                })
        }
    }
}