package pe.todotic.bookstoreapi_s1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.todotic.bookstoreapi_s1.model.Book;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RestController("BookController")
@RequestMapping("/api")
public class BookController
{
    static List<Book> oLstBook=new ArrayList();
    List<Book> oLstBookSearch;

    @GetMapping("/list")
        // @Produces(MediaType.APPLICATION_JSON)
    List<Book> listBook()
    {
        return  oLstBook;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    Book createBook(@RequestBody Book book)
    {
        oLstBook.add(book);
        return  book;
    }

    @RequestMapping(value ="/books" , params="title")
    public List<Book> searchBook(@RequestParam String title) {
        /*
        oLstBookSearch=new ArrayList();
        Iterator<Book> it = oLstBook.iterator();
        while(it.hasNext()){
            Book item=it.next();

            if(item.getTitle().contains(title))
            {
                oLstBookSearch.add(item);
            }
        }

        return oLstBookSearch;
        */

        return oLstBook.stream().filter((p)->p.getTitle().contains(title)).collect(Collectors.toList());

    }
}


