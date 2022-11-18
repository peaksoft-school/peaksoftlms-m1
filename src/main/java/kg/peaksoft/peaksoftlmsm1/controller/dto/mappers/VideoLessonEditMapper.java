package kg.peaksoft.peaksoftlmsm1.controller.dto.mappers;

import kg.peaksoft.peaksoftlmsm1.controller.dto.videoLesson.VideoLessonRequest;
import kg.peaksoft.peaksoftlmsm1.db.entity.VideoLesson;
import org.springframework.stereotype.Component;

@Component
public class VideoLessonEditMapper {

    public VideoLesson create(VideoLessonRequest request){
        if (request == null) {
            return null;
        }
        VideoLesson videoLesson = new VideoLesson();
        videoLesson.setName(request.getName());
        videoLesson.setDescription(request.getDescription());
        videoLesson.setLink(request.getLink());
        return videoLesson;
    }

    public VideoLesson update(VideoLesson videoLesson, VideoLessonRequest request){
        videoLesson.setName(request.getName());
        videoLesson.setDescription(request.getDescription());
        videoLesson.setLink(request.getLink());
        return videoLesson;
    }

}
