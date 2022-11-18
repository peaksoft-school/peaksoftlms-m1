package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.controller.dto.link.LinkRequest;
import kg.peaksoft.peaksoftlmsm1.controller.dto.link.LinkResponse;
import kg.peaksoft.peaksoftlmsm1.controller.dto.mappers.LinkEditMapper;
import kg.peaksoft.peaksoftlmsm1.controller.dto.mappers.LinkViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.entity.Link;
import kg.peaksoft.peaksoftlmsm1.db.repository.LinkRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepository linkRepository;
    private final LinkEditMapper linkEditMapper;
    private final LinkViewMapper linkViewMapper;


    public LinkResponse create(LinkRequest request){
        Link link = linkEditMapper.mapToEntity(request);
        linkRepository.save(link);
        log.info("Entity link save: {}", link.getId());
        return linkViewMapper.mapToResponse(link);
    }

    public LinkResponse update(Long id, LinkRequest request){
        Optional<Link> link = Optional.ofNullable(linkRepository.findById(id).orElseThrow(() -> {
            log.error("Entity link with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
        linkEditMapper.mapToUpdate(link.get(), request);
        log.info("Entity link updated: {}", id);
        return linkViewMapper.mapToResponse(linkRepository.save(link.get()));
    }

    public LinkResponse getById(Long id){
        log.info("Get entity link by id: {}", id);
        return linkViewMapper.mapToResponse(linkRepository.findById(id).orElseThrow(() -> {
            log.error("Entity link with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        }));
    }

    public LinkResponse delete(Long id){
        Link link = linkRepository.findById(id).orElseThrow(() -> {
            log.error("Entity link with id = {} does not exists in database", id);
            throw new ResourceNotFoundException("Entity", "id", id);
        });
        linkRepository.deleteById(id);
        log.info("Delete entity link by id: {}", id);
        return linkViewMapper.mapToResponse(link);
    }

}
