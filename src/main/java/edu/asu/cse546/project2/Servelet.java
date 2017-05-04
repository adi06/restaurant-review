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
import edu.asu.cse546.project2.model.yelp.Business;
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

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		// String name = req.getParameter("hotel");
		RestaurantReviewVO vo = new RestaurantReviewVO();
		String name = "north-india-restaurant-san-francisco";
		try {
			Reviews reviews = getYelpReviews(name);
//			OpenDataLIVEResponse openDataLIVEResponse = getOpenDataLIVEReview(name);
			Business business = getBusiness(name);

			// score between 0-1
			float score = getSentimentScore(reviews);

			vo.setScore(score);
			vo.setRiskCategory(openDataLIVEResponse.getRiskCategory());
			vo.setViolation(openDataLIVEResponse.getViolationDescription());
			vo.setReviews(reviews.getReviews());
			vo.setImageUrl(business.getImage_url());
			vo.setCoordinates(business.getCoordinates());
			vo.setDisplayAddress(business.getLocation().getDisplay_address());
			vo.setName(business.getName());
			vo.setRating(business.getRating());

			resp.setContentType("application/json");
			resp.setStatus(200);
			PrintWriter pw = resp.getWriter();
			pw.write(GSON.toJson(vo, RestaurantReviewVO.class));
			pw.flush();
			pw.close();
		} catch (IOException ioe) {
			resp.setStatus(400);
			LOG.severe(ioe.getMessage());
		} catch (RuntimeException re) {
			resp.setStatus(500);
			LOG.severe(re.getMessage());
		} catch (Exception e) {
			resp.setStatus(500);
			LOG.severe(e.getMessage());
		}
	}

	private float getSentimentScore(Reviews reviews) throws IOException {
		List<Sentiment> sentiments = new ArrayList<>();
		for (Review review : reviews.getReviews()) {
			sentiments.add(NLP_SERVICE.analyzeSentimentText(review.getText()));
		}
		float score = 0;
		for (Sentiment sentiment : sentiments) {
			score += Math.abs(sentiment.getScore());
		}
		return score / sentiments.size();
	}

	private OpenDataLIVEResponse getOpenDataLIVEReview(String name) throws IOException {
		name = "Tiramisu Kitchen";
		OpenDataLIVEResponse openDataLIVEResponses[] = OPEN_DATA_LIVE_SERVICE.getLatestRestaurantReviewByName(name);
		for (OpenDataLIVEResponse response : openDataLIVEResponses) {
			if (response.getRiskCategory() != null)
				return response;
		}
		return null;
	}

	private Reviews getYelpReviews(String name) throws IOException {
		return YELP_SERVICE.getReviews(name);
	}
	
	private Business getBusiness(String name) throws IOException {
		return YELP_SERVICE.searchRestaurant(name);
	}
}
