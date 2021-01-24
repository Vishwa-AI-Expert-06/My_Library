package com.example.my_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY = "bookId";

    private TextView BookName, AuthorName, txtPages, txtDescription;
    private Button btnAddToCurrentlyReading, btnAddToWantToReadList, btnAddToAlreadyReadList, btnAddToFavourite;
    private ImageView bookImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initViews();

        /*Book book = new Book(1, "Wings of Fire", "Dr.APJ Abdul Kalam", 180, "https://www.amazon.in/images/I/71KKZlVjbwL._AC_UY327_QL65_.jpg",
                "Wings of Fire: An Autobiography of Abdul Kalam", "Every common man who by his sheer grit and hard work achieves success should share . ");
        Book book1 = new Book(2, "Wings of Fire", "Dr.APJ Abdul Kalam", 180, "https://www.amazon.in/images/I/71KKZlVjbwL._AC_UY327_QL65_.jpg",
                "Wings of Fire: An Autobiography of Abdul Kalam", "Every common man who by his sheer grit and hard work achieves success should share . ");
        Book book2 = new Book(3, "Wings of Fire", "Dr.APJ Abdul Kalam", 180, "https://www.amazon.in/images/I/71KKZlVjbwL._AC_UY327_QL65_.jpg",
                "Wings of Fire: An Autobiography of Abdul Kalam", "Every common man who by his sheer grit and hard work achieves success should share . ");
        Book book3 = new Book(4, "Wings of Fire", "Dr.APJ Abdul Kalam", 180, "https://www.amazon.in/images/I/71KKZlVjbwL._AC_UY327_QL65_.jpg",
                "Wings of Fire: An Autobiography of Abdul Kalam", "Every common man who by his sheer grit and hard work achieves success should share . ");*/

        Intent intent = getIntent();
        if (null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY,-1);
            if (bookId != -1){
                Book incomingBook = Utils.getInstance(this).getBookById(bookId);
                if (null != incomingBook){
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavouriteBooks(incomingBook);
                }
            }
        }
    }
    /* Enable and Disable button
    * Add the book to Already Read Book Arraylist*/

    private void handleAlreadyRead(final Book book){
        ArrayList<Book> alreadyReadBooks  = Utils.getInstance(this).getAlreadyReadBooks();
        boolean existInAlreadyReadBooks = false;

        for(Book b: alreadyReadBooks){
            if (b.getId() == book.getId()){
                existInAlreadyReadBooks = true;
            }
        }

        if (existInAlreadyReadBooks) {
            btnAddToAlreadyReadList.setEnabled(false);
        }else{
            btnAddToAlreadyReadList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(BookActivity.this,AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something went wrong!! Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }


    private void handleWantToReadBooks(final Book book){
        ArrayList<Book> wantToReadBooks  = Utils.getInstance(this).getWantToReadBooks();
        boolean existInWantToReadBooks = false;

        for(Book b: wantToReadBooks){
            if (b.getId() == book.getId()){
                existInWantToReadBooks = true;
            }
        }

        if (existInWantToReadBooks) {
            btnAddToWantToReadList.setEnabled(false);
        }else{
            btnAddToWantToReadList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToWantToRead(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(BookActivity.this,WantToReadActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something went wrong!! Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }


    private void handleCurrentlyReadingBooks(final Book book){
        ArrayList<Book> currentlyReadingBooks  = Utils.getInstance(this).getCurrentlyReadingBooks();
        boolean existInCurrentlyReadingBooks = false;

        for(Book b: currentlyReadingBooks){
            if (b.getId() == book.getId()){
                existInCurrentlyReadingBooks = true;
            }
        }

        if (existInCurrentlyReadingBooks) {
            btnAddToCurrentlyReading.setEnabled(false);
        }else{
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToCurrentlyReadingBooks(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(BookActivity.this,CurrentlyReadingActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something went wrong!! Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }


    private void handleFavouriteBooks(final Book book){
        ArrayList<Book> favouriteBooks  = Utils.getInstance(this).getFavouriteBooks();
        boolean existInFavouriteBooks = false;

        for(Book b: favouriteBooks){
            if (b.getId() == book.getId()){
                existInFavouriteBooks = true;
            }
        }

        if (existInFavouriteBooks) {
            btnAddToFavourite.setEnabled(false);
        }else{
            btnAddToFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Utils.getInstance(BookActivity.this).addToFavouriteBooks(book)){
                        Toast.makeText(BookActivity.this, "Book Added", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(BookActivity.this,FavouriteBookActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "Something went wrong!! Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void setData(Book book) {
        BookName.setText(book.getName());
        AuthorName.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPage()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap().load(book.getImgUrl())
                .into(bookImg);
    }

    private void initViews() {
        BookName = findViewById(R.id.BookName);
        AuthorName = findViewById(R.id.AuthorName);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescription);

        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToAlreadyReadList = findViewById(R.id.btnAddToAlreadyReadList);
        btnAddToWantToReadList = findViewById(R.id.btnAddToWantToReadList);
        btnAddToFavourite = findViewById(R.id.btnAddToFavourite);

        bookImg = findViewById(R.id.bookImg);
    }


}