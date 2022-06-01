package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.presentation.PresentationRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.presentation.PresentationResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Presentation;
import kg.peaksoft.peaksoftlmsm1.db.mappers.presentation.PresentationEditMapper;
import kg.peaksoft.peaksoftlmsm1.db.mappers.presentation.PresentationViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.repository.PresentationRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PresentationService {

    private final PresentationRepository presentationRepository;
    private final PresentationEditMapper presentationEditMapper;
    private final PresentationViewMapper presentationViewMapper;

    public PresentationResponse create(PresentationRequest presentationRequest) {
        Presentation presentation = presentationEditMapper.create(presentationRequest);
        presentationRepository.save(presentation);
        return presentationViewMapper.mapToResponse(presentation);
    }

    public PresentationResponse update(Long id, PresentationRequest presentationRequest) {
        Optional<Presentation> presentation = presentationRepository.findById(id);
        if (presentation.isEmpty()) {
            System.out.println(presentation + "with id not found");
        }
        presentationEditMapper.update(presentation.get(), presentationRequest);
        return presentationViewMapper.mapToResponse(presentationRepository.save(presentation.get()));
    }

    public PresentationResponse getById(Long id){
        Optional<Presentation> presentation = presentationRepository.findById(id);
        if(presentation.isEmpty()){
            System.out.println(presentation + "with id not found");
        }
        return presentationViewMapper.mapToResponse(presentationRepository.save(presentation.get()));
    }

    public PresentationResponse delete(Long id){
        Presentation presentation = presentationRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id));
        presentationRepository.deleteById(id);
        return presentationViewMapper.mapToResponse(presentation);
    }


}






