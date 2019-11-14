package org.hari.creations.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheDemoService {

	
	@Cacheable(value = "squares", key = "#input", condition = "#input > 5")
	public Integer findSquare(Integer input)
	{
		System.out.println("findSquare : Simulate Backend operation for input: " + input);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return input*input;
	}
	
	@CacheEvict(value="squares", allEntries = true)
	public void clearAllEntries()
	{
		//Intentionally left blank.
		System.out.println("clearAllEntries : all entries cleared from cache");
	}
	
	@CacheEvict(value="squares", key="#input", beforeInvocation = true)
	public Integer clearEntryForInput(Integer input)
	{
		System.out.println("clearEntryForInput : Simulate Backend operation for input: " + input);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return input*input;
	}
	
	@CachePut(value = "squares", key="#input")
	public Integer updateEntryForInput(Integer input)
	{
		System.out.println("updateEntryForInput : Simulate Backend operation for input: " + input);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return input*input;
	}
	
}
