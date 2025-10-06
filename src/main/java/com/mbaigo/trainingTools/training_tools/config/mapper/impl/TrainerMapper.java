package com.mbaigo.trainingTools.training_tools.config.mapper.impl;

import com.mbaigo.trainingTools.training_tools.beans.domaine.Trainer;
import com.mbaigo.trainingTools.training_tools.beans.dto.TrainerDto;
import com.mbaigo.trainingTools.training_tools.config.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class TrainerMapper {

    /**
     * @param trainer
     * @return
     */
//    @Override
//    public TrainerDto toDto(Trainer trainer) {
//        if (trainer == null) return null;
//
//       return new TrainerDto();
////        TrainerDto.builder()
////                .firstName(trainer.getFirstName())
////                .lastName(trainer.getLastName())
////                .phoneNumber(trainer.getPhoneNumber())
////                .mailAdress(trainer.getMailAdress())
////                .level(trainer.getLevel())
////                .skills(trainer.getSkills())
////                .build();
//    }
//
//    @Override
//    public Trainer toEntity(TrainerDto dto) {
//        if (dto == null) return null;
//
//        return Trainer.builder()
//                .firstName(dto.getFirstName())
//                .lastName(dto.getLastName())
//                .phoneNumber(dto.getPhoneNumber())
//                .mailAdress(dto.getMailAdress())
//                .level(dto.getLevel())
//                .skills(dto.getSkills())
//                .trainingList(null) // pas dans le DTO → à gérer ailleurs si besoin
//                .build();
//    }
}
