package JavaWebMVC.Controller;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import Model.Book;

@Controller
public class BookController {
	
	public  ArrayList<Book> GetlistBook() {
		String link = "https://bookingapiiiii.herokuapp.com/sachbanchayfirst";

		String check1 = JavaWebMVC.API.CallAPI.Get(link).toString();

		ArrayList<Book> book1 = new ArrayList<Book>();

		if (check1 != null) {
			JSONArray json = new JSONArray(JavaWebMVC.API.CallAPI.Get(link).toString());
			json.forEach(data -> {
				JSONObject jsonobject = (JSONObject) data;
				Book book = new Book();
				book.setID(jsonobject.getString("id"));
				book.setTensach(jsonobject.getString("Tensach"));
				book.setAnh(jsonobject.getString("Anh"));
				book.setTenTG(jsonobject.getString("TenTG"));
				book1.add(book);
			});
		}
		return book1;
	}

	public  ArrayList<Book> GetlistBook2() {

		String link2 = "https://bookingapiiiii.herokuapp.com/sachbanchaysecond";

		String check2 = JavaWebMVC.API.CallAPI.Get(link2).toString();

		ArrayList<Book> book2 = new ArrayList<Book>();

		if (check2 != null) {
			JSONArray json2 = new JSONArray(JavaWebMVC.API.CallAPI.Get(link2).toString());
			json2.forEach(data -> {
				JSONObject jsonobject = (JSONObject) data;
				Book book = new Book();
				book.setID(jsonobject.getString("id"));
				book.setTensach(jsonobject.getString("Tensach"));
				book.setAnh(jsonobject.getString("Anh"));
				book.setTenTG(jsonobject.getString("TenTG"));
				book2.add(book);
			});
		}
		return book2;
	}

	public  ArrayList<Book> GetlistBook3() {

		String link3 = "https://bookingapiiiii.herokuapp.com/sachtimestamps";

		String check3 = JavaWebMVC.API.CallAPI.Get(link3).toString();

		ArrayList<Book> book3 = new ArrayList<Book>();

		if (check3 != null) {
			JSONArray json3 = new JSONArray(JavaWebMVC.API.CallAPI.Get(link3).toString());
			json3.forEach(data -> {
				JSONObject jsonobject = (JSONObject) data;
				Book book = new Book();
				book.setID(jsonobject.getString("id"));
				book.setTensach(jsonobject.getString("Tensach"));
				book.setAnh(jsonobject.getString("Anh"));
				book.setTenTG(jsonobject.getString("TenTG"));
				book.setMota(jsonobject.getString("Mota"));
				book3.add(book);
			});
		}
		return book3;
	}

}
