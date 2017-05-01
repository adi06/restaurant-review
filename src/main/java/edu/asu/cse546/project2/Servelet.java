package edu.asu.cse546.project2;

import java.io.IOException;
import java.util.ArrayList;

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



//import com.google.appengine.repackaged.com.google.gson.Gson;

@SuppressWarnings("serial")
public class Servelet extends HttpServlet {
	public void doGet(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/plain");
		String action = req.getParameter("hotel");
		 System.out.println(action);
		String hotel = "north-india-restaurant-san-francisco";
		YelpService yelp = new YelpService();
		NlpService nlp=new NlpService();
		OpenDataLIVEService openDataLIVEService = new OpenDataLIVEService();

		// Print result of yelp API
		Gson gson = new Gson();
		/*System.out.print(gson.toJson(yelp.searchRestaurant(hotel)));
		System.out.print(gson.toJson(yelp.getReviews(hotel)));
		// Print result of NLP API
		System.out.print(gson.toJson(nlp.analyzeSentimentText("Enjoy your vacation!")));*/
		
		Reviews reviews = yelp.getReviews(hotel);
		System.out.println("reviews:" +gson.toJson(reviews));
		OpenDataLIVEResponse openDataLIVEResponses[] = openDataLIVEService.getLatestRestaurantReviewByName("Tiramisu Kitchen");
		for(OpenDataLIVEResponse openDataLIVEResponse : openDataLIVEResponses){
			System.out.println(openDataLIVEResponse);
		}
		ArrayList<Sentiment> sentiments = new ArrayList<>();
		for(Review review : reviews.getReviews()){
			sentiments.add(nlp.analyzeSentimentText(review.getText()));
		}
		float score = 0;
		for(Sentiment sentiment : sentiments){
			score += Math.abs(sentiment.getScore());
		}
		System.out.println("score: "+score);
		System.out.println("avg score "+(score/sentiments.size()) * 5);
		System.out.println(gson.toJson(sentiments));

	}
}
