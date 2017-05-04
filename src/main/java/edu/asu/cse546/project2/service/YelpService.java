package edu.asu.cse546.project2.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.asu.cse546.project2.model.yelp.Business;
import edu.asu.cse546.project2.model.yelp.Reviews;

public class YelpService {
	private static final String BASE_URL = "https://api.yelp.com/v3/";
	private static final String SEARCH_URL = "businesses/search";
	private static final String SEARCH_BUSINESS = "businesses/";
	private static final String REVIEWS_URL = "/reviews";
	private static final String KEY = "Bearer Qq4n0uf_IeDA6eSCuDg0JQawSMoFkCZDDM4LfTIq_pIomQl9mOayqypTYkxjvBP2gUCUjQ8VY_MWmUBY5fQK2DtUL5E6KE6FECrrt0h_sLncArZNhJv6Y3Y2E3_1WHYx";
	private HttpURLConnection conn;
	private static final Gson gson = new Gson();

	public Business searchRestaurant(String name) throws IOException {
		Business business = null;
		try {
			URL url = new URL(BASE_URL + SEARCH_BUSINESS + name);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", KEY);
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			StringBuilder response = new StringBuilder();
			String output;
			while ((output = br.readLine()) != null) {
				response.append(output);
			}
			business = gson.fromJson(response.toString(),new TypeToken<Business>() {
					}.getType());
		} finally {
			conn.disconnect();
		}
		return business;
	}

	public Reviews getReviews(String name) throws IOException {
		Reviews reviews = null;
		try {
			URL url = new URL(BASE_URL + SEARCH_BUSINESS + name + REVIEWS_URL);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Authorization", KEY);
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			StringBuilder response = new StringBuilder();
			String output;
			while ((output = br.readLine()) != null) {
				response.append(output);
			}
			reviews = gson.fromJson(response.toString(),new TypeToken<Reviews>() {
					}.getType());
		} finally {
			conn.disconnect();
		}
		return reviews;
	}

}
