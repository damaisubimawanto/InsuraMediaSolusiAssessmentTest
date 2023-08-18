package com.damai.accordinnovations.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.damai.base.BaseViewModel
import com.damai.base.coroutines.DispatcherProvider
import com.damai.base.extensions.asLiveData
import com.damai.base.networks.Resource
import com.damai.domain.models.MovieGenreModel
import com.damai.domain.models.MovieItemModel
import com.damai.domain.usecases.GetMovieGenreListUseCase
import kotlinx.coroutines.launch

/**
 * Created by damai007 on 18/August/2023
 */
class MainViewModel(
    app: Application,
    private val getMovieGenreListUseCase: GetMovieGenreListUseCase,
    private val dispatcher: DispatcherProvider
) : BaseViewModel(app = app) {

    //region Live Data
    private val _genreListLiveData = MutableLiveData<List<MovieGenreModel>>()
    val genreListLiveData = _genreListLiveData.asLiveData()

    private val _movieListLiveData = MutableLiveData<List<MovieItemModel>>()
    val movieListLiveData = _movieListLiveData.asLiveData()
    //endregion `Live Data`

    fun getGenreList() {
        viewModelScope.launch(dispatcher.io()) {
            getMovieGenreListUseCase().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.model?.list?.let { response ->
                            _genreListLiveData.postValue(response)
                        }
                    }
                    is Resource.Error -> {

                    }
                }
            }
        }
    }
}