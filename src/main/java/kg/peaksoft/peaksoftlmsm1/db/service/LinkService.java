package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.link.LinkRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.link.LinkResponse;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.LinkEditMapper;
import kg.peaksoft.peaksoftlmsm1.db.dto.mappers.LinkViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Link;
import kg.peaksoft.peaksoftlmsm1.db.repository.LinkRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LinkService {

    private final LinkRepository linkRepository;
    private final LinkEditMapper linkEditMapper;
    private final LinkViewMapper linkViewMapper;


    public LinkResponse create(LinkRequest request){
        Link link = linkEditMapper.mapToEntity(request);
        linkRepository.save(link);
        return linkViewMapper.mapToResponse(link);
    }

    public LinkResponse update(Long id, LinkRequest request){
        Optional<Link> link = linkRepository.findById(id);
        if(link.isEmpty()){
            System.out.println(link + "with id not found");
        }
        linkEditMapper.mapToUpdate(link.get(), request);
        return linkViewMapper.mapToResponse(linkRepository.save(link.get()));
    }

    public LinkResponse getById(Long id){
        Optional<Link> link = linkRepository.findById(id);
        if(link.isEmpty()){
            System.out.println(link + "with id not found");
        }
        return linkViewMapper.mapToResponse(linkRepository.save(link.get()));
    }

    public LinkResponse delete(Long id){
        Link link = linkRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id));
        linkRepository.deleteById(id);
        return linkViewMapper.mapToResponse(link);
    }

}
