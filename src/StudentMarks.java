import java.awt.*;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class StudentMarks {

    static Scanner input = new Scanner(System.in) ;
    public static void marksEditorStudent() {
        boolean loopNeed = true;
        while (loopNeed) {
            System.out.println("1 - Mathematics Marks editor\n" +
                    "2 - Software Development i Marks editor\n" +
                    "3 - Computer Systems Fundamentals Marks editor\n" +
                    "4 - Software Development ii Marks editor\n" +
                    "5 - Trends in Computer Science Marks editor\n" +
                    "6 - Web Design and Development Marks editor\n" +
                    "7 - go back to previous page\n" +
                    "8 - go back to main page\n" +
                    "9 - Exit\n");

            try {
                System.out.print("\nAnswer here :");
                int optionIdVerificationForStudent = input.nextInt();
                System.out.println();

                switch (optionIdVerificationForStudent) {
                    case 1:
                        loopNeed = false;
                        ModuleMarksEditor.marksEditor("Mathematics");
                        break;

                    case 2:
                        loopNeed = false;
                        ModuleMarksEditor.marksEditor("Sd-1");
                        break;

                    case 3:
                        loopNeed = false;
                        ModuleMarksEditor.marksEditor("CSF");
                        break;

                    case 4:
                        loopNeed = false;
                        ModuleMarksEditor.marksEditor("Sd-2");
                        break;

                    case 5:
                        loopNeed = false;
                        ModuleMarksEditor.marksEditor("TCS");
                        break;

                    case 6:
                        loopNeed = false;
                        ModuleMarksEditor.marksEditor("WebDev");
                        break;

                    case 7:
                        loopNeed = false;
                        Main main = new Main();
                        main.managementArea();
                        break;

                    case 8:
                        loopNeed = false;
                        Main main2 = new Main();
                        main2.mainOptions();
                        break;


                    case 9:
                        loopNeed = false;
                        // Exit
                        break;


                    default:
                        System.out.println("\nOption selected is not correct !\n");
                }


            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input! Please enter a number.\n");
                input.nextLine(); // clear the invalid input
            }

        }

    }
}


// EDITOR FOR MODULE MARKS
class ModuleMarksEditor{

    static Scanner input = new Scanner(System.in) ;

    public static void marksEditor(String moduleName) {
        boolean loopNeed = true;
        while (loopNeed) {
            System.out.println("1 - Add marks for Student id\n" +
                    "2 - Remove marks for Student id\n" +
                    "3 - check Marks Student\n" +
                    "4 - full Student id and Marks.\n" +
                    "5 - go back to previous page\n" +
                    "6 - go back to main page\n" +
                    "7 - Exit\n");

            try {
                System.out.print("\nAnswer here :");
                int optionIdVerificationForStudent = input.nextInt();
                System.out.println();

                switch (optionIdVerificationForStudent) {
                    case 1:
                        loopNeed = false;
                        addStudentMark(moduleName);
                        marksEditor(moduleName);
                        break;

                    case 2:
                        loopNeed = false;
                        removeStudentMarks(moduleName);
                        marksEditor(moduleName);
                        break;

                    case 3:
                        loopNeed = false;
                        checkMarks(moduleName);
                        marksEditor(moduleName);
                        break;

                    case 4:
                        loopNeed = false;
                        popUpTheTextFileForStudentMarks(moduleName);
                        marksEditor(moduleName);
                        break;

                    case 5:
                        loopNeed = false;
                        StudentMarks.marksEditorStudent();
                        break;

                    case 6:
                        loopNeed = false;
                        Main main2 = new Main();
                        main2.mainOptions();
                        break;


                    case 7:
                        loopNeed = false;
                        // Exit
                        break;


                    default:
                        System.out.println("\nOption selected is not correct !\n");
                }


            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input! Please enter a number.\n");
                input.nextLine(); // clear the invalid input
            }

        }

    }

