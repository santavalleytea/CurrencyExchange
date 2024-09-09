/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package CurrencyExchange;

import CurrencyExchange.FileHandlers.*;
import CurrencyExchange.Users.AdminLogin;


import java.io.*;
import java.util.*;
import java.nio.file.*;
import processing.data.*;
import processing.core.*;


public class App extends PApplet{
    Json Json;
    AdminLogin AdminLogin;
    Database Database;


    static int width = 1920/2;
    static int height = 1080/2;
    PImage logo;
    Flag flag;

    // Could be modified
    String selectedFromCurrency = "AUS";
    String selectedToCurrency = "USA";

    @Override
    public void setup() {
        //initialise json file 
        String jsonFilepath = "src/main/java/resources/main/config.json";
        Json = new Json(loadJSONObject(jsonFilepath), jsonFilepath);

        //initialise database 
        String databaseFilePath = "src/main/java/resources/main/database.db";
        Database = new Database(databaseFilePath);
        Database.initialiseDatabase();

        //initialise admin login  
        String loginFilepath = "src/main/java/resources/main/admin.json";
        AdminLogin = new AdminLogin(loadJSONObject(loginFilepath), loginFilepath);

        // load the logo
        logo = loadImage("src/main/resources/logo.png");
        logo.resize(492/2, 187/2);


        flag = new Flag(this);
        flag.loadFlag(selectedFromCurrency);  // Load UK flag
        flag.loadFlag(selectedToCurrency);
    }

    public void settings() {
        // setting the size of the app
        size(width, height);
    }

    @Override
    public void draw() {
        // drawing the background colours - dark pink
        fill(255,249,254);
        rect(0, height/2, width, height/2);

        // drawing the background colours - light pink
        fill(92,16,73);
        rect(0, 0, width, height/2);

        // drawing the logo
        image(logo, 25, -15);

        // Shadow properties
        fill(0, 0, 0, 50);
        noStroke();

        // Canvas center
        int centerX = width/2;
        int centerY = height/2;

        // Rectangle properties
        float rectW = width-100;
        float rectH = height/2;
        float cornerRadius = 20;

        // Calculate position to center the rectangle
        float rectX = centerX - rectW / 2;
        float rectY = centerY - rectH / 2;

        // Shadow offset
        float shadowOffsetX = 10;
        float shadowOffsetY = 10;

        // Draw the shadow all around (slightly larger than the rectangle)
        float shadowOffset = 8;
        rect(rectX - shadowOffset, rectY - shadowOffset, rectW + 2 * shadowOffset, rectH + 2 * shadowOffset, cornerRadius + 5);

        // Main rectangle properties
        fill(255,249,254);
        noStroke();

        // Draw the main rounded rectangle
        rect(rectX, rectY, rectW, rectH, cornerRadius);

        flag.drawFlag(selectedFromCurrency, 100, 180);
        flag.drawFlag(selectedToCurrency, 100, 280);

    }

    public static void main(String[] args) {
        PApplet.main("CurrencyExchange.App");
    }
}