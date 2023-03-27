package noroff.mefit.services;

import noroff.mefit.models.Program;
import noroff.mefit.repositories.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class ProgramServiceImpl implements ProgramService{
    private final ProgramRepository programRepository;

    public ProgramServiceImpl(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    @Override
    public Program findById(Integer id) {
        return programRepository.findById(id).get();
    }

    @Override
    public Collection<Program> findAll() {
        return programRepository.findAll();
    }

    @Override
    public Program add(Program entity) {
        return programRepository.save(entity);
    }

    @Override
    public Program update(Program entity) {
        return programRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        //maybe bad
        Program program = findById(id);
        program.getProfiles().forEach(s->{
            s.setProgram(null);
        });

        program.getWorkouts().forEach(s->{
            Set tempSet = s.getPrograms();
            tempSet.remove(program);
            s.setPrograms(tempSet);
        });

        programRepository.delete(program);
    }
}
