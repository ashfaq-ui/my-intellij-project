import java.awt.*;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
// 4COSC007C.1 - Mathematics for computing
// 4COSC006C.1 - Software Devlopment i
// 4COSC009C.1 - Computer Systems Fundamentals
// 4COSC011C.2 - Web Design and Development
// 4COSC010C.2 - Software Development ii
// 4COSC008C.2 - Trends in Computer Science
public class ModuleArea {
    static Scanner input = new Scanner(System.in) ;

    public static void moduleEdit(){
        boolean loopNeed = true ;
        while(loopNeed) {

            System.out.println("1 - Add New Module\n" +
                    "2 - Remove Module\n" +
                    "3 - Full module Name and id\n" +
                    "4 - back to previous page\n" +
                    "5 - go back to main page\n" +
                    "6 - Exit");

            try{
                System.out.print("\nAnswer here :");
                int optionSelectedVerification = input.nextInt();
                System.out.println();

                switch (optionSelectedVerification) {
                    case 1:
                        loopNeed = false;
                        addModule();
                        moduleEdit();
                        break;

                    case 2:
                        loopNeed = false;
                        removeModule();
                        moduleEdit();
                        break;

                    case 3:
                        loopNeed = false;
                        popUpTheTextFileForModuleDeatails();
                        moduleEdit();
                        break;

                    case 4:
                        loopNeed = false;
                        Main main = new Main();
                        main.managementArea();
                        break;

                    case 5:
                        loopNeed = false;
                        Main main2 = new Main();
                        main2.mainOptions();
                        break;
                    case 6:
                        loopNeed = false;
                        //Exit
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

    public static void addModule(){
        String askModuleName ;
        String askModuleId ;
        boolean confirm_credentials = false ;
        while(true) {
            System.out.print("Enter New Module Name :");
            askModuleName = input.next();

            System.out.print("Enter New Module ID :");
            askModuleId = input.next();

            /*StudentVerification stuVer = new StudentVerification(ask_id,ask_password);
            students.add(stuVer);
            stuVer.display();
            */

            System.out.println("\nCan you confirm this?");
            System.out.println("Module Name: " + askModuleName + ", Module ID: " + askModuleId);
            System.out.println("\nif it is confirm enter - yes \n" +
                    "if it is not then  enter - no");

            String confirm_credentials_forbool = input.next();

            confirm_credentials_forbool = confirm_credentials_forbool.toLowerCase();

            if (confirm_credentials_forbool.equals("yes")) {
                confirm_credentials = true;
                break ;
            }

        }
        if (confirm_credentials) {
            try {
                File file = new File("ModuleDetails.txt");
                FileWriter writer = new FileWriter(file, true); // append mode
                writer.write(askModuleName + "," + askModuleId + "\n");
                writer.close();
                System.out.println("\nsaved successfully.\n");

            } catch (IOException e) {
                System.out.println("An error occurred while saving.");
                e.printStackTrace();
            }
        }
    }

    public static void removeModule() {
        try {
            File inputFile = new File("ModuleDetails.txt");
            File tempFile = new File("temp.txt");

            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            System.out.println("Can you enter the Module id? (for remove)");
            String removeModule = input.next();

            boolean found = false ;

            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(removeModule)) {
                    found = true ;
                    continue; // skip this line
                }
                bw.write(line);
                bw.newLine();
            }

            br.close();
            bw.close();

            if(found) {
                // Delete the original file
                if (inputFile.delete()) {
                    // Rename temp file to original file name
                    tempFile.renameTo(inputFile);
                    System.out.println("\nModule removed successfully. Module id: " + removeModule + "\n");
                } else {
                    System.out.println("Failed to delete original file.");
                }
            }else{
                System.out.println("\nModule id entered wrong or Not registered\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void popUpTheTextFileForModuleDeatails(){
        File file = new File("ModuleDetails.txt");

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
