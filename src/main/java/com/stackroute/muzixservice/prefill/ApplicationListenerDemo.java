package com.stackroute.muzixservice.prefill;

import com.stackroute.muzixservice.domain.Track;
import com.stackroute.muzixservice.repository.MuzixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
public class ApplicationListenerDemo implements ApplicationListener<ContextRefreshedEvent> {

    private  Environment environment;

    @Autowired
    public  ApplicationListenerDemo(Environment environment)
    {
        this.environment=environment;
    }
    @Autowired
    MuzixRepository muzixRepository;

    Track track=new Track();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)  {

        track.setTrackId(environment.getProperty("track1.id"));
        track.setTrackName(environment.getProperty("track1.name"));
        track.setTrackComments(environment.getProperty("track1.comment"));
        muzixRepository.save(track);
    }
}
