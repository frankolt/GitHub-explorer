package io.github.frankolt.githubexplorer.domain.github.mappers

interface ModelMapper<in InputModel, out OutputModel> {

    fun map(inputModel: InputModel): OutputModel
}
