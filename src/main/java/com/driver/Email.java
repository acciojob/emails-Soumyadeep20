package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        if (!oldPassword.equals(this.password)) {
           // System.out.println("Password change failed: Old password does not match.");
            return;
        }

        // Check if the new password meets the specified conditions
        if (isValidPassword(newPassword)) {
            // Change the password if conditions are met
            password = newPassword;
            //System.out.println("Password changed successfully.");
        }
        return;

    }
    private boolean isValidPassword(String newPassword) {
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        // Check password requirements
        return newPassword.length() >= 8 &&
                newPassword.matches(".*[A-Z].*") &&
                newPassword.matches(".*[a-z].*") &&
                newPassword.matches(".*\\d.*") &&
                newPassword.matches(".*[^A-Za-z0-9].*");
    }
}
