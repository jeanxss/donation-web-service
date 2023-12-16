package model.service;

public class ArticleNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	public ArticleNotFoundException() {
		super();
	}

	public ArticleNotFoundException(String arg0) {
		super(arg0);
	}
}
