import java.util.* ;
import javax.swing.*;




public class Main {
    static Scanner input = new Scanner(System.in) ;

    public static void main(String[] args) {
        mainOptions() ;

    }

    public static void mainOptions() {
        boolean loopNeed = true ;
        while(loopNeed) {
            System.out.println("Where would you like to go ?\n" +
                    "1 - Student Page\n" +
                    "2 - Lecture Page\n" +
                    "3 - Management Page\n" +
                    "4 - exit");
            try{
                System.out.print("\nAnswer here :");
                int optionSelectedMain = input.nextInt();
                System.out.println();

                switch (optionSelectedMain) {
                    case 1:
                        loopNeed = false ;
                        StudentResults studentResults = new StudentResults();
                        studentResults.studentVerification();
                        break;

                    case 2:
                        loopNeed = false ;
                        LecturesEditor lecturesEditor = new LecturesEditor();
                        lecturesEditor.studentVerification();
                        break;

                    case 3:
                        loopNeed = false ;
                        passwordForManagement();

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
    }

    // MANAGEMENT AREA!!!!!!
    public static void managementArea() {
        boolean loopNeed = true ;

        while(loopNeed) {
            System.out.println("1 - Id Verification Area\n" +
                    "2 - Marks Edit\n" +
                    "3 - Student Summary\n" +
                    "4 - Go back to main options\n" +
                    "5 - Exit");

            try{
                System.out.print("\nAnswer here :");
                int optionSelectedManage = input.nextInt();
                System.out.println();

                switch (optionSelectedManage) {
                    case 1:
                        loopNeed = false ;
                        idVerification();
                        break;

                    case 2:
                        loopNeed = false ;
                        StudentMarks marks = new StudentMarks();
                        marks.marksEditorStudent();
                        // call the function here
                        break;

                    case 3:
                        loopNeed = false ;
                        // call the function here
                        break;

                    case 4:
                        loopNeed = false ;
                        mainOptions();
                        break;

                    case 5:
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
    }

    public static void passwordForManagement(){
        JPasswordField passwordField = new JPasswordField();

        // Show a simple dialog with the password field
        JOptionPane.showMessageDialog(
                null,
                passwordField,
                "Enter the password (for management):",
                JOptionPane.PLAIN_MESSAGE
        );

        // Get the entered password
        String password = new String(passwordField.getPassword());

        if(password.equals("778899")){
            System.out.println("\nSuccessfully login!!\n");
            managementArea();
        }else{
            System.out.println("\npassword incorrect please try again !\n");
            mainOptions();

        }
    }

    // #1 ID VERIFICATION
    public static void idVerification(){
        boolean loopNeed = true ;
        while(loopNeed) {

            System.out.println("1 - Id Verification editor for Student\n" +
                    "2 - Id Verification editor for Lecture\n" +
                    "3 - back to previous page\n" +
                    "4 - go back to main page\n" +
                    "5 - Exit");

            try{
                System.out.print("\nAnswer here :");
                int optionSelectedVerification = input.nextInt();
                System.out.println();

                switch (optionSelectedVerification) {
                    case 1:
                        loopNeed = false;
                        StudentArea callingStudentArea = new StudentArea();
                        callingStudentArea.idVerificationForStudent();
                        break;

                    case 2:
                        loopNeed = false;
                        LectureArea callingLectureArea = new LectureArea();
                        callingLectureArea.idVerificationForLecture();
                        break;

                    case 3:
                        loopNeed = false;
                        managementArea();
                        break;

                    case 4:
                        loopNeed = false;
                        mainOptions();
                        break;

                    case 5:
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




}

