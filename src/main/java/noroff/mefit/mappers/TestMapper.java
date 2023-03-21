package noroff.mefit.mappers;

import noroff.mefit.dtos.ExerciseGetDTO;
import noroff.mefit.models.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TestMapper {
    //TestMapper INSTANCE = Mappers.getMapper(TestMapper.class);
    //@Mapping(target = "setCounts", ignore = true)
    @Mapping(target = "setCounts", ignore = true)
    ExerciseGetDTO exerciseGetDTO(Exercise exercise);

    @Mapping(target = "setCounts", ignore = true)
    Exercise exerciseDtoToExercise(ExerciseGetDTO exerciseGetDTO);


}
