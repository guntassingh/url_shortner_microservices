package com.rsystems.services;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpHeaders;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.rsystems.dtos.StatisticsDTO;
import com.rsystems.dtos.StatisticsSummaryDTO;
import com.rsystems.entities.Statistic;
import com.rsystems.entities.Url;
import com.rsystems.repositories.StatisticRepository;

public class TestStatisticServiceImpl {

	@InjectMocks
	StatisticServiceImpl statisticService;

	@Mock
	StatisticRepository repository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void whenCreatingStatisticVerifyThatRepositorySaveIsCalled() {

		Url url = new Url("MmM3MT", "http://docker.com", "12345");
		Statistic statistic = new Statistic("Firefox 7", "Computer", "Windows XP", url);
		statisticService.create(statistic);
		Mockito.verify(repository).save(statistic);
	}

	@Test
	public void whenMappingFromHeaderAndStatisticReturns() {

		Url url = new Url("MmM3MT", "http://www.docker.com", "12345");
		Map<String, String> headers = new HashMap<>();
		headers.put(HttpHeaders.USER_AGENT.toLowerCase(),
				"Mozilla/5.0 (Windows NT 5.1; rv:7.0.1) Gecko/20100101 Firefox/7.0.1");
		Statistic statistic = statisticService.extractStatsFromRequest(headers, url);
		assertEquals("Firefox 7", statistic.getBrowser());
		assertEquals("Computer", statistic.getDeviceType());
		assertEquals("Windows XP", statistic.getOperatingSystem());
		assertEquals(url, statistic.getUrl());
	}

	@Test
	public void whenGettingStatisticSummaryReturnsResultsFromRepository() {
		Long numberOfHits = 3L;

		StatisticsDTO firefox = new StatisticsDTO("Firefox", 1L);
		StatisticsDTO chrome = new StatisticsDTO("Chrome", 2L);
		List<StatisticsDTO> browsers = Arrays.asList(new StatisticsDTO[] { firefox, chrome });
		StatisticsDTO computer = new StatisticsDTO("Computer", 1L);
		StatisticsDTO mobile = new StatisticsDTO("Mobile", 1L);
		StatisticsDTO tablet = new StatisticsDTO("Tablet", 1L);
		List<StatisticsDTO> deviceTypes = Arrays.asList(new StatisticsDTO[] { computer, mobile, tablet });
		Long totalOfLinuxHits = 3L;
		StatisticsDTO linux = new StatisticsDTO("Linux", totalOfLinuxHits);
		List<StatisticsDTO> operationSystems = Arrays.asList(new StatisticsDTO[] { linux });
		Mockito.when(repository.getNumberOfHits()).thenReturn(numberOfHits);
		Mockito.when(repository.getBrowsers()).thenReturn(browsers);
		Mockito.when(repository.getDevicesTypes()).thenReturn(deviceTypes);
		Mockito.when(repository.getOperatingSystems()).thenReturn(operationSystems);
		StatisticsSummaryDTO statisticsSummary = statisticService.getStatisticsSummary();
		assertEquals(numberOfHits, statisticsSummary.getNumberOfHits());
		assertEquals(2, statisticsSummary.getBrowsers().size());
		assertEquals(3, statisticsSummary.getDevicesTypes().size());
		assertEquals(1, statisticsSummary.getOperatingSystems().size());
		assertEquals(totalOfLinuxHits, statisticsSummary.getOperatingSystems().get(0).getTotal());
	}

	@Test
	public void whenGettingStatisticSummaryByCodeReturnsResultsFromRepository() {
		String code = "MmM3MT";
		Long numberOfHits = 3L;

		StatisticsDTO firefox = new StatisticsDTO("Firefox", 1L);
		StatisticsDTO chrome = new StatisticsDTO("Chrome", 2L);
		List<StatisticsDTO> browsers = Arrays.asList(new StatisticsDTO[] { firefox, chrome });
		StatisticsDTO computer = new StatisticsDTO("Computer", 1L);
		StatisticsDTO mobile = new StatisticsDTO("Mobile", 1L);
		StatisticsDTO tablet = new StatisticsDTO("Tablet", 1L);
		List<StatisticsDTO> deviceTypes = Arrays.asList(new StatisticsDTO[] { computer, mobile, tablet });
		Long totalOfLinuxHits = 2L;
		StatisticsDTO linux = new StatisticsDTO("Linux", totalOfLinuxHits);
		StatisticsDTO windows = new StatisticsDTO("Windows", 1L);
		List<StatisticsDTO> operationSystems = Arrays.asList(new StatisticsDTO[] { linux, windows });
		Mockito.when(repository.getNumberOfHitsByCode(code)).thenReturn(numberOfHits);
		Mockito.when(repository.getBrowsersByCode(code)).thenReturn(browsers);
		Mockito.when(repository.getDevicesTypesByCode(code)).thenReturn(deviceTypes);
		Mockito.when(repository.getOperatingSystemsByCode(code)).thenReturn(operationSystems);
		StatisticsSummaryDTO statisticsSummary = statisticService.getStatisticsSummaryByCode(code);
		assertEquals(numberOfHits, statisticsSummary.getNumberOfHits());
		assertEquals(2, statisticsSummary.getBrowsers().size());
		assertEquals(3, statisticsSummary.getDevicesTypes().size());
		assertEquals(2, statisticsSummary.getOperatingSystems().size());
		assertEquals(totalOfLinuxHits, statisticsSummary.getOperatingSystems().get(0).getTotal());
	}

}
