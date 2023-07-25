package utility;

public class AsideTest {

	public static void main(String[] args) {
		String fileName = "img_61.png";
		AsideUtil au = new AsideUtil();
		fileName = au.squareImage(fileName);
		System.out.println(fileName);

	}

}
