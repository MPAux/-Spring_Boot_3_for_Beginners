package com.luv2code.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class CricketCoach implements Coach {

    public CricketCoach() {
        System.out.println("In Constructor "+this.getClass().getSimpleName());
    }

    // define our init method
    @PostConstruct
    public void doMyStartupStuff() {
        System.out.println("CREATED Inside doMyStartupStuff(): "+this.getClass().getSimpleName());
    }

    // define our destroy method
    @PreDestroy
    public void doMyCleanupStuff() {
        System.out.println("DESTROYED Inside doMyCleanupStuff(): "+this.getClass().getSimpleName());

    }
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }

}
