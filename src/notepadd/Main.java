package notepadd;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Person> personList = new ArrayList<>();

    public static void main(String[] args) {
        LoadPersonList();

        while (true) {
            System.out.println("Enter command (Create = 1, List Contacts = 2, Delete = 3, Exit = 0):");
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
                case "0":
                    return;
                default:
                    System.out.println("It isn't a command");
            }
        }


    }

    private static void deletePersone() {
        System.out.println("Enter command Person ID for Delete:");
        Integer cmd = scanner.nextInt();


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


        do {
            System.out.println("Please enter name:");
            name = scanner.next();
        } while (name.isEmpty());
        do {
            System.out.println("Please enter Surname:");
            surname = scanner.next();
        } while (surname.isEmpty());
        do {
            System.out.println("Please enter phone number:");
            phone = scanner.next();
        } while (phone.isEmpty());

        Person p = new Person();
        p.setName(name);
        p.setPhone(phone);
        p.setSurname(surname);
        personList.add(p);

        saveNewPerson();

        //System.out.println(p);
    }

    private static void LoadPersonList() {
        File file = new File("contacts.txt");
        try (Scanner in = new Scanner(file)) {
            while (in.hasNext()) {
                Person p = new Person();
                p.setId(in.nextInt());
                p.setName(in.next());
                p.setSurname(in.next());
                p.setPhone(in.next());
                personList.add(p);
            }
        } catch (IOException e) {
            System.out.println("Cannot save to File");
        }

    }

    private static void saveNewPerson() {
        File file = new File("contacts.txt");

        try (PrintWriter out = new PrintWriter(file)) {
            for (Person p : personList) {
                out.printf("%s %s %s %s\r\n", p.getId(), p.getName(), p.getSurname(), p.getPhone());
            }
        } catch (IOException e) {
            System.out.println("Cannot save to File");
        }
    }
}