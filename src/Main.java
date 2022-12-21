import Domain.ManageTrainingPackageHandler;
import Domain.TrainingPackage;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String command = chooseCommand(scanner);
            if ("1".equals(command)) {
                ManageTrainingPackageHandler trainer = new ManageTrainingPackageHandler();
                String backOrExitCommand = managePackagesMenu(scanner, trainer);
                isExit = "6".equals(backOrExitCommand);
            } else if ("2".equals(command)) {
                System.out.println("Please select 1, this part is not ready :) ");
            }
        }
        System.out.println("Goodbye!");
    }

    public static void welcomeMessage() {
        System.out.println("     GYM      ");
        System.out.println("    STUDIO    ");
        System.out.println("  APPLICATION ");
        System.out.println("   WELCOME  ");
    }

    public static String chooseCommand(Scanner scanner) {

        welcomeMessage();

        boolean validCommand = false;
        String command = null;
        while (!validCommand) {
            System.out.println("Enter the number of command you want to perform: ");
            System.out.println("1 - Manage Memberships");
            System.out.println("2 - Not Ready, please don't choose!");
            command = scanner.nextLine();
            if (null == command) {
                System.out.println("Invalid command!");
            } else switch (command) {
                case "1":
                    validCommand = true;
                    break;
                case "2":
                    validCommand = true;
                    break;
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
        return command;
    }

    public static String managePackagesMenu(Scanner scanner, ManageTrainingPackageHandler trainer) {
        boolean exitCommand = false;
        String command = null;
        while (!exitCommand) {
            System.out.println("Enter the number of command you want to perform: ");
            System.out.println("1 - List Packages");
            System.out.println("2 - Add Package");
            System.out.println("3 - Delete Package");
            System.out.println("4 - Back");
            System.out.println("5 - Exit");
            command = scanner.nextLine();
            if ("1".equals(command)) {
                System.out.println("packageID" + "\t\t" +"memberName" + "\t\t" + "totalCount" + "\t\t" + "completedSession" + "\t\t" + "description");
                Map<Integer, TrainingPackage> packages = trainer.savePackages();
                for (Integer key : packages.keySet()) {
                    TrainingPackage trainingPackage = packages.get(key);
                    System.out.println(trainingPackage.getPackageID() + "\t\t"  + trainingPackage.getMemberName()  + "\t\t" +trainingPackage.getDescription() + "\t\t" + trainingPackage.getTotalCount() + "\t\t" + trainingPackage.getCompletedSession());
                }
            }else if ("2".equals(command)) {
                System.out.println("Enter the packageID: ");
                String packageID = scanner.nextLine();
                System.out.println("Enter the total count of the package: ");
                String totalCount = scanner.nextLine();
                System.out.println("Enter the description: ");
                String description = scanner.nextLine();
                System.out.println("Enter the member name ");
                String memberName = scanner.nextLine();
                System.out.println("Enter completed sessions");
                String completedSessions = scanner.nextLine();
                TrainingPackage trainingPackage = new TrainingPackage(packageID, description, Integer.parseInt(totalCount), Integer.parseInt(completedSessions), memberName);
                trainer.addPackage(trainingPackage);
        }else if ("3".equals(command)) {
                System.out.println("Enter the packageID: ");
                String packageID = scanner.nextLine();
                trainer.deletePackage(Integer.parseInt(packageID));
            } else if ("4".equals(command)) {
                exitCommand = true;
            } else if ("5".equals(command)) {
                exitCommand = true;
            } else {
                System.out.println("Invalid command!");
            }
        }
        return command;
    }
}