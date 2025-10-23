package com.mbaigo.trainingtools.training_tools.user.mappers;

import com.mbaigo.trainingtools.training_tools.user.dto.ExperienceRequest;
import com.mbaigo.trainingtools.training_tools.user.dto.ProfilResponseDto;
import com.mbaigo.trainingtools.training_tools.user.dto.SpecialityRequest;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Experience;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Profil;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Speciality;

import java.util.stream.Collectors;

public class ProfilMapper {
    public static ProfilResponseDto toDTO(Profil profil) {
        if (profil == null) return null;

        return ProfilResponseDto.builder()
                .id(profil.getId())
                .nom(profil.getNom())
                .prenom(profil.getPrenom())
                .email(profil.getEmail())
                .certification(profil.getCertification())
                .parcours(profil.getParcours())
                .photoUrl(profil.getPhotoUrl())
                .githubUrl(profil.getGithubUrl())
                .linkedinUrl(profil.getLinkedinUrl())
                .avatarUrl(profil.getAvatarUrl())
                .experiences(profil.getExperiences() != null
                        ? profil.getExperiences().stream().map(ProfilMapper::toExperienceDTO).collect(Collectors.toList())
                        : null)
                .specialities(profil.getSpecialities() != null
                        ? profil.getSpecialities().stream().map(ProfilMapper::toSpecialityDTO).collect(Collectors.toList())
                        : null)
                .build();
    }

    public static ExperienceRequest toExperienceDTO(Experience exp) {
        if (exp == null) return null;
        return ExperienceRequest.builder()
                .jobTitle(exp.getJobTitle())
                .company(exp.getCompany())
                .location(exp.getLocation())
                .startDate(exp.getStartDate())
                .endDate(exp.getEndDate())
                .jobDescription(exp.getJobDescription())
                .build();
    }

    public static SpecialityRequest toSpecialityDTO(Speciality sp) {
        if (sp == null) return null;
        return SpecialityRequest.builder()
                .title(sp.getTitle())
                .build();
    }
}
