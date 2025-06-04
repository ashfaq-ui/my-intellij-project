import java.io.*;
import java.util.InputMismatchException;
import java.awt.Desktop;
import java.util.Scanner;

public class LectureArea {
    static Scanner input = new Scanner(System.in) ;

    public static void idVerificationForLecture() {
        boolean loopNeed = true ;
        while(loopNeed) {
            System.out.println("1 - Add new Lecture\n" +
                    "2 - Remove a Lecture\n" +
                    "3 - check the password for Lecture\n" +
                    "4 - change the password\n" +
                    "5 - full Lecture id and password.\n" +
                    "6 - go back to previous page\n" +
                    "7 - go back to main page\n" +
                    "8 - Exit\n");

            try{
                System.out.print("\nAnswer here :");
                int optionIdVerificationForLecture = input.nextInt();
                System.out.println();

                switch (optionIdVerificationForLecture) {
                    case 1:
                        loopNeed = false;
                        addNewLecture();
                        idVerificationForLecture();
                        break;

                    case 2:
                        loopNeed = false;
                        removeLecture();
                        idVerificationForLecture();
                        break;

                    case 3:
                        loopNeed = false;
                        checkPassword();
                        idVerificationForLecture();
                        break;

                    case 4:
                        loopNeed = false;
                        changePassword();
                        idVerificationForLecture();
                        break;

                    case 5:
                        loopNeed = false;
                        popUpTheTextFileForLectureDeatails();
                        idVerificationForLecture();
                        break;

                    case 6:
                        loopNeed = false;
                        Main main = new Main();
                        main.idVerification();
                        break;

                    case 7:
                        loopNeed = false;
                        Main main2 = new Main();
                        main2.mainOptions();
                        break;


                    case 8:
                        loopNeed = false;
                        // Exit
                        break;


                    default:
                        System.out.println("\nOption selected is not correct !\n");
                }


            }
            catch (InputMismatchException e){
                System.out.println("\nInvalid input! Please enter a number.\n");
                input.nextLine(); // clear the invalid input
            }

        }

    }

    public static void addNewLecture() {

        String ask_password, ask_id ;
        boolean idExists = false ;
        boolean confirm_credentials = false;

        while (true) {
            System.out.print("Enter New Lecture ID :");
            ask_id = input.next();

            idExists = false ;
            try {

                File file = new File("LectureDetails.txt");
                if(!file.exists()){
                    file.createNewFile();
                }

                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);

                String line;

                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        String id = parts[0].trim();

                        if (id.equals(ask_id)) {
                            idExists = true;
                            break;
                        }
                    }
                }

