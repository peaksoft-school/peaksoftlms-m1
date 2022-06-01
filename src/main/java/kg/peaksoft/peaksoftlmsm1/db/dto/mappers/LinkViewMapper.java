package kg.peaksoft.peaksoftlmsm1.db.dto.mappers;

import kg.peaksoft.peaksoftlmsm1.db.dto.link.LinkResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.Link;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LinkViewMapper {

    public LinkResponse mapToResponse(Link link){
        if(link == null){
            return null;
        }
        LinkResponse linkResponse = new LinkResponse();
        if(link.getId() != null){
            linkResponse.setId(link.getId());
        }
        linkResponse.setText(link.getText());
        linkResponse.setLink(link.getLink());
        return linkResponse;
    }

    public List<LinkResponse> map(List<Link> links){
        List<LinkResponse> responses = new ArrayList<>();
        for(Link link : links){
            responses.add(mapToResponse(link));
        }
        return responses;
    }
}
