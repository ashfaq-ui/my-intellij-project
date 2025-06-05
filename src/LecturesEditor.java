import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LecturesEditor {
    static Scanner input = new Scanner(System.in);
    static String lectureId;

    public static void studentVerification(){
        System.out.print("Can you please enter your Lecture id for verification here : ");
        lectureId = input.next();

        idVerification(lectureId);
    }

    public static void idVerification(String lectureId){
        try {
            File file = new File("LectureDetails.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;
            String password ;
            boolean found = false;

            while((line = br.readLine()) != null ){
                String[] parts = line.split(",");
                if(parts.length == 2) {
                    String id = parts[0].trim();
                    String passwordFromRegister = parts[1].trim();

                    if (id.equals(lectureId)) {
                        System.out.println("id exits !\n");
                        found = true;




                        while (true) {

                            System.out.print("Now enter your Lecture id password : ");
                            password = input.next();

                            if (passwordFromRegister.equals(password)) {
                                System.out.println("Login successfull !!\n");

                                boolean loopNeed = true ;

                                while(loopNeed) {
                                    System.out.println("1 - Marks Editor\n" +
                                            "2 - Student Summary\n" +
                                            "3 - Go back to main options\n" +
                                            "4 - Exit");

                                    try{
                                        System.out.print("\nAnswer here :");
                                        int optionSelectedManage = input.nextInt();
                                        System.out.println();

                                        switch (optionSelectedManage) {
                                            case 1:
                                                loopNeed = false ;
                                                StudentMarks marks = new StudentMarks();
                                                marks.marksEditorStudent();
                                                break;

                                            case 2:
                                                loopNeed = false ;
                                                // call the function here
                                                break;

                                            case 3:
                                                loopNeed = false ;
                                                Main main = new Main();
                                                main.mainOptions();
                                                break;

                                            case 4:
                                                loopNeed = false ;
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

                                break;

                            } else {
                                System.out.println("Incorrect password please try again ! \n");
                            }

                        }
                    }
                }
            }

            if(!found){
                System.out.println("\nID is not found or ID entered wrong Can you please try again!\n");
                studentVerificationTwo();
            }
            br.close();
        } catch(IOException e){
            e.printStackTrace();
        }

    }

    public static void studentVerificationTwo(){
        System.out.print("Enter your student Id again : ");
        lectureId = input.next();

        idVerification(lectureId);
    }
}
