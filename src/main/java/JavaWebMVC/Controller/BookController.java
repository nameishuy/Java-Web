package JavaWebMVC.Controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import Model.Book;
import Model.Chude;

@Controller
public class BookController {

	public ArrayList<Book> GetlistBook() {
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

	public ArrayList<Book> GetlistBook2() {

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

	public ArrayList<Book> GetlistBook3() {

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

	@RequestMapping(value = { "/products" })
	public ModelAndView Products(HttpServletRequest req) throws UnsupportedEncodingException {
		String link = "https://bookingapiiiii.herokuapp.com/chude";
		String check = JavaWebMVC.API.CallAPI.Get(link).toString();
		req.setCharacterEncoding("UTF-8");
		ArrayList<Chude> CD = new ArrayList<Chude>();
		if (check != null) {
			JSONArray json = new JSONArray(JavaWebMVC.API.CallAPI.Get(link).toString());
			json.forEach(data -> {
				JSONObject jsonobject = (JSONObject) data;
				Chude chude = new Chude();
				chude.set_id(jsonobject.getString("_id"));
				chude.setTenChuDe(jsonobject.getString("TenChuDe"));
				CD.add(chude);
			});
		}
		System.out.println(req.getParameter("Sort"));
		ModelAndView mv = new ModelAndView("/user/products");
		mv.addObject("Chude", CD);
		mv.addObject("keyword", req.getParameter("keyword"));
		return mv;
	}

	public int Cout(HttpServletRequest req) throws UnsupportedEncodingException {
		String linkbook = null;
		req.setCharacterEncoding("UTF-8");
		if (req.getParameter("chude") != null) {
			linkbook = "https://bookingapiiiii.herokuapp.com/sachbyCD/" + req.getParameter("chude");

		} else if (req.getParameter("keyword") != null) {
			byte[] key = req.getParameter("keyword").getBytes();
			String keyword = new String(key, "UTF-8");
			linkbook = "https://bookingapiiiii.herokuapp.com/sachbyname/" + keyword;
		} else {
			linkbook = "https://bookingapiiiii.herokuapp.com/sach";
		}
		boolean check = JavaWebMVC.API.CallAPI.Get(linkbook) == null;
		if (!check) {
			JSONArray json = new JSONArray(JavaWebMVC.API.CallAPI.Get(linkbook).toString());
			return json.length();
		}
		return 0;
	}

	public ArrayList<Book> getList(HttpServletRequest req, int Frist, int Last) throws UnsupportedEncodingException {
		String link = "sachpagination";
		req.setCharacterEncoding("UTF-8");
		if (req.getParameter("chude") != null) {
			link = "sachpaginationbychude/" + req.getParameter("chude");
		}
		if (req.getParameter("keyword") != null) {
			byte[] key = req.getParameter("keyword").getBytes();
			String keyword = new String(key, "UTF-8");
			link = "sachpaginationSearch/" + keyword;
		}

		String linkbook = "https://bookingapiiiii.herokuapp.com/" + link + "/" + Frist + "/" + Last;
		boolean check = JavaWebMVC.API.CallAPI.Get(linkbook) == null;
		ArrayList<Book> book = new ArrayList<Book>();

		if (!check) {
			JSONArray json = new JSONArray(JavaWebMVC.API.CallAPI.Get(linkbook).toString());
			json.forEach(data -> {
				JSONObject jsonobject = (JSONObject) data;
				Book modelbook = new Book();
				if (jsonobject.has("Messager")) {
					modelbook.setMessager(jsonobject.getString("Messager"));
				} else {
					modelbook.setID(jsonobject.getString("id"));
					modelbook.setTensach(jsonobject.getString("Tensach"));
					modelbook.setMessager(null);
					modelbook.setAnh(jsonobject.getString("Anh"));
					modelbook.setTenTG(jsonobject.getString("TenTG"));
					modelbook.setGiaban(jsonobject.getDouble("Giaban"));
				}
				book.add(modelbook);
			});
		} else {
			Book modelbook = new Book();
			modelbook.setMessager("Không Có Sách Này");
			book.add(modelbook);
		}
		return book;
	}

}
