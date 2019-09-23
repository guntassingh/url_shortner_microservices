package com.rsystems.services;

import com.rsystems.dtos.CreateLinkDTO;
import com.rsystems.dtos.LinkDTO;
import com.rsystems.entities.Url;

public interface UrlService {
	
	
	/**
	 * Get URL code details
	 * @param urlCode
	 * @return
	 */
	public Url find(String urlCode);
	
	/**
	 * Get url code details if found
	 * @param urlCode
	 * @return
	 */
	public Url getCodeDetails(String urlCode);

	/**
	 * Create createShortURL
	 * 
	 * @param customerId, url of CreateLinkDTO
	 * @return LinkDTO
	 */
	public LinkDTO createShortURL(CreateLinkDTO urlDto);



}
