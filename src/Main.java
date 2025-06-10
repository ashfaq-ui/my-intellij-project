import java.util.* ;
import javax.swing.*;
import java.io.* ;


public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        mainOptions();

    }

    public static void mainOptions() {
        boolean loopNeed = true;
        while (loopNeed) {
            System.out.println("Where would you like to go ?\n" +
                    "1 - Student Page\n" +
                    "2 - Lecture Page\n" +
                    "3 - Management Page\n" +
                    "4 - exit");
            try {
                System.out.print("\nAnswer here :");
                int optionSelectedMain = input.nextInt();
                System.out.println();

                switch (optionSelectedMain) {
                    case 1:
                        loopNeed = false;
                        StudentResults studentResults = new StudentResults();
                        studentResults.studentVerification();
                        break;

                    case 2:
                        loopNeed = false;
                        LecturesEditor lecturesEditor = new LecturesEditor();
                        lecturesEditor.studentVerification();
                        break;

                    case 3:
                        loopNeed = false;
                        passwordForManagement();

                        break;

                    case 4:
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

    // MANAGEMENT AREA!!!!!!
    public static void managementArea() {
        boolean loopNeed = true;

        while (loopNeed) {
            System.out.println("1 - Id Verification Area\n" +
                    "2 - Marks Edit\n" +
                    "3 - Student Summary\n" +
                    "4 - Go back to main options\n" +
                    "5 - Exit");

            try {
                System.out.print("\nAnswer here :");
                int optionSelectedManage = input.nextInt();
                System.out.println();

                switch (optionSelectedManage) {
                    case 1:
                        loopNeed = false;
                        idVerification();
                        break;

                    case 2:
                        loopNeed = false;
                        StudentMarks marks = new StudentMarks();
                        marks.marksEditorStudent();
                        // call the function here
                        break;

                    case 3:
                        loopNeed = false;
                        summaryView();
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
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input! Please enter a number.\n");
                input.nextLine(); // clear the invalid input
            }

        }
    }

    public static void passwordForManagement() {
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

        if (password.equals("778899")) {
            System.out.println("\nSuccessfully login!!\n");
            managementArea();
        } else {
            System.out.println("\npassword incorrect please try again !\n");
            mainOptions();

        }
    }



    // SUMMARY VIEW !!!
    public static void summaryView() {
        boolean loopNeed = true;
        while (loopNeed) {
            System.out.println("1 - Mathematics Student Summary\n" +
                    "2 - Software Development i Student Summary\n" +
                    "3 - Computer Systems Fundamentals Student Summary\n" +
                    "4 - Software Development ii Student Summary\n" +
                    "5 - Trends in Computer Science Student Summary\n" +
                    "6 - Web Design and Development Student Summary\n" +
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
                        summaryOfTheModule("Mathematics");
                        break;

                    case 2:
                        loopNeed = false;
                        summaryOfTheModule("Sd-1");
                        break;

                    case 3:
                        loopNeed = false;
                        summaryOfTheModule("CSF");
                        break;

                    case 4:
                        loopNeed = false;
                        summaryOfTheModule("Sd-2");
                        break;

                    case 5:
                        loopNeed = false;
                        summaryOfTheModule("TCS");
                        break;

                    case 6:
                        loopNeed = false;
                        summaryOfTheModule("WebDev");
                        break;

                    case 7:
                        loopNeed = false;
                        managementArea();
                        break;

                    case 8:
                        loopNeed = false;
                        Main main2 = new Main();
                        mainOptions();
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

    public static void summaryOfTheModule (String moduleName){
        try {
            File file = new File(moduleName + ".txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            int aPlus = 0;
            int a = 0;
            int bPlus = 0;
            int b = 0;
            int cPlus = 0;
            int c = 0;
            int fail = 0;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int marks = Integer.parseInt(parts[1]);

                if (marks >= 90) {
                    aPlus += 1;
                }else if(marks >= 70) {
                    a += 1;
                }else if(marks >= 65) {
                    bPlus += 1;
                }else if(marks >= 60) {
                    b += 1;
                }else if(marks >= 50) {
                    cPlus += 1;
                }else if(marks >= 40) {
                    c += 1;
                } else{
                    fail += 1;
                }

            }
            SimpleBarChart chart = new SimpleBarChart(aPlus, a, bPlus, b, cPlus, c, fail, moduleName);
            chart.setVisible(true);
            summaryView();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    // #1 ID VERIFICATION
    public static void idVerification () {
        boolean loopNeed = true;
        while (loopNeed) {

            System.out.println("1 - Id Verification editor for Student\n" +
                    "2 - Id Verification editor for Lecture\n" +
                    "3 - back to previous page\n" +
                    "4 - go back to main page\n" +
                    "5 - Exit");

            try {
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
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid input! Please enter a number.\n");
                input.nextLine(); // clear the invalid input
            }

        }
    }



}






