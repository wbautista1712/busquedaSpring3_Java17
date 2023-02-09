package pe.todotic.bookstoreapi_s1.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.todotic.bookstoreapi_s1.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/books")
public class BookController {

    public static List<Book> bookList = new ArrayList<>();

    /**
     * Devuelve la lista completa de libros
     * Retorna el status OK: 200
     * Ej.: GET http://localhost:9090/api/books
     */
    @GetMapping
    List<Book> list() {
        return bookList;
    }

    /**
     * Devuelve un libro por su ID, en caso contrario null.
     * Retorna el status OK: 200
     * Ej.: GET http://localhost:9090/api/books/1
     */
    @GetMapping("/{id}")
    Book get(@PathVariable Integer id) {
        return bookList
                .stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Crea un libro a partir del cuerpo
     * de la solicitud HTTP y retorna
     * el libro creado.
     * Retorna el status CREATED: 201
     * Ej.: POST http://localhost:9090/api/books
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Book create(@RequestBody Book book) {
        // asigna los datos básicos del libro y que deben ser establecidos
        // solo por el programa
        book.setId(new Random().nextInt(256));

        bookList.add(book);
        return book;
    }

    /**
     * Actualiza un libro por su ID, a partir
     * del cuerpo de la solicitud HTTP.
     * Retorna el status OK: 200.
     * Ej.: PUT http://localhost:9090/api/books/1
     */
    @PutMapping("/{id}")
    Book update(@PathVariable Integer id,
            @RequestBody Book bookForm) {
        Book bookFromDb = bookList
                .stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (bookFromDb == null) return null;

        bookFromDb.setTitle(bookForm.getTitle());
        bookFromDb.setPrice(bookForm.getPrice());

        return bookFromDb;
    }

    /**
     * Elimina un libro por su ID.
     * Retorna el status NO_CONTENT: 204
     * Ej.: DELETE http://localhost:9090/api/books/1
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        bookList.removeIf(l -> l.getId().equals(id));
    }

}
