package kg.peaksoft.peaksoftlmsm1.controller.mappers.testMappers;

import kg.peaksoft.peaksoftlmsm1.controller.dto.test.request.OptionRequest;
import kg.peaksoft.peaksoftlmsm1.db.entity.testEntity.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OptionEditMapper {

    public Option mapToEntity(OptionRequest optionRequest) {
        if (optionRequest == null) {
            return null;
        }
        Option option = new Option();
        option.setOption(optionRequest.getOption());
        option.setCorrect(optionRequest.isCorrect());
        return option;
    }

    public Option mapToUpdate(Option option, OptionRequest optionRequest) {
        option.setOption(optionRequest.getOption());
        option.setCorrect(optionRequest.isCorrect());
        return option;
    }
}
