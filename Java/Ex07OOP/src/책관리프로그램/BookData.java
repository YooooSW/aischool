package 책관리프로그램;

public class BookData {

	private String title;
	private int price;
	private String writer;

	public BookData(String title, int price, String writer) {
		this.title = title;
		this.price = price;
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public int getPrice() {
		return price;
	}

	public String getWriter() {
		return writer;
	}

}
