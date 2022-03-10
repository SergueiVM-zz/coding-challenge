package com.example.demo.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.model.PlanningSession;
import com.example.demo.model.Session;

@Mapper(componentModel = "spring")
public interface SpringConverter {
	
    	SpringConverter INSTANCE = Mappers.getMapper(SpringConverter.class);
	
	    Session planningSessionToSession(PlanningSession planningSession);
}
