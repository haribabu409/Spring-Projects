package org.hari.creations.resource;

import org.hari.creations.service.CacheDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheDemoResource {

	@Autowired
	CacheDemoService service;
	
	@RequestMapping("/findsquares")
	public void execute()
	{
		for(int i=0; i<=10; i++)
		{
			System.out.println("executing findSquare method for:" + i);
			service.findSquare(i);					
		}		
	}
	
	@RequestMapping("/findsquares/{number}")
	public void executeForInput(@PathVariable("number") Integer input)
	{
		System.out.println("executing findSquare method for:" + input);
		service.findSquare(input);		
	}
	
	@RequestMapping("/clear")
	public void clearCacheEntries()
	{
		System.out.println(" Executing clearCacheEntries");
		service.clearAllEntries();
	}
	
	@RequestMapping("/clear/{number}")
	public void clearCacheEntryForInput(@PathVariable("number") Integer input)
	{
		System.out.println(" Executing clearCacheEntryForInput:" + input);
		service.clearAllEntries();
	}
	
	@RequestMapping("/update/{number}")
	public void updateCacheEntryForInput(@PathVariable("number") Integer input)
	{
		System.out.println(" Executing updateCacheEntryForInput:" + input);
		service.clearAllEntries();
	}
	
	@RequestMapping("/test")
	public void test()
	{
		// find squares of numbers from 10 to 20 and add in cache
		for(int i=10; i<=20; i++)
			service.findSquare(i);
		
		// find square of 20 and check it is cached value or recomputed [cached value is used]
		service.findSquare(20);
		
		// clear all entries of cache
		service.clearAllEntries();
		
		// find square of 20 and check it is cached value or recomputed [as cache is empty, recompute is done]
		service.findSquare(20);
		
		//clear value for 20 in cache
		service.clearEntryForInput(20);
		
		//here 20 will be computed again. as 20 is cleared in previous step		
		service.findSquare(20);
		
		//update value of 20
		service.updateEntryForInput(20);
		
		//as 20 is in cache, if will not be recomputed
		service.findSquare(20);
		service.findSquare(20);
		
		
	}
}
