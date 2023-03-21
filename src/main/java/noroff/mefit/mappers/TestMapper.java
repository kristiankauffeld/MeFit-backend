package noroff.mefit.mappers;

import noroff.mefit.dtos.ExerciseDTO;
import noroff.mefit.models.Exercise;
import noroff.mefit.models.SetCount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TestMapper {

    ExerciseDTO exerciseDTO(Exercise exercise);

    @Mapping(target = "setCounts", ignore = true)
    Exercise exerciseDtoToExercise(ExerciseDTO exerciseDTO);

/*    @Named("setCountsToInts")
    default Set<Integer> map(Set<SetCount> source) {
        if (source == null) return null;
        return source.stream().map(s -> s.getExercise_repetitions()).collect(Collectors.toSet());
    }*/

}
