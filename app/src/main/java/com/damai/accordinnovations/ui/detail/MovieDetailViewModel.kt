package com.damai.accordinnovations.ui.detail

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.damai.base.BaseViewModel
import com.damai.base.coroutines.DispatcherProvider
import com.damai.base.extensions.asLiveData
import com.damai.base.extensions.orZero
import com.damai.base.networks.Resource
import com.damai.base.utils.Constants.ARGS_MOVIE_ID
import com.damai.domain.models.MovieItemModel
import com.damai.domain.models.MovieUiModel
import com.damai.domain.models.ReviewItemRequestModel
import com.damai.domain.models.ReviewUiModel
import com.damai.domain.usecases.GetMovieDetailsUseCase
import com.damai.domain.usecases.GetMovieReviewListUseCase
import kotlinx.coroutines.launch

/**
 * Created by damai007 on 19/August/2023
 */
class MovieDetailViewModel(
    app: Application,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieReviewsUseCase: GetMovieReviewListUseCase,
    private val dispatcher: DispatcherProvider
) : BaseViewModel(app = app) {

    //region Live Data
    private val _movieDetailsLiveData = MutableLiveData<MovieItemModel>()
    val movieDetailsLiveData = _movieDetailsLiveData.asLiveData()

    private val _movieReviewsLiveData = MutableLiveData<List<ReviewUiModel>>()
    val movieReviewsLiveData = _movieReviewsLiveData.asLiveData()
    //endregion `Live Data`

    //region Variables
    private var movieId = 0
    private var reviewCurrentPage = 1
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

    fun getMovieReviews() {
        viewModelScope.launch(dispatcher.io()) {
            val requestModel = ReviewItemRequestModel(
                movieId = movieId,
                page = reviewCurrentPage
            )
            getMovieReviewsUseCase(requestModel).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.model?.list?.let { response ->
                            var currentData = _movieReviewsLiveData.value?.toMutableList()
                            if (currentData == null) {
                                currentData = mutableListOf()
                            }
                            currentData.removeAll {
                                it is ReviewUiModel.ReviewFooterLoading
                            }
                            response.forEach {
                                currentData.add(ReviewUiModel.ReviewItemWrap(it))
                            }

                            resource.model?.let { model ->
                                if (model.currentPage.orZero() < model.totalPage.orZero()) {
                                    currentData.add(ReviewUiModel.ReviewFooterLoading)
                                }
                            }

                            _movieReviewsLiveData.postValue(currentData.toList())
                        }
                    }
                    is Resource.Error -> {

                    }
                }
            }
        }
    }

    fun changeReviewCurrentPage(newPage: Int) {
        reviewCurrentPage = newPage
    }

    fun resetReviewList() {
        reviewCurrentPage = 1
    }
}