                br.close();
            } catch (IOException e){
                e.printStackTrace();
            }

            if (idExists) {
                System.out.println("\nID is already registered!\nPlease try again.\n");
            } else {
                System.out.print("Enter A password for id :");
                ask_password = input.next();

                System.out.println("\nCan you confirm this is the credentials?");
                System.out.println("ID: " + ask_id + ", Password: " + ask_password);
                System.out.println("\nif it is confirm enter - yes \n" +
                        "if it is not then  enter - no");

                String confirm_credentials_forbool = input.next();

                confirm_credentials_forbool = confirm_credentials_forbool.toLowerCase();

                if (confirm_credentials_forbool.equals("yes")) {
                    confirm_credentials = true;


                    try {
                        FileWriter writer = new FileWriter("LectureDetails.txt", true);
                        writer.write(ask_id + "," + ask_password + "\n");
                        writer.close();
                        System.out.println("\nCredentials saved successfully.\n");
                    } catch (IOException e) {
                        System.out.println("An error occurred while saving credentials.");
                        e.printStackTrace();
                    }
                    break;
                } else {
                    System.out.println("\nLet's try again.\n");
                }
            }










        }


    }

    public static void removeLecture() {
        try {
            File inputFile = new File("LectureDetails.txt");
            File tempFile = new File("temp.txt");

            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            System.out.println("Can you enter the Lecture id? (for remove)");
            String removeId = input.next();

            String line;
            boolean idExists = false;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                String id = parts[0].trim();

                if (id.equals(removeId)){
                    if (line.contains(removeId)) {
                        idExists = true ;
                        System.out.println("\nLecture id removed successfully. ID: " + removeId + "\n");
                        continue; // skip this line
                    }
                }

                bw.write(line);
                bw.newLine();
            }

            br.close();
            bw.close();

            if(!idExists){
                System.out.println("\nid is not found !\n");
            }

            // Delete the original file
            if (inputFile.delete()) {
                // Rename temp file to original file name
                tempFile.renameTo(inputFile);

            } else {
                System.out.println("Failed to delete original file.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkPassword(){
        try{
            File file = new File("LectureDetails.txt");
            BufferedReader br = new BufferedReader(new FileReader("LectureDetails.txt"));

            System.out.print("Can you enter the Lecture id? (for get the password): ");
            String checkIdsPassword = input.next();

            String line;
            boolean found = false ;

            while((line = br.readLine()) != null) {
                if (line.contains(checkIdsPassword)) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        System.out.println("\n"+ "password for " + checkIdsPassword + " is : " + parts[1] + "\n");
                        found = true ;
                    }
                }
            }
            br.close();

            if(!found){
                System.out.println("\nStudent id wrong or Not registered\n");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void changePassword() {


        Scanner input = new Scanner(System.in);
        String ask_id, ask_oldPassword, newPassword;
        boolean idExists = false;

        System.out.print("Enter Lecture ID (for Change the Password): ");
        ask_id = input.next();

        // First check if ID exists and read all content
        File inputFile = new File("LectureDetails.txt");
        File tempFile = new File("temp.txt");

        if (!inputFile.exists()) {
            try {
                inputFile.createNewFile();  // This will create an empty file
            } catch (IOException e) {
                System.out.println("Error creating file.");
                e.printStackTrace();
                return;
            }
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");

                if (parts.length == 2) {

                    String id = parts[0].trim();
                    String oldPassword = parts[1].trim();

                    if (id.equals(ask_id)) {
                        // If ID exists, ask for new marks
                        System.out.print("Enter old password here: ");
                        ask_oldPassword = input.next();
                        idExists = true;

                        if (oldPassword.equals(ask_oldPassword)) {
                            System.out.print("Can you enter a new password here: ");
                            newPassword = input.next();

                            // Confirm
                            System.out.println("\nCan you confirm this is the new Marks?");
                            System.out.println("ID: " + ask_id + ", " + "NEW PASSWORD: " + newPassword);
                            System.out.println("\nEnter 'yes' to confirm or 'no' to cancel.");
                            String confirm = input.next().toLowerCase();

                            if (confirm.equals("yes")) {
                                bw.write(id + "," + newPassword);
                                bw.newLine();
                                System.out.println("\nPassword successfully changed !\n");
                            } else {
                                bw.write(id + "," + oldPassword);  // keep old mark
                                bw.newLine();
                                System.out.println("\nUpdate canceled.\n");
                            }
                        } else {
                            System.out.println("\nEntered old password is wrong !! (if you need to know about your password contact the management)\n");
                            bw.write(id + "," + oldPassword);
                            bw.newLine();

                        }

                    } else {
                        // Copy other records as is
                        bw.write(id + "," + oldPassword);
                        bw.newLine();
                    }
                }
            }

            br.close();
            bw.close();

            // Replace original file with updated temp file
            if (inputFile.delete()) {
                tempFile.renameTo(inputFile);
            } else {
                System.out.println("Could not update original file.");
            }

            if (!idExists) {
                System.out.println("\nEntered id is wrong or id is not registered !\n");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void popUpTheTextFileForLectureDeatails(){
        File file = new File("LectureDetails.txt");

        if (Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try{
                if(file.exists()){
                    desktop.open(file);
                }else{
                    System.out.println("file does not exist.");
                }
            } catch(IOException e){
                e.printStackTrace();
            }
        }else{
            System.out.println("Desktop is not supported.");
        }

    }
}
