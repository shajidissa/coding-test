package com.connectgroup;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.connectgroup.model.Log;

public class DataFilterer {
	
	public static Function<String, Log> mapToLog = (line) -> {
	  String[] col = line.split(",");
	  return new Log(Integer.parseInt(col[0]), col[1] ,Integer.parseInt(col[2]));
	};
	
    public static Collection<?> filterByCountry(Reader source, String country) {
    	
    	BufferedReader br = new BufferedReader(source);
    			
    	List<Log> logs = br.lines().skip(1)			
    		    .map(mapToLog)
    		    .filter(log -> log.getCountryCode().equals(country))
    		    .collect(Collectors.toList());
    	
    	return logs;   
    }

    public static Collection<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) {

    	BufferedReader br = new BufferedReader(source);
    	
    	List<Log> logs = br.lines().skip(1)			
    		    .map(mapToLog)
    		    .filter(log -> log.getCountryCode().equals(country))
    		    .filter(log -> log.getResponseTime() > limit)
    		    .collect(Collectors.toList());
    	
    	return logs;
    }
  
    public static Collection<?> filterByResponseTimeAboveAverage(Reader source) {
    	
    	BufferedReader br = new BufferedReader(source);
    	
    	List<Log> allLogs = br.lines().skip(1)			
    		    .map(mapToLog)
    		    .collect(Collectors.toList());
    	
    	// get the average
    	double average = allLogs
                .stream()             
                .mapToInt(Log::getResponseTime)
                .average()
                .getAsDouble();
    	
    	List<Log> logs = allLogs.stream()
    		    .filter(log -> log.getResponseTime() > average)
    		    .collect(Collectors.toList());
    	
    	return logs;
    }
}