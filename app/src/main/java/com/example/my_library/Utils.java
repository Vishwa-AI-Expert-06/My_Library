package com.example.my_library;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private static final String ALL_BOOKS_KEY = "all_books";
    private static final String ALREADY_READ_BOOKS = "already_read_books";
    private static final String WANT_TO_READ_BOOKS= "want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS = "currently_reading_books";
    private static final String FAVOURITE_BOOKS = "favourite_books";

    private static Utils instance;

    private SharedPreferences sharedPreferences;


    /*private static ArrayList<Book> allbooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> favouriteBooks;*/

    private Utils(Context context) {

        sharedPreferences = context.getSharedPreferences("alternate_db",Context.MODE_PRIVATE);

        if (null == getAllbooks()){
            initData();
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        if (null == getAlreadyReadBooks()){
            editor.putString(ALREADY_READ_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getWantToReadBooks()){
            editor.putString(WANT_TO_READ_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getCurrentlyReadingBooks()){
            editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (null == getFavouriteBooks()){
            editor.putString(FAVOURITE_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
    }

    private void initData() {

        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book(1,"Wings of Fire","Dr.APJ Abdul Kalam",180,"https://www.amazon.in/images/I/71KKZlVjbwL._AC_UY327_QL65_.jpg",
                "Wings of Fire: An Autobiography of Abdul Kalam","Every common man who by his sheer grit and hard work achieves success should share his story with the rest for they may find inspiration and strength to go on, in his story. The 'Wings of Fire' is one such autobiography by visionary scientist Dr. APJ Abdul Kalam, who from very humble beginnings rose to be the President of India." +
                " The book is full of insights, personal moments and life experiences of Dr. Kalam. It gives us an understanding on his journey of success. "));
        books.add(new Book(2, "Wings of Fire", "Dr.APJ Abdul Kalam", 180, "https://www.amazon.in/images/I/71KKZlVjbwL._AC_UY327_QL65_.jpg",
                "Wings of Fire: An Autobiography of Abdul Kalam", "Every common man who by his sheer grit and hard work achieves success should share . "));
        books.add(new Book(3, "Wings of Fire", "Dr.APJ Abdul Kalam", 180, "https://www.amazon.in/images/I/71KKZlVjbwL._AC_UY327_QL65_.jpg",
                "Wings of Fire: An Autobiography of Abdul Kalam", "Every common man who by his sheer grit and hard work achieves success should share . "));
        books.add(new Book(4, "Wings of Fire", "Dr.APJ Abdul Kalam", 180, "https://www.amazon.in/images/I/71KKZlVjbwL._AC_UY327_QL65_.jpg",
                "Wings of Fire: An Autobiography of Abdul Kalam", "Every common man who by his sheer grit and hard work achieves success should share . "));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        editor.putString(ALL_BOOKS_KEY,gson.toJson(books));
        editor.commit();

    }

    public static Utils getInstance(Context context) {

        if(null!=instance){
            return instance;
        }else{
            instance = new Utils(context);
            return instance;
        }
    }

    public  ArrayList<Book> getAllbooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY,null),type);
        return books;
    }

    public  ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(ALREADY_READ_BOOKS,null),type);
        return books;
    }

    public  ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS,null),type);
        return books;
    }

    public  ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS,null),type);
        return books;
    }

    public  ArrayList<Book> getFavouriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(sharedPreferences.getString(FAVOURITE_BOOKS,null),type);
        return books;
    }

    public Book getBookById(int id){
        ArrayList<Book> books = getAllbooks();
        if (null != books){
            for (Book b: books){
                if (b.getId() == id){
                    return b;
                }
            }
        }

        return null;
    }

    public boolean addToAlreadyRead(Book book){
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books){
            if (books.add(book)){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(ALREADY_READ_BOOKS);
                editor.putString(ALREADY_READ_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToWantToRead(Book book){
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books){
            if (books.add(book)){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToCurrentlyReadingBooks(Book book){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books){
            if (books.add(book)){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean addToFavouriteBooks(Book book){
        ArrayList<Book> books = getFavouriteBooks();
        if (null != books){
            if (books.add(book)){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(FAVOURITE_BOOKS);
                editor.putString(FAVOURITE_BOOKS,gson.toJson(books));
                editor.commit();
                return true;
            }
        }
        return false;
    }

    public boolean removeFromAlreadyRead(Book book){
        ArrayList<Book> books = getAlreadyReadBooks();
        if (null != books){
            for (Book b: books){
                if (b.getId() == book.getId()){
                    if (books.remove(b)){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        editor.remove(ALREADY_READ_BOOKS);
                        editor.putString(ALREADY_READ_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromCurrentlyReading(Book book){
        ArrayList<Book> books = getCurrentlyReadingBooks();
        if (null != books){
            for (Book b: books){
                if (b.getId() == book.getId()){
                    if (books.remove(b)){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromWantToRead(Book book){
        ArrayList<Book> books = getWantToReadBooks();
        if (null != books){
            for (Book b: books){
                if (b.getId() == book.getId()){
                    if (books.remove(b)){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean removeFromFavouriteBooks(Book book){
        ArrayList<Book> books = getFavouriteBooks();
        if (null != books){
            for (Book b: books){
                if (b.getId() == book.getId()){
                    if (books.remove(b)){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        editor.remove(FAVOURITE_BOOKS);
                        editor.putString(FAVOURITE_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
