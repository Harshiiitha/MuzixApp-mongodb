package com.stackroute.muzixservice.service;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixservice.exceptions.TrackNotFoundException;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;

@Primary
@Service
@CacheConfig(cacheNames = {"track"})
public class MuzixServiceImpl implements MuzixService{

    @Autowired
    private MuzixRepository muzixRepository;

    public MuzixServiceImpl(MuzixRepository muzixRepository) {
        this.muzixRepository = muzixRepository;
    }

    public void simulateDelay()
    {
        try {
            Thread.sleep(3000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {

        if (muzixRepository.existsById(track.getTrackId())) {
            throw new TrackAlreadyExistsException("Track already exists");
        }
        Track savedTrack = muzixRepository.save(track);
        if(savedTrack==null)
        {
            throw new TrackAlreadyExistsException("Track already exists");
        }
        return savedTrack;
    }

    @Cacheable(value = "track")
    @Override
    public List<Track> getAllTracks() {

        simulateDelay();
        return muzixRepository.findAll();
    }

    @CacheEvict(allEntries = true)
    @Override
    public Track updateTrackComments(String id,String comment) throws TrackNotFoundException {
        Track track=null;
        if(muzixRepository.existsById(id))
        {
            track=muzixRepository.findById(id).get();
            track.setTrackComments(comment);
            muzixRepository.save(track);
        }
        else
        {
            throw new TrackNotFoundException("Track does not exist");
        }
        return track;
    }


    @Override
    public Track deleteTrack(String id) throws TrackNotFoundException{
        Track track=null;
        if(muzixRepository.existsById(id))
        {
            track=muzixRepository.findById(id).get();
            muzixRepository.deleteById(id);
        }
        else
        {
            throw new TrackNotFoundException("Track does not exist");
        }
      return track;
    }
    @Override
    public Track findTrackByName(String name)throws TrackNotFoundException
    {
        Track track=null;
        track=muzixRepository.findTrackByName(name);
        if(track==null)
        {
            throw new TrackNotFoundException("Track does not exist");
        }
        return track;
    }
}