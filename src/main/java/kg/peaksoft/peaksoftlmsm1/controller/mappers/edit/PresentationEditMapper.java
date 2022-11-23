package kg.peaksoft.peaksoftlmsm1.controller.mappers.edit;

import kg.peaksoft.peaksoftlmsm1.controller.dto.presentation.PresentationRequest;
import kg.peaksoft.peaksoftlmsm1.db.entity.Presentation;
import org.springframework.stereotype.Component;

@Component
public class PresentationEditMapper {

    public Presentation create(PresentationRequest presentationRequest) {
        if (presentationRequest == null) {
            return null;
        }
        Presentation presentation = new Presentation();
        presentation.setName(presentationRequest.getName());
        presentation.setDescription(presentationRequest.getDescription());
        presentation.setFile(presentationRequest.getFile());
        return presentation;

    }

    public Presentation update(Presentation presentation, PresentationRequest presentationRequest) {
        presentation.setName(presentationRequest.getName());
        presentation.setDescription(presentationRequest.getDescription());
        presentation.setFile(presentationRequest.getFile());
        return presentation;
    }

}






