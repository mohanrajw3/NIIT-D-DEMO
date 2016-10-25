package com.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.model.Book;
@Repository
public class BookDaoImpl implements BookDao{
	@Autowired
private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
	return sessionFactory;
}

public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}
	@SuppressWarnings("unchecked")
	public List<Book> getAllBooks() {
		
		Session session=sessionFactory.openSession();
		
		List<Book> books=session.createQuery("from Book").list();
		
		
		return books;
		
	}
	public void addBook(Book book) {
		Session session=sessionFactory.openSession();
		session.save(book);
		session.close();	
}

	public Book getBookByIsbn(int i) {
		
	  Session session=sessionFactory.openSession();
	  Book book=(Book)session.get(Book.class, i);  
	  session.close();
	  return book;
	}
public void deleteBook(int isbn) {
		Session session=sessionFactory.openSession();
		
		Book book=(Book)session.get(Book.class, isbn);
		
		session.delete(book);
		
		session.flush();
		
		session.close();
		
		
	}
public void editBook(Book book) {
	Session session=sessionFactory.openSession();
	session.update(book);
	session.flush();
	session.close();
}


}
