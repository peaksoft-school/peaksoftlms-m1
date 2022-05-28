package kg.peaksoft.peaksoftlmsm1.db.service;

import kg.peaksoft.peaksoftlmsm1.db.dto.videoLesson.VideoLessonRequest;
import kg.peaksoft.peaksoftlmsm1.db.dto.videoLesson.VideoLessonResponse;
import kg.peaksoft.peaksoftlmsm1.db.entity.models.VideoLesson;
import kg.peaksoft.peaksoftlmsm1.db.mappers.videoLesson.VideoLessonEditMapper;
import kg.peaksoft.peaksoftlmsm1.db.mappers.videoLesson.VideoLessonViewMapper;
import kg.peaksoft.peaksoftlmsm1.db.repository.VideoLessonRepository;
import kg.peaksoft.peaksoftlmsm1.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoLessonService {

    private final VideoLessonRepository repository;
    private final VideoLessonEditMapper editMapper;
    private final VideoLessonViewMapper viewMapper;

    public VideoLessonResponse create(VideoLessonRequest request){
        VideoLesson videoLesson = editMapper.create(request);
        repository.save(videoLesson);
        return viewMapper.mapperResponse(videoLesson);
    }

    public VideoLessonResponse update(Long id,VideoLessonRequest request){
        Optional<VideoLesson> videoLesson = repository.findById(id);
        if(videoLesson.isEmpty()){
            System.out.println(videoLesson + "with id not found");
        }
        editMapper.update(videoLesson.get(),request);
        return viewMapper.mapperResponse(repository.save(videoLesson.get()));
    }

    public VideoLessonResponse getById(Long id){
        Optional<VideoLesson> videoLesson = repository.findById(id);
        if(videoLesson.isEmpty()){
            System.out.println(videoLesson + "with id not found");
        }
        return viewMapper.mapperResponse(repository.save(videoLesson.get()));
    }

    public VideoLessonResponse delete(Long id) {
        VideoLesson videoLesson = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Entity", "id", id));
        repository.deleteById(id);
        return viewMapper.mapperResponse(videoLesson);
    }

}
