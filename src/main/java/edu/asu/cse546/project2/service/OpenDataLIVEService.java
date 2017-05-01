package edu.asu.cse546.project2.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.logging.Logger;

import com.google.gson.Gson;

import edu.asu.cse546.project2.model.opendata.OpenDataLIVEResponse;

public class OpenDataLIVEService {
	private static final String ACCESS_TOKEN = "HY2sbnYq0YNKVY0JEYUxnb6MC";
	private static final String BASE_URL = "https://data.sfgov.org/resource/sipz-fjte.json";
	private static final String QUERY = "SELECT * WHERE business_name=\"{0}\" ORDER BY inspection_date DESC LIMIT 3";
	private static final Gson GSON = new Gson();
	private static final Logger LOG = Logger.getLogger(OpenDataLIVEService.class.getName());

	public OpenDataLIVEResponse[] getLatestRestaurantReviewByName(String name) {
		HttpURLConnection con = null;
		OpenDataLIVEResponse openDataLIVEResponse[] = null;
		BufferedReader br = null;
		try {
			String query = MessageFormat.format(QUERY, name);
			LOG.info("query " + query);
			String uri = BASE_URL + "?$query=" + URLEncoder.encode(query, "UTF-8");
			LOG.info("urlEncode " + uri);
			URL url = new URL(uri);
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("access_token", ACCESS_TOKEN);

			if (con.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + con.getResponseCode());
			}
			br = new BufferedReader(new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
			StringBuilder response = new StringBuilder();
			String output;
			while ((output = br.readLine()) != null) {
				response.append(output);
			}
			openDataLIVEResponse = GSON.fromJson(response.toString(),
					new com.google.appengine.repackaged.com.google.gson.reflect.TypeToken<OpenDataLIVEResponse[]>() {
					}.getType());
			LOG.info("response " + openDataLIVEResponse.toString());
		} catch (Exception e) {
			LOG.severe("" + e);
		} finally {
			LOG.info("Closing HTTP connection");
			if (con != null)
				con.disconnect();
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					LOG.severe(e.getMessage());
				}
		}
		return openDataLIVEResponse;
	}
}
