package kg.peaksoft.peaksoftlmsm1.db.dto.mappers.testMappers;

import kg.peaksoft.peaksoftlmsm1.db.dto.test.request.response.OptionResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.testEntity.Option;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OptionViewMapper {

    public OptionResponse mapToResponse(Option option) {
        if (option == null) {
            return null;
        }
        OptionResponse response = new OptionResponse();
        if (option.getId() != null) {
            response.setId(option.getId());
        }
        response.setOption(option.getOption());
        return response;
    }

    public List<OptionResponse> map(List<Option> optionList) {
        List<OptionResponse> responses = new ArrayList<>();
        for(Option option : optionList){
            responses.add(mapToResponse(option));
        }
        return responses;
    }
}
