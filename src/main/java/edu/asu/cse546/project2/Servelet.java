package edu.asu.cse546.project2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.asu.cse546.project2.model.nlp.Sentiment;
import edu.asu.cse546.project2.model.opendata.OpenDataLIVEResponse;
import edu.asu.cse546.project2.model.yelp.Review;
import edu.asu.cse546.project2.model.yelp.Reviews;
import edu.asu.cse546.project2.service.NlpService;
import edu.asu.cse546.project2.service.OpenDataLIVEService;
import edu.asu.cse546.project2.service.YelpService;
import edu.asu.cse546.project2.view.RestaurantReviewVO;

@SuppressWarnings("serial")
public class Servelet extends HttpServlet {
	private static final Gson GSON = new Gson();
	private static final YelpService YELP_SERVICE = new YelpService();
	private static final NlpService NLP_SERVICE = new NlpService();
	private static final OpenDataLIVEService OPEN_DATA_LIVE_SERVICE = new OpenDataLIVEService();
	private static final Logger LOG = Logger.getLogger(Servelet.class.getName());
	
	public void doGet(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//String name = req.getParameter("hotel");
		String name = "north-india-restaurant-san-francisco";
		
		Reviews reviews = getYelpReviews(name);
		OpenDataLIVEResponse openDataLIVEResponse = getOpenDataLIVEReview(name);
		
		//score between 0-1
		float score = getSentimentScore(reviews);
		
		RestaurantReviewVO vo = new RestaurantReviewVO();
		vo.setScore(score);
		vo.setRiskCategory(openDataLIVEResponse.getRiskCategory());
		vo.setViolation(openDataLIVEResponse.getViolationDescription());
		
		resp.setContentType("application/json");
		resp.setStatus(200);
		PrintWriter pw = resp.getWriter();
		pw.write(GSON.toJson(vo, RestaurantReviewVO.class));
		pw.flush();
		pw.close();

	}

	private float getSentimentScore(Reviews reviews) {
		List<Sentiment> sentiments = new ArrayList<>();
		for(Review review : reviews.getReviews()){
			sentiments.add(NLP_SERVICE.analyzeSentimentText(review.getText()));
		}
		float score = 0;
		for(Sentiment sentiment : sentiments){
			score += Math.abs(sentiment.getScore());
		}
		return score/sentiments.size();
	}

	private OpenDataLIVEResponse getOpenDataLIVEReview(String name) {
		name = "Tiramisu Kitchen";
		OpenDataLIVEResponse openDataLIVEResponses[] = OPEN_DATA_LIVE_SERVICE.getLatestRestaurantReviewByName(name);
		for(OpenDataLIVEResponse response : openDataLIVEResponses){
			if(response.getRiskCategory() != null)
				return response;
		}
		return null;
	}

	private Reviews getYelpReviews(String name) {
		return YELP_SERVICE.getReviews(name);
	}
}
