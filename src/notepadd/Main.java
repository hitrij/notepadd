package notepadd;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main<name, phone, surname, email> {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Person> personList = new ArrayList<>();
    public Integer order;

    public static void main(String[] args) {
        int order = -1;
        LoadPersonList(order);

        while (true) {
            System.out.println("Enter command (Create = 1, List Contacts = 2, Delete = 3, Help = H, Exit = 0):");
            String cmd = scanner.next();
            switch (cmd) {
                case "1":
                    create();
                    break;
                case "2":
                    showPersonList();
                    break;
                case "3":
                    deletePersone();
                    break;
                case "H":
                    showHelp();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("It isn't a command");
            }
        }


    }

    private static void showHelp() {
        System.out.println("Help xxxxxxxxxxx");
        System.out.println("Help xxxxxxxxxxx");
        System.out.println("Help xxxxxxxxxxx");
        System.out.println("Help xxxxxxxxxxx");
        System.out.println("Help xxxxxxxxxxx");
    }

    private static void deletePersone() {
        System.out.println("Enter command Person ID for Delete:");
        Integer cmd = scanner.nextInt();
        personList = new ArrayList<>();
        Person.count = -1;
        LoadPersonList(cmd);
        saveNewPerson();

    }

    private static void showPersonList() {
        for (Person p : personList) {
            System.out.println(p);
        }
    }


    private static void create() {

        String name;
        String surname;
        String phone;
        String email;


        do {
            System.out.println("Please enter name:");
            name = askString();
            if (name.contains("_")) name="";
        } while (name.isEmpty());
        do {
            System.out.println("Please enter Surname:");
            surname = askString();
            if (surname.contains("_")) surname="";
        } while (surname.isEmpty());
        do {
            System.out.println("Please enter phone number:");
            phone = scanner.next();
            if (!phone.matches("\\d+")) {
                phone = "";
                System.out.println("Phone number must contain only digits!");
            }
            if (phone.length() < 5) phone = "";
        } while (phone.isEmpty());
        do {
            System.out.println("Please Enter Email address:");
            email = scanner.next();
        } while (email.isEmpty());

        Person p = new Person();
        p.setName(name);
        p.setPhone(phone);
        p.setSurname(surname);
        p.setEmail(email);
        personList.add(p);

        saveNewPerson();

        //System.out.println(p);
    }

    private static String askString() {
        var result = new ArrayList<String>();
        var word = scanner.next();
        if (word.startsWith("\"")) {
            do {
                result.add(word);
                if (word.endsWith("\"")) {
                    String str = String.join("_", result);
                    return str.substring(1, str.length()-1);
                }
                word = scanner.next();
            } while(true);
        } else {
            return word;
        }
    }

    private static void LoadPersonList(Integer order) {
        File file = new File("contacts.txt");
        try (Scanner in = new Scanner(file)) {
            while (in.hasNext()) {
                Person p = new Person();
                p.setId(in.nextInt());
                p.setName(in.next());
                p.setSurname(in.next());
                p.setPhone(in.next());
                p.setEmail(in.next());
                if (p.getId() < order || order < 1) {
                    personList.add(p);
                } else if (p.getId() > order) {
                    p.setId(p.getId() - 1);
                    personList.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("Cannot save to File");
        }

    }

    private static void saveNewPerson() {
        File file = new File("contacts.txt");

        try (PrintWriter out = new PrintWriter(file)) {
            for (Person p : personList) {
                out.printf("%s %s %s %s %s\r\n", p.getId(), p.getName(), p.getSurname(), p.getPhone(), p.getEmail());
            }
        } catch (IOException e) {
            System.out.println("Cannot save to File");
        }
    }
}