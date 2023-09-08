package com.damai.mandiribank.ui.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.damai.base.BaseViewModel
import com.damai.base.coroutines.DispatcherProvider
import com.damai.base.extensions.asLiveData
import com.damai.base.extensions.orZero
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
    private var totalPage = 1
    var isMovieListReset = false

    val isStopLoadMore: Boolean
        get() = currentPage >= totalPage
    //endregion `Variables`

    fun getGenreList() {
        viewModelScope.launch(dispatcher.io()) {
            getMovieGenreListUseCase().collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.model?.list?.let { response ->
                            response.firstOrNull()?.isSelected = true
                            _genreListLiveData.postValue(response)
                            selectedGenreName = response.firstOrNull()?.id.orZero().toString()
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
                            var currentData = _movieListLiveData.value?.toMutableList()
                            if (currentData == null) {
                                currentData = mutableListOf()
                            }
                            currentData.removeAll {
                                it is MovieUiModel.MovieFooterLoading
                            }
                            response.forEach {
                                currentData.add(MovieUiModel.MovieItemWrap(it))
                            }

                            resource.model?.let { model ->
                                totalPage = model.totalPage.orZero()
                                if (model.currentPage.orZero() < model.totalPage.orZero()) {
                                    currentData.add(MovieUiModel.MovieFooterLoading)
                                }
                            }

                            _movieListLiveData.postValue(currentData.toList())
                        }
                    }
                    is Resource.Error -> {

                    }
                }
            }
        }
    }

    fun changeGenre(
        genreId: Int,
        changedCallback: (selectedPosition: Int) -> Unit
    ) {
        val currentData = requireNotNull(_genreListLiveData.value)

        /* Get index of currently selected genre. */
        val currentSelectedIndex = currentData.indexOfFirst {
            it.isSelected
        }

        /* Get index of new selected genre. */
        val newSelectedIndex = currentData.indexOfFirst {
            it.id == genreId
        }

        if (newSelectedIndex > -1 && newSelectedIndex != currentSelectedIndex) {
            /* Set previous selected genre to be not-selected. */
            currentData[currentSelectedIndex].isSelected = false

            /* Set new genre to be selected. */
            currentData[newSelectedIndex].let {
                it.isSelected = true
                selectedGenreName = it.id.toString()
                isMovieListReset = true
                resetMovieList()
                changedCallback.invoke(newSelectedIndex)
                getMovieList()
            }
        }

        currentData.let(_genreListLiveData::postValue)
    }

    fun changeCurrentPage(newPage: Int) {
        currentPage = newPage
    }

    private fun resetMovieList() {
        currentPage = 1
        _movieListLiveData.value = listOf()
    }
}