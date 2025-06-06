import java.util.* ;
import java.io.* ;
import java.io.*;

public class StudentResults {
    static Scanner input = new Scanner(System.in);
    static String studentId;

    public static void studentVerification(){
        System.out.print("Can you please enter your student id for verification here : ");
        studentId = input.next();

        idVerification(studentId);
    }

    public static void idVerification(String studentId){
        try {
            File file = new File("StudentDetails.txt");
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

                    if (id.equals(studentId)) {
                        System.out.println("id exits !\n");
                        found = true;




                        while (true) {

                            System.out.print("Now enter your student id password : ");
                            password = input.next();

                            if (passwordFromRegister.equals(password)) {
                                System.out.println("Login successfull !!");
                                showsTheResults();
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
        studentId = input.next();

        idVerification(studentId);
    }

    public static void showsTheResults(){
        System.out.println("Your Student Id : " + studentId);

        String mathematics = moduleMarks("Mathematics");
        String sdOne = moduleMarks("Sd-1");
        String csf = moduleMarks("CSF");
        String sdTwo = moduleMarks("Sd-2");
        String tcs = moduleMarks("TCS");
        String webDev = moduleMarks("WebDev");

        System.out.println("\nSEMESTER -1 RESULTS BELOW\n");
        System.out.println("mathematics marks is :" + mathematics);
        System.out.println("SD-1 marks is :" + sdOne);
        System.out.println("CSF marks is :" + csf);

        System.out.println("\nSEMESTER -2 RESULTS BELOW\n");
        System.out.println("SD-2 marks is :" + sdTwo);
        System.out.println("TCS marks is :" + tcs);
        System.out.println("WEBDEV marks is :" + webDev);





    }

    public static String moduleMarks(String moduleName){
        try {
            File file = new File(moduleName + ".txt");
            BufferedReader br = new BufferedReader(new FileReader(moduleName + ".txt"));

            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0] ;
                String marks = parts[1];
                if (parts.length >= 2 && id.equals(studentId)) {
                    return marks;
                }
            }



            br.close();
            return "Marks Yet to be Marked ! Please wait for a while..." ;


        } catch(IOException e){
            e.printStackTrace();
            return "Error reading the file.";
        }

    }

}
