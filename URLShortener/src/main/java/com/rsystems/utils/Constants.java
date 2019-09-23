package com.rsystems.utils;

public class Constants {

	private Constants()
	{
		
	}
	// General
	public static final int URL_CODE_SIZE = 6;
	public static final int MAX_LONG_URL_SIZE = 2048;
	public static final String PATTERN_BREAKING_CHARACTERS = "[\n|\r|\t]";

	// UrlResources
	public static final String FINDING_URL_FOR_REDIRECTING = "Finding url for redirecting, code: {0}";
	public static final String FINDING_OR_CREATING_URL = "Finding or creating url: {0}";

	// UrlService
	public static final String URL_NOT_FOUND = "URL not found";
	public static final String URL_FOUND = "URL already exist";
	public static final String FINDING_URL_BY_CODE = "Finding url, code: {0}";
	public static final String URL_INSERT = " inserting, long url: {0}";

	// StatisticResources
	public static final String GETTING_STATISTICS_SUMMARY = "Getting statistics summary.";
	public static final String GETTING_STATISTICS_SUMMARY_BY_CODE = "Getting statistics summary by code: ";

	// StatisticService
	public static final String CREATING_A_STATISTIC = "Creating a statistic: {0}";
	public static final String MAPPING_STATISTIC_FROM_HEADERS = "Mapping statistic from headers: {0}";

	// UrlShortnerHelper
	public static final String MD5_ALGORITHM_IS_NOT_AVAILABLE = "The MD5 algorithm is not available in the environment.";
	
	
	//  ThirdPartyService
	
	public static final String VALID_USER="validUser";
	public static final String UNAUTHORIZED_USER="UnAuthorized User";

}
