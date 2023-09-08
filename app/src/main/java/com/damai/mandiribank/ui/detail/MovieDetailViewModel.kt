package com.damai.mandiribank.ui.detail

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
import com.damai.base.utils.Event
import com.damai.base.utils.MovieType
import com.damai.domain.models.MovieItemModel
import com.damai.domain.models.MovieVideoModel
import com.damai.domain.models.ReviewItemRequestModel
import com.damai.domain.models.ReviewUiModel
import com.damai.domain.usecases.GetMovieDetailsUseCase
import com.damai.domain.usecases.GetMovieReviewListUseCase
import com.damai.domain.usecases.GetMovieVideosUseCase
import kotlinx.coroutines.launch

/**
 * Created by damai007 on 19/August/2023
 */
class MovieDetailViewModel(
    app: Application,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getMovieReviewsUseCase: GetMovieReviewListUseCase,
    private val getMovieVideosUseCase: GetMovieVideosUseCase,
    private val dispatcher: DispatcherProvider
) : BaseViewModel(app = app) {

    //region Live Data
    private val _movieDetailsLiveData = MutableLiveData<MovieItemModel>()
    val movieDetailsLiveData = _movieDetailsLiveData.asLiveData()

    private val _movieReviewsLiveData = MutableLiveData<List<ReviewUiModel>>()
    val movieReviewsLiveData = _movieReviewsLiveData.asLiveData()

    private val _movieReviewsCountLiveData = MutableLiveData(0)
    val movieReviewsCountLiveData = _movieReviewsCountLiveData.asLiveData()

    private val _trailerMovieLiveData = MutableLiveData<MovieVideoModel?>()
    val trailerMovieLiveData = _trailerMovieLiveData.asLiveData()

    private val _errorMovieDetailsLiveData = MutableLiveData<Event<Pair<Boolean, String?>>>()
    val errorMovieDetailsLiveData = _errorMovieDetailsLiveData.asLiveData()

    private val _errorMovieReviewsLiveData = MutableLiveData<Event<Pair<Boolean, String?>>>()
    val errorMovieReviewsLiveData = _errorMovieReviewsLiveData.asLiveData()
    //endregion `Live Data`

    //region Variables
    private var movieId = 0
    private var reviewCurrentPage = 1
    private var totalReviewPage = 1

    val isStopLoadMore: Boolean
        get() = reviewCurrentPage >= totalReviewPage
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
                        Event(
                            Pair(true, resource.errorMessage)
                        ).let(_errorMovieDetailsLiveData::postValue)
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
                                totalReviewPage = model.totalPage.orZero()
                                if (model.currentPage.orZero() < model.totalPage.orZero()) {
                                    currentData.add(ReviewUiModel.ReviewFooterLoading)
                                }
                            }

                            _movieReviewsLiveData.postValue(currentData.toList())
                        }
                    }
                    is Resource.Error -> {
                        Event(
                            Pair(true, resource.errorMessage)
                        ).let(_errorMovieReviewsLiveData::postValue)
                    }
                }
            }
        }
    }

    fun getMovieReviewsCount() {
        viewModelScope.launch(dispatcher.io()) {
            val requestModel = ReviewItemRequestModel(
                movieId = movieId,
                page = 1
            )
            getMovieReviewsUseCase(requestModel).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.model?.totalResult?.let(
                            _movieReviewsCountLiveData::postValue
                        ) ?: _movieReviewsCountLiveData.postValue(0)
                    }
                    is Resource.Error -> {
                        _movieReviewsCountLiveData.postValue(0)
                    }
                }
            }
        }
    }

    fun getMovieVideos() {
        viewModelScope.launch(dispatcher.io()) {
            getMovieVideosUseCase(movieId).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.model?.list?.let { response ->
                            response.find { movieVideo ->
                                movieVideo.type == MovieType.Trailer.type
                            }?.let(_trailerMovieLiveData::postValue)
                        }
                    }
                    is Resource.Error -> Unit
                }
            }
        }
    }

    fun changeReviewCurrentPage(newPage: Int) {
        reviewCurrentPage = newPage
    }

    fun resetReviewList() {
        reviewCurrentPage = 1
        _movieReviewsLiveData.value = listOf()
    }
}