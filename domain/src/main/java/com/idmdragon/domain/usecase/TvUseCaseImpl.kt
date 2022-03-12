package com.idmdragon.domain.usecase

import com.idmdragon.domain.model.MovieTv
import com.idmdragon.domain.repository.TvRepository
import com.idmdragon.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

class TvUseCaseImpl(private val tvRepository: TvRepository) : TvUseCase {

    override fun getTvAiringToday(): Flow<Resource<List<MovieTv>>> =
        tvRepository.getTvAiringToday()

    override fun getTvOnTheAir(): Flow<Resource<List<MovieTv>>> =
        tvRepository.getTvOnTheAir()

    override fun getTvTopRated(): Flow<Resource<List<MovieTv>>> =
        tvRepository.getTvTopRated()

    override fun getTvPopular(): Flow<Resource<List<MovieTv>>> =
        tvRepository.getTvPopular()

    override fun getDetailTv(tvId: Int, tvType: String): Flow<Resource<MovieTv>> =
        tvRepository.getDetailTv(tvId,tvType)

}