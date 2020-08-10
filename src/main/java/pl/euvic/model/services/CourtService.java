package pl.euvic.model.services;

import org.springframework.stereotype.Service;
import pl.euvic.model.entities.CourtEntity;
import pl.euvic.model.repositories.CourtRepository;
import pl.euvic.model.responses.CourtRestModel;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourtService {

    private final CourtRepository courtRepository;

    public CourtService(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    public List<CourtRestModel> getAll() {
        return courtRepository.findAll().stream()
                .map(CourtRestModel::new)
                .collect(Collectors.toList());
    }

    public CourtRestModel getById(final Long id) {
        return new CourtRestModel(courtRepository.getById(id));
    }

    public Long add(CourtRestModel courtRestModel) {
        return courtRepository.save(mapRestModel(courtRestModel)).getId();
    }

    private CourtEntity mapRestModel(final CourtRestModel model) {
        return new CourtEntity(model.getName());
    }

    public void deleteById(Long id) {
        courtRepository.deleteById(id);
    }
}
