package com.rsystems.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.Date;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import com.rsystems.dtos.CreateLinkDTO;
import com.rsystems.dtos.LinkDTO;
import com.rsystems.entities.Url;
import com.rsystems.exceptions.UrlFoundException;
import com.rsystems.exceptions.UrlNotFoundException;
import com.rsystems.repositories.UrlRepository;

public class TestUrlServiceImpl {

	@InjectMocks
	@Spy
	UrlServiceImpl urlService;

	@Mock
	UrlRepository repository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	Url url = new Url("MmM3MT", "http://docker.com", "12345");
	Optional<Url> url1;
	Optional<Url> returnCacheValue = Optional.of((Url) url);

	@Test
	public void whenCodeExistReturnUrlFromCodeDetails() {

		String existingCode = "3077yW";
		Url existingUrl = new Url(existingCode, "http://www.docker.com", "12345");
		Optional<Url> optional = Optional.of(existingUrl);
		Mockito.when(repository.findById(existingCode)).thenReturn(optional);
		Url url = urlService.getCodeDetails(existingCode);
		assertEquals(existingUrl, url);
	}

	@Test(expected = UrlNotFoundException.class)
	public void whenCodeNotExistReturnNotFoundException() {

		String existingCode = "3077yW";
		Url existingUrl = new Url(existingCode, "http://www.docker.com", "12345");
		Optional<Url> optional = Optional.of(existingUrl);
		Mockito.when(repository.findById(existingCode)).thenReturn(optional);
		urlService.getCodeDetails("3077yW1");
	}

	@Test
	public void whenCodeExistReturnsUrl() {
		String existingCode = "3077yW";
		Url existingUrl = new Url(existingCode, "http://www.docker.com", "12345");
		Optional<Url> optional = Optional.of(existingUrl);
		Mockito.when(repository.findById(existingCode)).thenReturn(optional);
		Url url = urlService.find(existingCode);
		assertEquals(existingUrl, url);
	}

	@Test
	public void whenCodeNotExistReturnNull() {

		String existingCode = "3077yW1";
		Optional<Url> optional = Optional.empty();
		Mockito.when(repository.findById(existingCode)).thenReturn(optional);
		Url url = urlService.find(existingCode);
		assertNull(url);
	}

	@Test
	public void whenLongUrlDoesNotExistSaveItAndReturnNewUrlCode() {

		String existingCode = "MmM3MT";
		Url existingUrl = new Url("MmM3MT", "http://www.docker.com", "12345");
		LinkDTO linkdto = new LinkDTO();
		CreateLinkDTO createLinkDTO = new CreateLinkDTO();
		createLinkDTO.setCustomerId("12345");
		createLinkDTO.setUrl("http://www.docker.com");
		existingUrl.setCreatedAt(new Date());
		Optional<Url> optional = Optional.of(existingUrl);
		Mockito.when(repository.findById(existingCode)).thenReturn(optional);
		Mockito.when(urlService.dtoToEntity(createLinkDTO)).thenReturn(existingUrl);
		Mockito.when(repository.save(existingUrl)).thenReturn(existingUrl);
		Mockito.when(urlService.entityToDTO(existingUrl)).thenReturn(linkdto);
		LinkDTO linkDTO = urlService.createShortURL(createLinkDTO);
		assertEquals(linkdto, linkDTO);

	}

	@Test(expected = UrlFoundException.class)
	public void whenURLExistReturnError() {

		String existingCode = "NDRmNz";
		Url existingUrl = new Url(existingCode, "http://www.docker.com", "12345");
		CreateLinkDTO createLinkDTO = new CreateLinkDTO();
		createLinkDTO.setCustomerId("12345");
		createLinkDTO.setUrl("http://www.docker.com");
		Optional<Url> optional = Optional.of(existingUrl);
		existingUrl.setCreatedAt(new Date());
		Mockito.when(repository.findById(existingCode)).thenReturn(optional);
		Mockito.when(urlService.dtoToEntity(createLinkDTO)).thenReturn(existingUrl);
		Mockito.when(repository.save(existingUrl)).thenReturn(existingUrl);
		urlService.createShortURL(createLinkDTO);

	}

}
