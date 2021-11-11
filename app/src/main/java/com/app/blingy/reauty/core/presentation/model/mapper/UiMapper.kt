package com.app.blingy.reauty.core.presentation.model.mapper

/**
 * contract to convert Model from Domain to Model for UI
 */
interface UiMapper<DomainModel, UiModel> {
    fun mapToView(input: DomainModel): UiModel
}