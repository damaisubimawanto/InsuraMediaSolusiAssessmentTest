package com.damai.accordinnovations.ui.detail

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.damai.base.BaseViewModel
import com.damai.base.coroutines.DispatcherProvider
import com.damai.base.extensions.asLiveData
import com.damai.base.networks.Resource
import com.damai.base.utils.Constants.ARGS_MOVIE_ID
import com.damai.domain.models.MovieItemModel
import com.damai.domain.usecases.GetMovieDetailsUseCase
import kotlinx.coroutines.launch

/**
 * Created by damai007 on 19/August/2023
 */
class MovieDetailViewModel(
    app: Application,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val dispatcher: DispatcherProvider
) : BaseViewModel(app = app) {

    //region Live Data
    private val _movieDetailsLiveData = MutableLiveData<MovieItemModel>()
    val movieDetailsLiveData = _movieDetailsLiveData.asLiveData()
    //endregion `Live Data`

    //region Variables
    private var movieId = 0
    //endregion `Variables`

    fun initFromIntent(bundle: Bundle?) {
        bundle?.let {
            if (it.containsKey(ARGS_MOVIE_ID)) {
                movieId = it.getInt(ARGS_MOVIE_ID)
            }
        }
    }

    fun getMovieDetails() {
        viewModelScope.launch(dispatcher.io()) {
            getMovieDetailsUseCase(movieId).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.model?.details?.let { response ->
                            _movieDetailsLiveData.postValue(response)
                        }
                    }
                    is Resource.Error -> {

                    }
                }
            }
        }
    }
}