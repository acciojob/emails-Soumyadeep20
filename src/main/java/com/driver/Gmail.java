package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    private List<mail> inbox;
    private List<mail> trash;

    public Gmail(String emailId, int inboxCapacity) {
      super(emailId);
      this.inboxCapacity=inboxCapacity;
        this.inbox = new ArrayList<>();
        this.trash = new ArrayList<>();

    }


    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        mail newMail = new mail(date, sender, message);
        if(inbox.size()>=inboxCapacity){
           moveOldestMailToTrash();
        }
        inbox.add(newMail);
    }
    private void moveOldestMailToTrash() {
        // Find the oldest mail in the inbox
        mail oldestMail = inbox.get(0);
        for (mail mail1 : inbox) {
            if (mail1.getDate().before(oldestMail.getDate())) {
                oldestMail = mail1;
            }
        }

        // Move the oldest mail to trash
        inbox.remove(oldestMail);
        trash.add(oldestMail);

    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        mail mailToDelete = null;
        for (mail mail1 : inbox) {
            if (mail1.getMessage().equals(message)) {
                mailToDelete = mail1;
                break;
            }
        }

        // If the mail is found, move it to trash and remove it from the inbox
        if (mailToDelete != null) {
            trash.add(mailToDelete);
            inbox.remove(mailToDelete);
        }


    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inbox.size()>0){
            return inbox.get(inbox.size() - 1).getMessage();
        }
       return null;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox.size()>0){
            return inbox.get(0).getMessage();
        }
       return null;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;

        for (mail mail1 : inbox) {
            Date mailDate = mail1.getDate();

            // Check if the mail date is between the given start and end dates (inclusive)
            if (!mailDate.before(start) && !mailDate.after(end)) {
                count++;
            }
        }

       // System.out.println("Number of mails between " + start + " and " + end + ": " + count);
        return count;

    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();

    }

    public void emptyTrash(){
        // clear all mails in the trash
       trash.clear();

    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
     class mail{
        private Date date;
        private String sender;
        private String message;

        public mail(Date date, String sender, String message) {
            this.date = date;
            this.sender = sender;
            this.message = message;
        }

        public Date getDate(){
            return this.date;
        }
        public String getMessage(){
            return this.message;
        }

         public String getSender() {
             return sender;
         }
     }

}
