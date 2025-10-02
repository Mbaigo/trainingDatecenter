package com.mbaigo.trainingTools.training_tools.config.mapper;

import java.util.List;

public interface GenericMapper <E, D> {
    D toDto(E entity);
    E toEntity(D dto);
    default List<D> toDtoList(List<E> entities) {
        return entities == null ? List.of() : entities.stream().map(this::toDto).toList();
    }

    default List<E> toEntityList(List<D> dtos) {
        return dtos == null ? List.of() : dtos.stream().map(this::toEntity).toList();
    }
}
