package com.spring.mvc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
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
	return new ModelAndView("bookList","books",books);
}

@RequestMapping("getBookByIsbn/{isbn}")
public ModelAndView getBookByIsbn(@PathVariable(value="isbn") int isbn){
	Book b=bookService.getBookByIsbn(isbn);
	return new ModelAndView("bookPage","bookObj",b);
}


@RequestMapping(value="admin/delete/{isbn}")
public String deleteBook(@PathVariable(value="isbn") int isbn){
Path path=Paths.get("/resources/images/" + isbn + ".jpg");
	if(Files.exists(path))
			{
		           try {
					Files.delete(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	bookService.deleteBook(isbn);
return "redirect:/getAllBooks";	}


@RequestMapping(value="/admin/book/addBook",method=RequestMethod.GET)
public String getBookForm(Model model){
	Book book=new Book();
	Category category=new Category();
	category.setCid(1);
	book.setCategory(category);
	model.addAttribute("bookFormObj",book);
	return "bookForm";
	
}
@RequestMapping(value="/admin/book/addBook",method=RequestMethod.POST)
public String addBook( @Valid @ModelAttribute(value="bookFormObj")  Book book,BindingResult result){
	if(result.hasErrors())
		return "bookForm";
	bookService.addBook(book);
	
	
MultipartFile image=book.getBookImage();
	if(image!=null && !image.isEmpty()){
	Path path=Paths.get("/resources/images/" + book.getIsbn() + ".jpg");
	try {
		image.transferTo(new File(path.toString()));
	} 
	catch (IllegalStateException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	return "redirect:/getAllBooks";
}


@RequestMapping("/admin/book/editBook/{isbn}")
public ModelAndView getEditForm(@PathVariable(value="isbn")  int isbn){
	Book book=this.bookService.getBookByIsbn(isbn);
	return new ModelAndView("editBookForm","editBookObj",book);
}


@RequestMapping(value="/admin/book/editBook",method=RequestMethod.POST)
public String editBook(@ModelAttribute(value="editBookObj") Book book)
{
bookService.editBook(book);
return "redirect:/getAllBooks";
}



}
