package noroff.mefit.dtos;

import lombok.Getter;
import lombok.Setter;
import noroff.mefit.models.SetCount;

import java.util.Set;
@Getter
@Setter
public class ExerciseDTO {

        private int id;

        private String name;

        private String description;

        private String muscle_group;

        private String imageURL;

        private String videoURL;

        private Set<SetCount> setCounts;
}
