/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package CurrencyExchange;

import CurrencyExchange.FileHandlers.*;
import CurrencyExchange.Users.AdminLogin;
import CurrencyExchange.Users.CurrencyManager;


import java.io.*;
import java.util.*;
import java.nio.file.*;
import processing.data.*;
import processing.core.*;


public class App extends PApplet{
    Json Json;
    AdminLogin AdminLogin;
    Database Database;
    CurrencyConverterUI CurrencyConverterUI;
    PopularUI PopularUI;
    PrintSummaryUI PrintSummaryUI;
    UpdateUI UpdateUI;
    Login Login;

    // Canvas center
    int centerX = width/2;
    int centerY = height/2;

    // Rectangle properties
    float rectW = width-100;
    float rectH = height/2;
    float cornerRadius = 10;

    // Calculate position to center the rectangle
    float rectX = centerX - rectW / 2;
    float rectY = centerY - rectH / 2;

    int finalTab = (int)(rectX+3*rectW/4);

    // Shadow offset
    float shadowOffsetX = 10;
    float shadowOffsetY = 10;

    // Draw the shadow all around (slightly larger than the rectangle)
    float shadowOffset = 8;

    static int width = 1920/2;
    static int height = 1080/2;
    boolean exchangeTabSelected = true;
    boolean popularTabSelected = false;
    boolean printTabSelected = false;
    boolean updateTabSelected = false;
    PImage logo;
    PImage selectedConvert;
    PImage unSelectedConvert;
    PImage selectedPopular;
    PImage unSelectedPopular;
    PImage selectedPrint;
    PImage unSelectedPrint;
    PImage selectedUpdate;
    PImage unSelectedUpdate;
    Flag flag;

    CurrencyManager currencyManager;

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

        // load all other images
        selectedConvert = loadImage("src/main/resources/convert-selected.png");
        selectedConvert.resize(1920 / 40, 1080 / 40);
        unSelectedConvert = loadImage("src/main/resources/convert-not-selected.png");
        unSelectedConvert.resize(1920 / 40, 1080 / 40);
        selectedPopular = loadImage("src/main/resources/popular-selected.png");
        selectedPopular.resize(1920 / 40, 1080 / 40);
        unSelectedPopular = loadImage("src/main/resources/popular-not-selected.png");
        unSelectedPopular.resize(1920 / 40, 1080 / 40);
        selectedPrint = loadImage("src/main/resources/print-selected.png");
        selectedPrint.resize(1920 / 40, 1080 / 40);
        unSelectedPrint = loadImage("src/main/resources/print-not-selected.png");
        unSelectedPrint.resize(1920 / 40, 1080 / 40);
        selectedUpdate = loadImage("src/main/resources/update-selected.png");
        selectedUpdate.resize(1920 / 40, 1080 / 40);
        unSelectedUpdate = loadImage("src/main/resources/update-not-selected.png");
        unSelectedUpdate.resize(1920 / 40, 1080 / 40);

        currencyManager = new CurrencyManager(Database, Json);
        CurrencyConverterUI = new CurrencyConverterUI(this, currencyManager);

