package noroff.mefit.mappers;

import noroff.mefit.dtos.ExerciseGetDTO;
import noroff.mefit.models.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {



    //@Mapping(target = "setCounts", ignore = true)
    Exercise exerciseDtoToExercise(ExerciseGetDTO exerciseDTO);


    @Mapping(target = "id", ignore = true)
    void updateExerciseFromDto(ExerciseGetDTO exerciseDTO, @MappingTarget Exercise exercise);



}
