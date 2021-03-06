package com.kodilla.spring.library;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public final class Library {

    private final List<String> books = new ArrayList<>();
    private LibraryDbController libraryDbController;


    public Library(LibraryDbController libraryDbController) {
        this.libraryDbController = libraryDbController;
    }

    public Library () {
        //do Nothing
    }

    public void saveDoDb() {
        libraryDbController.saveData();
    }

    public void loadFrom() {
        libraryDbController.loadData();
    }
}