        PopularUI = new PopularUI(this, currencyManager);
        PrintSummaryUI = new PrintSummaryUI(this, currencyManager);
        UpdateUI = new UpdateUI(this, currencyManager);
        Login = new Login(this);
    }

    public void settings() {
        // setting the size of the app
        size(width, height);
    }

    @Override
    public void draw() {
        // drawing the background colours - light pink
        fill(255, 249, 254);
        rect(0, height/2, width, height/2);

        // drawing the background colours - dark pink
        fill(92,16,73);
        rect(0, 0, width, height/2);

        // drawing the logo
        image(logo, 25, -15);

//        // Shadow properties
//        fill(0, 0, 0, 50);
//        noStroke();
//
//        rect(rectX - shadowOffset, rectY - shadowOffset, rectW + 2 * shadowOffset, rectH + 2 * shadowOffset, cornerRadius + 5);
//
//        // Main rectangle properties
//        fill(255,249,254);
//        noStroke();
//
//        // Draw the main rounded rectangle
//        rect(rectX, rectY, rectW, rectH, cornerRadius);

        // Long rectangle header
        noStroke();
        fill(220, 202, 216);
        rect(rectX, rectY+30, rectW, 30);
        rect(rectX, rectY, rectW, 60, cornerRadius);

        // Hovering Tabs
        boolean isHoveringExchange = isMouseOverButton((int)rectX, (int)rectY, (int)rectW/4, 60);
        boolean isHoveringPopular = isMouseOverButton((int)rectX+(int)rectW/4, (int)rectY, (int)rectW/4, 60);
        boolean isHoveringPrint = isMouseOverButton((int)rectX+2*(int)rectW/4, (int)rectY, (int)rectW/4, 60);
        boolean isHoveringUpdate = isMouseOverButton((int)rectX+3*(int)rectW/4, (int)rectY, (int)rectW/4, 60);

        // Currency Converter Tab
        if (exchangeTabSelected) {
            if (CurrencyConverterUI != null) {
                CurrencyConverterUI.drawConverter();
                // Long rectangle header
                fill(220, 202, 216);
                rect(rectX, rectY+30, rectW, 30);
                rect(rectX, rectY, rectW, 60, cornerRadius);
            } else {
                System.out.println("Converter UI is null");
            }
            noStroke();
            fill(255, 249, 254);
            rect(rectX, rectY, rectW/4, 60, cornerRadius);
            rect(rectX, rectY+30, rectW/4, 30);

            // Converter text and image
            fill(222, 37, 176);
            image(selectedConvert, 100, 155);
            text("Convert", 145, 173);

        } else {
            fill(220, 202, 216);
            rect(rectX, rectY, rectW/4, 60, cornerRadius);
            rect(rectX, rectY+30, rectW/4, 30);

            // Converter text and image
            fill(113, 103, 111);
            image(unSelectedConvert, 100, 155);
            text("Convert", 145, 173);

            if (isHoveringExchange) {
                System.out.println("Mouse is hovering over 'exchange' tab");
                fill(255, 249, 254, 191);
            }
        }

        if (popularTabSelected) {
            if (PopularUI != null) {
                PopularUI.drawPopular();
            } else {
                System.out.println("Popular UI is null");
            }
            noStroke();
            // Long rectangle header
            fill(220, 202, 216);
            rect(rectX, rectY+30, rectW, 30);
            rect(rectX, rectY, rectW, 60, cornerRadius);

            // Most Popular tab
            fill(255, 249, 254);
            rect(rectX+rectW/4, rectY, rectW/4, 60, cornerRadius);
            rect(rectX+rectW/4, rectY+30, rectW/4, 30);

            fill(222, 37, 176);
            image(selectedPopular, 290, 153);
            text("Most Popular", 335, 173);

            fill(220, 202, 216);
            rect(rectX, rectY, rectW/4, 60, cornerRadius);
            rect(rectX, rectY+30, rectW/4, 30);

            textSize(16);
            // Converter tab
            fill(113, 103, 111);
            image(unSelectedConvert, 100, 155);
            text("Convert", 145, 173);

        } else {
            // Popular Table Tab
            fill(220, 202, 216);
            rect(rectX+rectW/4, rectY, rectW/4, 60, cornerRadius);
            rect(rectX+rectW/4, rectY+30, rectW/4, 30);

            // Converter text and image
            fill(113, 103, 111);
            image(unSelectedPopular, 290, 153);
            text("Most Popular", 335, 173);

            if (isHoveringPopular) {
                System.out.println("Mouse is hovering over 'popular' tab");
                fill(255, 249, 254, 191);
            }
        }

        if (printTabSelected) {
            if (PrintSummaryUI != null) {
                PrintSummaryUI.drawPrintSummaryUI();
            } else {
                System.out.println("Print Summary UI is null");
            }

            noStroke();

            // Long rectangle header
            fill(220, 202, 216);
            rect(rectX, rectY+30, rectW, 30);
            rect(rectX, rectY, rectW, 60, cornerRadius);

            textSize(16);

            fill(255, 249, 254);
            rect(rectX+2*rectW/4, rectY, rectW/4, 60, cornerRadius);
            rect(rectX+2*rectW/4, rectY+30, rectW/4, 30);

            fill(222, 37, 176);
            image(selectedPrint, 500, 155);
            text("Print Summary", 545, 173);

            fill(220, 202, 216);
            rect(rectX, rectY, rectW/4, 60, cornerRadius);
            rect(rectX, rectY+30, rectW/4, 30);

            // Converter text and image
            fill(113, 103, 111);
            image(unSelectedConvert, 100, 155);
            text("Convert", 145, 173);

            // Popular Table Tab
            fill(220, 202, 216);
            rect(rectX+rectW/4, rectY, rectW/4, 60, cornerRadius);
            rect(rectX+rectW/4, rectY+30, rectW/4, 30);

            // Converter text and image
            fill(113, 103, 111);
            image(unSelectedPopular, 290, 153);
            text("Most Popular", 335, 173);

        } else {
            noStroke();
            fill(220, 202, 216);
            rect(rectX+2*rectW/4, rectY, rectW/4, 60, cornerRadius);
            rect(rectX+2*rectW/4, rectY+30, rectW/4, 30);

            fill(113, 103, 111);
            image(unSelectedPrint, 500, 155);
            text("Print Summary", 545, 173);

            if (isHoveringPrint) {
                System.out.println("Mouse is hovering over 'print' tab");
                fill(255, 249, 254, 191);
            }
        }

        if (updateTabSelected) {
            if (UpdateUI != null) {
                UpdateUI.drawUpdate();
            } else {
                System.out.println("Update UI is null");
            }

            noStroke();
            textSize(16);
            fill(255, 249, 254);
            rect(rectX+3*rectW/4, rectY, rectW/4, 60, cornerRadius);
            rect(rectX+3*rectW/4, rectY+30, rectW/4, 30);

            fill(222, 37, 176);
            image(selectedUpdate, 725, 153);
            text("Rate Update", 770, 173);

            // Convert tab
            fill(220, 202, 216);
            rect(rectX, rectY, rectW/4, 60, cornerRadius);
            rect(rectX, rectY+30, rectW/4, 30);

            fill(113, 103, 111);
            image(unSelectedConvert, 100, 155);
            text("Convert", 145, 173);

            // Popular Table Tab
            fill(220, 202, 216);
            rect(rectX+rectW/4, rectY, rectW/4, 60, cornerRadius);
            rect(rectX+rectW/4, rectY+30, rectW/4, 30);

            fill(113, 103, 111);
            image(unSelectedPopular, 290, 153);
            text("Most Popular", 335, 173);

            // Print Summary tab
            fill(220, 202, 216);
            rect(rectX+2*rectW/4, rectY, rectW/4, 60, cornerRadius);
            rect(rectX+2*rectW/4, rectY+30, rectW/4, 30);

            fill(113, 103, 111);
            image(unSelectedPrint, 500, 155);
            text("Print Summary", 545, 173);

        } else {

            fill(220, 202, 216);
            rect(rectX+3*rectW/4, rectY, rectW/4, 60, cornerRadius);
            rect(rectX+3*rectW/4, rectY+30, rectW/4, 30);

            fill(113, 103, 111);
            image(unSelectedUpdate, 725, 153);
            text("Rate Update", 770, 173);

            if (isHoveringUpdate) {
                System.out.println("Mouse is hovering over 'update' tab");
                fill(255, 249, 254, 191);
            };
        }
        // Get mouse hover status
        boolean isHovering = isMouseOverButton(800, 15, 115, 40);

        // Change the color based on hover
        if (isHovering) {
            System.out.println("Mouse is hovering over 'Admin Login' button");
            fill(222, 37, 176, 200);
        } else {
            fill(222, 37, 176); // Default color
        }

        // Draw the button after setting the fill color
        noStroke();
        rect(800, 15, 115, 40, cornerRadius);

        // Draw the button text
        textSize(16);
        fill(255);
        text("Admin Login", 808, 40);

        if (Login.isLoginScreenVisible) {
            Login.drawLogin();
        }

        if (Login.Register.isRegisterScreenVisible) {
            Login.Register.drawRegister();
        }

        if (UpdateUI.addCurrency.isAddCurrency) {
            UpdateUI.addCurrency.drawAddCurrency();
        }

    }


    private boolean isMouseOverButton(int x, int y, int w, int h) {
        return (mouseX > x && mouseX < x + w &&
                mouseY > y && mouseY < y + h);
    }
    @Override
    public void mousePressed() {
        // Delegate the mouse press event to the UI handler
        if (exchangeTabSelected) {
            CurrencyConverterUI.mousePressed();
        }

        if (popularTabSelected) {
            PopularUI.mousePressed();
        }

        if (printTabSelected) {
            PrintSummaryUI.mousePressed();
        }

        if (updateTabSelected) {
            UpdateUI.mousePressed();
        }

        if (UpdateUI.addCurrency.isAddCurrency) {
            UpdateUI.addCurrency.mousePressed();
        }

        if (isMouseOverButton(800, 15, 115, 40)) {
            Login.isLoginScreenVisible = true;
        }

        if (Login.isLoginScreenVisible) {
            Login.mousePressed();
        }

        if (Login.Register.isRegisterScreenVisible) {
            Login.Register.mousePressed();
        }

        if (isMouseOverButton((int)rectX, (int)rectY, (int)rectW/4, 60)) {
            exchangeTabSelected = true;
            popularTabSelected = false;
            printTabSelected = false;
            updateTabSelected = false;
        }

        else if (isMouseOverButton((int)rectX+(int)rectW/4, (int)rectY, (int)rectW/4, 60)) {
            exchangeTabSelected = false;
            popularTabSelected = true;
            printTabSelected = false;
            updateTabSelected = false;
        }

        else if (isMouseOverButton((int)rectX+2*(int)rectW/4, (int)rectY, (int)rectW/4, 60)) {
            exchangeTabSelected = false;
            popularTabSelected = false;
            printTabSelected = true;
            updateTabSelected = false;
        }

        else if (isMouseOverButton((int)rectX+3*(int)rectW/4, (int)rectY, (int)rectW/4, 60)) {
            exchangeTabSelected = false;
            popularTabSelected = false;
            printTabSelected = false;
            updateTabSelected = true;
        }
    }

    @Override
    public void keyPressed() {
        // Delegate to the CurrencyConverterUI if the amount box is selected
        CurrencyConverterUI.keyPressed();
        PrintSummaryUI.keyPressed();
        UpdateUI.keyPressed();
    }

    public static void main(String[] args) {
        PApplet.main("CurrencyExchange.App");
    }
}