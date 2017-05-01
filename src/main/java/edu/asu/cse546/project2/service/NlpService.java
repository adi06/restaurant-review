package edu.asu.cse546.project2.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;

import edu.asu.cse546.project2.model.nlp.AnalyzeRequest;
import edu.asu.cse546.project2.model.nlp.AnalyzeResponse;
import edu.asu.cse546.project2.model.nlp.Document;
import edu.asu.cse546.project2.model.nlp.Sentiment;

public class NlpService {
	private static final String KEY = "key=AIzaSyC3_bD_noAsyDhDBRKwmGCT-7tUxm3pmpA";
	private static final String BASE_URL = "https://language.googleapis.com/v1/documents:analyzeSentiment?";
	private static final String TYPE = "PLAIN_TEXT";
	private static final String ENCODING = "UTF8";
	private HttpURLConnection conn;
	private static final Gson gson = new Gson();

	public Sentiment analyzeSentimentText(String text) throws IOException {
		Sentiment sentiment = null;
		try {
			Document document = new Document();
			document.setType(TYPE);
			document.setContent(text);
			AnalyzeRequest request = new AnalyzeRequest();
			request.setDocument(document);
			request.setEncodingType(ENCODING);
			URL url = new URL(BASE_URL + KEY);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			os.write(gson.toJson(request).getBytes("UTF-8"));
			os.close();
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			StringBuilder str = new StringBuilder();
			String output;
			while ((output = br.readLine()) != null) {
				str.append(output);
			}

			AnalyzeResponse response = gson.fromJson(str.toString(),
					new com.google.appengine.repackaged.com.google.gson.reflect.TypeToken<AnalyzeResponse>() {
					}.getType());
			sentiment = response.getDocumentSentiment();
		} finally {
			conn.disconnect();
		}
		return sentiment;
	}
}
