package com.damai.accordinnovations.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.damai.base.BaseViewModel
import com.damai.base.coroutines.DispatcherProvider
import com.damai.base.extensions.asLiveData
import com.damai.base.networks.Resource
import com.damai.domain.models.MovieGenreModel
import com.damai.domain.models.MovieItemByGenreRequestModel
import com.damai.domain.models.MovieUiModel
import com.damai.domain.usecases.GetMovieGenreListUseCase
import com.damai.domain.usecases.GetMovieItemListUseCase
import kotlinx.coroutines.launch

/**
 * Created by damai007 on 18/August/2023
 */
class MainViewModel(
    app: Application,
    private val getMovieGenreListUseCase: GetMovieGenreListUseCase,
    private val getMovieItemListUseCase: GetMovieItemListUseCase,
    private val dispatcher: DispatcherProvider
) : BaseViewModel(app = app) {

    //region Live Data
    private val _genreListLiveData = MutableLiveData<List<MovieGenreModel>>()
    val genreListLiveData = _genreListLiveData.asLiveData()

    private val _movieListLiveData = MutableLiveData<List<MovieUiModel>>()
    val movieListLiveData = _movieListLiveData.asLiveData()
    //endregion `Live Data`

    //region Variables
    private var selectedGenreName = ""
    private var currentPage = 1
    //endregion `Variables`

    fun getGenreList() {
        viewModelScope.launch(dispatcher.io()) {
            getMovieGenreListUseCase().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.model?.list?.let { response ->
                            _genreListLiveData.postValue(response)
                            selectedGenreName = response.firstOrNull()?.name.orEmpty()
                            getMovieList()
                        }
                    }
                    is Resource.Error -> {

                    }
                }
            }
        }
    }

    fun getMovieList() {
        viewModelScope.launch(dispatcher.io()) {
            val requestModel = MovieItemByGenreRequestModel(
                genreName = selectedGenreName,
                page = currentPage
            )
            getMovieItemListUseCase(requestModel).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.model?.list?.let { response ->
                            val newData = mutableListOf<MovieUiModel>()
                            response.forEach {
                                newData.add(MovieUiModel.MovieItemWrap(it))
                            }
                            newData.add(MovieUiModel.MovieFooterLoading)
                            _movieListLiveData.postValue(newData)
                        }
                    }
                    is Resource.Error -> {

                    }
                }
            }
        }
    }
}