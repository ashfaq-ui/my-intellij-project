import java.util.* ;
import java.io.* ;

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
                System.out.println("\nID is not found or id entered wrong Can you please try again!\n");
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
}
