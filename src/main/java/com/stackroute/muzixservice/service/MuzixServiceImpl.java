package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Primary
@Service
public class MuzixServiceImpl implements MuzixService{

    @Autowired
    private MuzixRepository muzixRepository;
    private Track track;
    @Autowired
    private Environment environment;

    public MuzixServiceImpl(MuzixRepository muzixRepository) {
        this.muzixRepository = muzixRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {

        if (muzixRepository.existsById(track.getTrackId())) {
            throw new TrackAlreadyExistsException(environment.getProperty("trackalreadyexistsexception.message"));
        }
        Track savedTrack = muzixRepository.save(track);
        if(savedTrack==null)
        {
            throw new TrackAlreadyExistsException("trackalreadyexistsexception.message");
        }
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() {
            return muzixRepository.findAll();

    }

    @Override
    public Track updateTrackComments(String id,String comment) throws TrackNotFoundException {

        Optional optional=muzixRepository.findById(id);
        if(optional.isPresent())
        {
            track=muzixRepository.findById(id).get();
            track.setTrackComments(comment);
            muzixRepository.save(track);
        }
        else
        {
            throw new TrackNotFoundException(environment.getProperty("tracknotfoundexception.message"));
        }
        return track;
    }

    @Override
    public Track deleteTrack(String id) throws TrackNotFoundException{
        Track track=null;
        Optional optional=muzixRepository.findById(id);
        if(optional.isPresent())
        {
            track=muzixRepository.findById(id).get();
            muzixRepository.deleteById(id);
        }
        else
        {
            throw new TrackNotFoundException(environment.getProperty("trackalreadyexistsexception.message"));
        }
      return track;
    }
}