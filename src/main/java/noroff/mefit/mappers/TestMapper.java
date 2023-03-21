package noroff.mefit.mappers;

import noroff.mefit.dtos.ExerciseDTO;
import noroff.mefit.models.Exercise;
import noroff.mefit.models.SetCount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TestMapper {
    //TestMapper INSTANCE = Mappers.getMapper(TestMapper.class);
    //@Mapping(target = "setCounts", ignore = true)
    ExerciseDTO exerciseDTO(Exercise exercise);

    //@Mapping(target = "setCounts", ignore = true)
    Exercise exerciseDtoToExercise(ExerciseDTO exerciseDTO);


}
