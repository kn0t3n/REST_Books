package de.gbsschulen.rest;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/bookstore")
public class BookResource {

    private BookService bookService = new BookService();

    // http://localhost:8080/rest/bookservice/1

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Book getBook(@PathParam("id") int id) {
        Book book = bookService.getBook(id);
        return book;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public String deleteBook(@PathParam("id") int id) {
        Book book = bookService.deleteBook(id);
        if (book != null) {
            return book.getTitel() + " wurde aus DB gelöscht";
        }
        return "nicht gelöscht!";
    }

    // localhost:8080/rest/bookstore/book?id=2
    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/book/")
    public Book gibBuch(@QueryParam("id") int id) {
        Book book = bookService.getBook(id);
        return book;
    }


    // curl  -H "content-type: application/json" -X PUT -d '{"id":55,"autor":"fdsaf","isbn":"2222","titel":"fdsaf"}' localhost:8080/rest/bookstore/createBook
    // curl  -H "content-type: application/json" -X PUT -T file.json localhost:8080/rest/bookstore/createBook
    @PUT
    @Path("/createBook")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String putBook(Book book) {
        bookService.add(book);
        System.out.println(book);
        return "Hinzugefügt: " + book.getTitel();
    }


    //curl  -H "content-type: application/json"  -X POST -d '{"id":11,"autor":"fdfdfdfaf","isbn":"2222","titel":"fdsaf"}' localhost:8080/rest/bookstore/changeBook

    @POST
    @Path("/changeBook")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String change(Book book) {
        bookService.change(book);
        return "Geändert: " + book.toString();
    }
}
