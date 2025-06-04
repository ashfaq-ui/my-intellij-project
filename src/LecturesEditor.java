import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
                                System.out.println("Login successfull !!");
                                // call here the call or method to show the results
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
