package CurrencyExchange;

import processing.core.PApplet;

public class Register {
    PApplet parent;
    boolean isRegisterScreenVisible = false;
    boolean employeeIdSelected = false;
    boolean usernameSelected = false;
    boolean passwordSelected = false;
    float shadowOffset = 8;
    Login login;
    public Register(PApplet parent, Login login) {

        this.parent = parent;
        this.login = login;
    }

    public void drawRegister() {

        if (!isRegisterScreenVisible) return;

        // Background Overlay
        parent.fill(0, 0, 0, 150);
        parent.rect(0, 0, parent.width*2, parent.height);

        // Shadow properties
        parent.fill(0, 0, 0, 50);
        parent.noStroke();
        parent.rect(parent.width / 2 - 200 - shadowOffset, parent.height / 2 - 200 - shadowOffset, 400 + 2 * shadowOffset, 400 + 2 * shadowOffset, 15);


        // White Login Box
        parent.fill(255,249,254);
        parent.stroke(200);
        parent.rect(parent.width / 2 - 200, parent.height / 2 - 200, 400, 400, 10);

        // Title Text "Admin Register"
        parent.fill(0);
        parent.textSize(24);
        parent.text("Admin Register", 395, 140);

        // Employee ID Field
        if (employeeIdSelected) {
            parent.fill(220, 202, 216);
        } else {
            parent.noFill();
        }
        parent.stroke(84, 84, 84);
        parent.rect(parent.width / 2 - 120, parent.height / 2 - 90, 240, 40, 5);
        parent.fill(84, 84, 84);
        parent.textSize(16);
        parent.text("Employee ID", 370, 205);

        // Username Field
        if (usernameSelected) {
            parent.fill(220, 202, 216);
        } else {
            parent.noFill();
        }
        parent.stroke(84, 84, 84);
        parent.rect(parent.width / 2 - 120, parent.height / 2 - 10, 240, 40, 5);
        parent.fill(84, 84, 84);
        parent.textSize(16);
        parent.text("Username", 370, 285);

        // Password Field
        if (passwordSelected) {
            parent.fill(220, 202, 216);
        } else {
            parent.noFill();
        }
        parent.stroke(84, 84, 84);
        parent.rect(parent.width / 2 - 120, parent.height / 2 + 70, 240, 40, 5);
        parent.fill(84, 84, 84);
        parent.textSize(16);
        parent.text("Password", 370, 365);

        // Register Button
        // Draw the button after setting the fill color
        boolean isHover = isMouseOverButton(560, 360, 100, 40);
        if (isHover) {
            parent.fill(222, 37, 176, 200);
        } else {
            parent.fill(222, 37, 176);
        }
        parent.noStroke();
        parent.rect(560, 410, 100, 40, 10);
        parent.fill(255);
        parent.textSize(16);
        parent.text("Register", 577, 435);

        boolean isHovering = isMouseOverButton(300, 425, (int)parent.textWidth("Have An Account?"), 10);
        if (isHovering) {
            parent.fill(222, 37, 176);
        } else {
            parent.fill(0);
        }

        parent.text("Have An Account?", 300, 435);

    }

    private boolean isMouseOverButton(int x, int y, int w, int h) {
        return (parent.mouseX > x && parent.mouseX < x + w &&
                parent.mouseY > y && parent.mouseY < y + h);
    }

    public void mousePressed() {
        if (isMouseOverButton(300, 425, (int)parent.textWidth("Have An Account?"), 10)) {
            isRegisterScreenVisible = false;
            login.isLoginScreenVisible = true;
        }

        if (isMouseOverButton(parent.width / 2 - 120, parent.height / 2 - 90, 240, 40)) {
            employeeIdSelected = true;
            usernameSelected = false;
            passwordSelected = false;
        }

        if (isMouseOverButton(parent.width / 2 - 120, parent.height / 2 - 10, 240, 40)) {
            employeeIdSelected = false;
            usernameSelected = true;
            passwordSelected = false;
        }

        if (isMouseOverButton(parent.width / 2 - 120, parent.height / 2 + 70, 240, 40)) {
            employeeIdSelected = false;
            usernameSelected = false;
            passwordSelected = true;
        }
    }
}
