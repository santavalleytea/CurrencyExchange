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
    PImage logo;

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
    }

    public void settings() {
        // setting the size of the app
        size(width, height);
    }

    @Override
    public void draw() {
        // drawing the background image
        fill(255,249,254);
        rect(0, height/2, width, height/2);

        fill(92,16,73);
        rect(0, 0, width, height/2);

        image(logo, 25, -15);

        // Shadow properties
        fill(0, 0, 0, 50);  // Shadow color: semi-transparent black
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
        float shadowOffset = 8;  // Distance of shadow around the rectangle
        rect(rectX - shadowOffset, rectY - shadowOffset, rectW + 2 * shadowOffset, rectH + 2 * shadowOffset, cornerRadius + 5);

        // Main rectangle properties
        fill(255,249,254);  // Blue color
        noStroke();

        // Draw the main rounded rectangle
        rect(rectX, rectY, rectW, rectH, cornerRadius);

    }

    public static void main(String[] args) {
        PApplet.main("CurrencyExchange.App");
    }
}
