package kg.peaksoft.peaksoftlmsm1.controller.mappers.edit;

import kg.peaksoft.peaksoftlmsm1.controller.dto.link.LinkRequest;
import kg.peaksoft.peaksoftlmsm1.db.entity.Link;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LinkEditMapper {

    public Link mapToEntity(LinkRequest linkRequest){
        if(linkRequest == null){
            return null;
        }
        Link link = new Link();
        link.setText(linkRequest.getText());
        link.setLink(linkRequest.getLink());
        return link;
    }

    public Link mapToUpdate(Link link, LinkRequest linkRequest){
        link.setText(linkRequest.getText());
        link.setLink(linkRequest.getLink());
        return link;
    }
}
