package edu.asu.cse546.project2.model.nlp;

public class Sentence {
	private Text text;
	private Sentiment sentiment;

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public Sentiment getSentiment() {
		return sentiment;
	}

	public void setSentiment(Sentiment sentiment) {
		this.sentiment = sentiment;
	}
}
