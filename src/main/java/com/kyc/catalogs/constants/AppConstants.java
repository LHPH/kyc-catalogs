package com.kyc.catalogs.constants;

public class AppConstants {

	public static final String ENDPOINT_CATALOG_INDEX = "/catalogs";
	public static final String ENDPOINT_CATALOG="/catalogs/{catalog}";
	public static final String ENDPOINT_CATALOG_CRITERIA="/catalogs/single/{catalog}/{criteria}";
	public static final String ENDPOINT_CATALOG_CLEAN_CACHE = "/cache/eviction";

	public static final String MESSAGE_001 = "001";
	public static final String MESSAGE_002 = "002";
	public static final String MESSAGE_003 = "003";

	public static final String PATH_PARAM_CATALOG = "catalog";
	public static final String PATH_PARAM_CRITERIA = "criteria";


	private AppConstants(){}

}
