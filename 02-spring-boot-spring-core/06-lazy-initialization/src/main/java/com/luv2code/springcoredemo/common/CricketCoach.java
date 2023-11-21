package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CricketCoach implements Coach {

    public CricketCoach() {
        System.out.println("In Constructor "+this.getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }

}
