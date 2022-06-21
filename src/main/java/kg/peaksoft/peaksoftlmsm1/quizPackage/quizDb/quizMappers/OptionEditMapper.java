package kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.quizMappers;

import kg.peaksoft.peaksoftlmsm1.quizPackage.quizDb.request.OptionRequest;
import kg.peaksoft.peaksoftlmsm1.quizPackage.quizEntity.Option;
import org.springframework.stereotype.Component;

@Component
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
