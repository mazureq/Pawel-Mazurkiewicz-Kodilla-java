package com.kodilla.testing.library;

import javafx.beans.binding.When;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class BookDirectoryTestSuite {

    @Test
    public void testBookWithConditionsReturnList(){
        //Given
        LibraryDatabase libraryDatabaseMock = mock(LibraryDatabase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        List<Book> resultListOfBooks = new ArrayList<>();
        Book book1 = new Book("Secrets of Alamo", "John Smith", 2008);
        Book book2 = new Book("Secretaries and Directors", "Dilbert Michigan", 2012);
        Book book3 = new Book("Secret life of programmers", "Steve Wolkowitz", 2016);
        Book book4 = new Book("Secrets of Java", "Ian Tenewitch", 2010);
        resultListOfBooks.add(book1);
        resultListOfBooks.add(book2);
        resultListOfBooks.add(book3);
        resultListOfBooks.add(book4);

        when(libraryDatabaseMock.listBooksWithCondition("Secret")).thenReturn(resultListOfBooks);

        //When
        List<Book> theListOfBooks = bookLibrary.listBooksWithCondition("Secret");

        //Then
        assertEquals(4, theListOfBooks.size());
    }

    private List<Book> generateListOfNBooks(int booksQuantity){
        List<Book> resultList = new ArrayList<>();
        for(int n = 1; n<=booksQuantity; n++){
            Book theBook = new Book("Title" + n, "Author" + n, 1970 + n);
            resultList.add(theBook);
        }
        return resultList;
    }

    @Test
    public void testListBooksWithConditionMoreThan20() {

        //Given
        LibraryDatabase libraryDatabaseMock = mock(LibraryDatabase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);

        List<Book> resultListOf0Books = new ArrayList<>();
        List<Book> resultListOf15Books = generateListOfNBooks(15);
        List<Book> resultListOf40Books = generateListOfNBooks(40);

        when(libraryDatabaseMock.listBooksWithCondition(anyString())).thenReturn(resultListOf15Books);
        when(libraryDatabaseMock.listBooksWithCondition("Zero Books")).thenReturn(resultListOf0Books);
        when(libraryDatabaseMock.listBooksWithCondition("40 Books")).thenReturn(resultListOf40Books);

        //When
        List<Book> theListOfBooks0 = bookLibrary.listBooksWithCondition("Zero Books");
        List<Book> theListOfBooks15 = bookLibrary.listBooksWithCondition("Any title");
        List<Book> theListOfBooks40 = bookLibrary.listBooksWithCondition("40 Books");

        //Then
        Assert.assertEquals(0, theListOfBooks0.size());
        Assert.assertEquals(15, theListOfBooks15.size());
        Assert.assertEquals(0, theListOfBooks40.size());

    }

    @Test
    public void testListBooksWithConditionFragmentShorterThan3() {

        //Given
        LibraryDatabase libraryDatabaseMock = mock(LibraryDatabase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
        List<Book>resultListOfBooks10 = generateListOfNBooks(10);
        when(libraryDatabaseMock.listBooksWithCondition(anyString())).thenReturn(resultListOfBooks10);

        //When
        List<Book> theListOfBooks10 = bookLibrary.listBooksWithCondition("as");

        //Then
        Assert.assertEquals(0, theListOfBooks10.size());
        verify(libraryDatabaseMock, times(0)).listBooksWithCondition(anyString());

    }

    @Test
    public void testListBooksInHandsOfNoBooksBorrowed(){

        //Given
        LibraryDatabase libraryDatabaseMock = mock(LibraryDatabase.class);
        BookLibrary bookLibrary = new BookLibrary(libraryDatabaseMock);
                //generating different cases of books renting
        List<Book> noBooksList = new ArrayList<>();
        List<Book> oneBookList = generateListOfNBooks(1);
        List<Book> fiveBooksList = generateListOfNBooks(5);
                //generating different LibraryUsers
        LibraryUser user1 = new LibraryUser("John", "Smith", "88888888888");
        LibraryUser user2 = new LibraryUser("Mike", "Hampton", "77777777777");
        LibraryUser user3 = new LibraryUser("Jenny", "Jenny", "66666666666");

        when(libraryDatabaseMock.listBooksInHandsOf(user1)).thenReturn(noBooksList);
        when(libraryDatabaseMock.listBooksInHandsOf(user2)).thenReturn(oneBookList);
        when(libraryDatabaseMock.listBooksInHandsOf(user3)).thenReturn(fiveBooksList);

        //When
        List<Book> theNoBooksList = bookLibrary.listBooksInHandsOf(user1);
        List<Book> theOneBookList = bookLibrary.listBooksInHandsOf(user2);
        List<Book> theFiveBooksList = bookLibrary.listBooksInHandsOf(user3);

        //Then
        Assert.assertEquals(0, theNoBooksList.size());
        Assert.assertEquals(1, theOneBookList.size());
        Assert.assertEquals(5, theFiveBooksList.size());

    }

}