    public static void addStudentMark(String moduleName) {
        Scanner input = new Scanner(System.in);
        String ask_id, ask_Marks;
        boolean idExists = false;
        boolean confirmCredentials = false;

        System.out.print("Enter Student ID for Marks Entry: ");
        ask_id = input.next();

        // First check if ID exists and read all content
        File inputFile = new File(moduleName + ".txt");
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
                    String mark = parts[1].trim();

                    if (id.equals(ask_id)) {
                        // If ID exists, ask for new marks
                        System.out.print("Enter " + moduleName + "Marks here: ");
                        ask_Marks = input.next();
                        idExists = true;

                        // Confirm
                        System.out.println("\nCan you confirm this is the new Marks?");
                        System.out.println("ID: " + ask_id + ", " + moduleName +" marks: " + ask_Marks);
                        System.out.println("\nEnter 'yes' to confirm or 'no' to cancel.");
                        String confirm = input.next().toLowerCase();

                        if (confirm.equals("yes")) {
                            confirmCredentials = true;
                            bw.write(id + "," + ask_Marks);
                            bw.newLine();
                        } else {
                            bw.write(id + "," + mark);  // keep old mark
                            bw.newLine();
                            System.out.println("\nUpdate canceled.\n");
                        }
                    } else {
                        // Copy other records as is
                        bw.write(id + "," + mark);
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

        } catch (IOException e) {
            e.printStackTrace();
        }

        // If ID does not exist, add new entry
        if (!idExists) {
            System.out.print("Enter "+ moduleName +" Marks here: ");
            ask_Marks = input.next();

            System.out.println("\nCan you confirm this is correct?");
            System.out.println("ID: " + ask_id + ", "+ moduleName + " marks: " + ask_Marks);
            System.out.println("Enter 'yes' to confirm or 'no' to cancel.");
            String confirm = input.next().toLowerCase();

            if (confirm.equals("yes")) {
                try {
                    FileWriter writer = new FileWriter(inputFile, true); // Append mode
                    writer.write(ask_id + "," + ask_Marks + "\n");
                    writer.close();
                    System.out.println("\nNew student record saved successfully.\n");
                } catch (IOException e) {
                    System.out.println("An error occurred while saving the new record.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("\nRecord not saved.\n");
            }
        } else if (confirmCredentials) {
            System.out.println("\nStudent record updated successfully.\n");
        }
    }

    public static void removeStudentMarks(String moduleName) {
        try {
            File inputFile = new File(moduleName + ".txt");
            File tempFile = new File("temp.txt");

            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            System.out.println("Can you enter the Student id? (for remove the marks)");
            String removeMarks = input.next();

            String line;
            boolean found = false ;
            while ((line = br.readLine()) != null) {
                // Split line by comma
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String id = parts[0].trim();
                    String mark = parts[1].trim();
                    if (id.equals(removeMarks)) {
                        mark = "NotMarkedYet";
                        found = true ;
                    }
                    bw.write(id + "," + mark);
                    bw.newLine();
                }
            }

            br.close();
            bw.close();
            if(found){
                // Delete the original file
                if (inputFile.delete()) {
                    // Rename temp file to original file name
                    tempFile.renameTo(inputFile);
                    System.out.println("\nStudent id : " + removeMarks + " " + moduleName + " Marks removed successfully.\n");
                } else {
                    System.out.println("Failed to delete original file.");
                }
            }else{
                System.out.println("\nId is wrong or id is not registered\n");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkMarks(String moduleName){
        try{
            File file = new File(moduleName + ".txt");
            BufferedReader br = new BufferedReader(new FileReader(moduleName + ".txt"));

            System.out.print("Can you enter the Student id? (for get the Marks): ");
            String checkIdsMarks = input.next();

            String line;
            boolean found = false;

            while((line = br.readLine()) != null) {
                if (line.contains(checkIdsMarks)) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        System.out.println("\n" + moduleName + " marks for " + checkIdsMarks + " is : " + parts[1] + "\n");
                        found = true;
                        break;
                    }
                }
            }
            br.close();

            if(!found){
                System.out.println("\nId entered wrong or Id is not registered\n");
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void popUpTheTextFileForStudentMarks(String moduleName){
        File file = new File(moduleName + ".txt");

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

// say hello to git hub



