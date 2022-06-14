package kg.peaksoft.peaksoftlmsm1.db.dto.mappers;

import kg.peaksoft.peaksoftlmsm1.db.dto.videoLesson.VideoLessonResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.VideoLesson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VideoLessonViewMapper {

    public VideoLessonResponse mapperResponse(VideoLesson videoLesson){
        if (videoLesson == null) {
            return null;
        }
        VideoLessonResponse response = new VideoLessonResponse();
        if (videoLesson.getId() != null) {
            response.setId(videoLesson.getId());
        }
        response.setName(videoLesson.getName());
        response.setDescription(videoLesson.getDescription());
        response.setLink(videoLesson.getLink());
        return response;
    }

    public List<VideoLessonResponse> map(List<VideoLesson> lessons){
        List<VideoLessonResponse> responses = new ArrayList<>();
        for(VideoLesson lesson: lessons){
            responses.add(mapperResponse(lesson));
        }
        return responses;
    }
}
