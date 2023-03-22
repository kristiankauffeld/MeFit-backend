package noroff.mefit.mappers;

import noroff.mefit.dtos.ExerciseDTO;
import noroff.mefit.models.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    @Mapping(target = "setCounts", ignore = true)
    ExerciseDTO exerciseDTO(Exercise exercise);

    //@Mapping(target = "setCounts", ignore = true)
    Exercise exerciseDtoToExercise(ExerciseDTO exerciseDTO);


    @Mapping(target = "id", ignore = true)
    void updateExerciseFromDto(ExerciseDTO exerciseDTO, @MappingTarget Exercise exercise);



}
