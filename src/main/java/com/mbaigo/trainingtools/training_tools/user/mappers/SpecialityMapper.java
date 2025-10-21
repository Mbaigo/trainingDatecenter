package com.mbaigo.trainingtools.training_tools.user.mappers;

import com.mbaigo.trainingtools.training_tools.user.dto.SpecialityRequest;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Profil;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Speciality;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class SpecialityMapper {

    public Speciality toEntity(SpecialityRequest dto, Profil profil) {
        if (dto == null) return null;
        Speciality sp = new Speciality();
        sp.setTitle(dto.getTitle());
        if (profil != null) sp.setProfil(profil);
        return sp;
    }

    public SpecialityRequest toDto(Speciality entity) {
        if (entity == null) return null;
        SpecialityRequest dto = new SpecialityRequest();
        dto.setTitle(entity.getTitle());
        return dto;
    }

    public List<SpecialityRequest> toDtoList(List<Speciality> list) {
        if (list == null || list.isEmpty()) return Collections.emptyList();
        return list.stream().map(this::toDto).toList();
    }

    public List<Speciality> toEntityList(List<SpecialityRequest> dtos, Profil profil) {
        if (dtos == null || dtos.isEmpty()) return Collections.emptyList();
        return dtos.stream().map(dto -> toEntity(dto, profil)).toList();
    }
}

