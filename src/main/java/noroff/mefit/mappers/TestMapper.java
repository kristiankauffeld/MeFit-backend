package noroff.mefit.mappers;

import noroff.mefit.dtos.ExerciseDTO;
import noroff.mefit.models.Exercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TestMapper {
    TestMapper INSTANCE = Mappers.getMapper(TestMapper.class);
    //@Mapping(target = "setCounts", ignore = true)
    @Mapping(target = "setCounts", ignore = true)
    ExerciseDTO exerciseDTO(Exercise exercise);

    //@Mapping(target = "setCounts", ignore = true)
    Exercise exerciseDtoToExercise(ExerciseDTO exerciseDTO);


}
