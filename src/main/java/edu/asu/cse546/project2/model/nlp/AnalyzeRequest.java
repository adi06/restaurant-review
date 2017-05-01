package edu.asu.cse546.project2.model.nlp;

public class AnalyzeRequest {
	private String encodingType;
	private Document document;

	public String getEncodingType() {
		return encodingType;
	}

	public void setEncodingType(String encodingType) {
		this.encodingType = encodingType;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
}
