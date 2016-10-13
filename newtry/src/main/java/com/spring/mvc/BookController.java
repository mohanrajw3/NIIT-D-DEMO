package com.spring.mvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.Book;
import com.spring.model.Category;
import com.spring.services.BookService;
@Controller
public class BookController {
	@Autowired
private BookService bookService;

public BookService getBookService() {
	return bookService;
}

public void setBookService(BookService bookService) {
	this.bookService = bookService;
}

@RequestMapping("/getAllBooks")
public ModelAndView getAllProducts(){
	List<Book> books = bookService.getAllBooks();
	return new ModelAndView("booksList","books",books);
}
@RequestMapping("getBookByIsbn/{isbn}")
public ModelAndView getBookByIsbn(@PathVariable(value="isbn") int isbn){
	Book b=bookService.getBookByIsbn(isbn);
	return new ModelAndView("bookPage","bookObj",b);
}
@RequestMapping("/delete/{isbn}")
public String deleteBook(@PathVariable(value="isbn") int isbn){
	bookService.deleteBook(isbn);
	return "redirect:/getAllBooks";
}

@RequestMapping(value="/admin/book/addBook",method=RequestMethod.GET)
public String getBookForm(Model model){
	Book book=new Book();
	Category category=new Category();
	category.setCid(1);//New Arrivals
	//set the category as 1 for the Book book
	book.setCategory(category);
	model.addAttribute("bookFormObj",book);
	return "bookForm";
	
}
@RequestMapping(value="/admin/book/addBook",method=RequestMethod.POST)
public String addBook(@ModelAttribute(value="bookFormObj")  Book book){
	bookService.addBook(book);
	return "redirect:/getAllBooks";
}



}
