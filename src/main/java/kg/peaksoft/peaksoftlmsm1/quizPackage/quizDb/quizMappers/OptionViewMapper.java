package kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.quizMappers;

import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.response.OptionResponse;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizEntity.Option;
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
