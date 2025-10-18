package com.mbaigo.trainingtools.training_tools.user.mappers;

import com.mbaigo.trainingtools.training_tools.user.dto.ExperienceRequest;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Experience;
import com.mbaigo.trainingtools.training_tools.user.entities.users.Profil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExperienceMapperTest {

    private final ExperienceMapper mapper = new ExperienceMapper();

    @Test
    void toDtoAndBack_shouldMapFieldsCorrectly() {
        Profil profil = new Profil();
        profil.setId(42L);

        Experience exp = new Experience();
        exp.setId(1L);
        exp.setJobTitle("Dev");
        exp.setCompany("Acme");
        exp.setLocation("Paris");
        exp.setStartDate("2020-01-01");
        exp.setEndDate("2021-01-01");
        exp.setJobDescription("Did stuff");
        exp.setProfil(profil);

        ExperienceRequest dto = mapper.toDto(exp);
        assertNotNull(dto);
        assertEquals(exp.getId(), dto.getId());
        assertEquals(exp.getJobTitle(), dto.getJobTitle());
        assertEquals(exp.getCompany(), dto.getCompany());
        assertEquals(exp.getLocation(), dto.getLocation());
        assertEquals(exp.getStartDate(), dto.getStartDate());
        assertEquals(exp.getEndDate(), dto.getEndDate());
        assertEquals(exp.getJobDescription(), dto.getJobDescription());
        assertEquals(profil.getId(), dto.getProfilId());

        // Convert back to entity using the profil object
        Experience back = mapper.toEntity(dto, profil);
        assertNotNull(back);
        assertEquals(dto.getId(), back.getId());
        assertEquals(dto.getJobTitle(), back.getJobTitle());
        assertEquals(dto.getCompany(), back.getCompany());
        assertEquals(dto.getLocation(), back.getLocation());
        assertEquals(dto.getStartDate(), back.getStartDate());
        assertEquals(dto.getEndDate(), back.getEndDate());
        assertEquals(dto.getJobDescription(), back.getJobDescription());
        assertEquals(profil, back.getProfil());
    }
}

