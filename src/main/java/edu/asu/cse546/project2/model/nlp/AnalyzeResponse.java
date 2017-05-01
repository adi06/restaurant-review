package edu.asu.cse546.project2.model.nlp;

import java.util.ArrayList;

public class AnalyzeResponse {
	private Sentiment documentSentiment;
	private String language;
	private ArrayList<Sentence> sentences;

	public Sentiment getDocumentSentiment() {
		return documentSentiment;
	}

	public void setDocumentSentiment(Sentiment documentSentiment) {
		this.documentSentiment = documentSentiment;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public ArrayList<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(ArrayList<Sentence> sentences) {
		this.sentences = sentences;
	}
}
