package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.controller.dto.presentation.PresentationRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.presentation.PresentationResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.Presentation;
import kg.peaksoft.peaksoftlmsm1.controller.mappers.edit.PresentationEditMapper;
import kg.peaksoft.peaksoftlmsm1.controller.mappers.view.PresentationViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.repository.PresentationRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PresentationService {

    private final PresentationRepository presentationRepository;
    private final PresentationEditMapper presentationEditMapper;
    private final PresentationViewMapper presentationViewMapper;

    public PresentationResponse create(PresentationRequest presentationRequest) {
        Presentation presentation = presentationEditMapper.create(presentationRequest);
        presentationRepository.save(presentation);
        log.info("Entity presentation save: {}", presentation.getName());
        return presentationViewMapper.mapToResponse(presentation);
    }

    public PresentationResponse update(Long id, PresentationRequest presentationRequest) {
        Optional<Presentation> presentation = Optional.ofNullable(presentationRepository.findById(id).orElseThrow(() -> {
            log.error("Entity presentation with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
        presentationEditMapper.update(presentation.orElseThrow(NoSuchElementException::new), presentationRequest);
        log.info("Entity presentation updated: {}", id);
        return presentationViewMapper.mapToResponse(presentationRepository.save(presentation.get()));
    }

    public PresentationResponse getById(Long id) {
        log.info("Get entity presentation by id: {}", id);
        return presentationViewMapper.mapToResponse(presentationRepository.findById(id).orElseThrow(() -> {
            log.error("Entity presentation with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
    }

    public PresentationResponse delete(Long id) {
        Presentation presentation = presentationRepository.findById(id).orElseThrow(() -> {
            log.error("Entity presentation with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        });
        presentationRepository.deleteById(id);
        log.info("Delete entity presentation by id: {}", id);
        return presentationViewMapper.mapToResponse(presentation);
    }

}






