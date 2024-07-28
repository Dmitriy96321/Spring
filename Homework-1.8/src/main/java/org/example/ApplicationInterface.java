package org.example;

import org.example.service.ContactService;
import org.example.util.PersonManager;
import org.example.util.writer.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ApplicationInterface {
    private final String HELP_MESSAGE = "Здравствуйте, это небольшое консольное приложение \"Контакты\".\n" +
            "Которое позволяет сохранять необходимые вам контакты.\n" +
            "Для работы с приложением используются следующие комманды:\n" +
            "list - выводит список всех существующих контактов. Формат ввода \"list\"\n" +
            "add - добавляет новый контакт. Формат ввода \"add Иванов Иван Иванович;" +
            "+890999999;someEmail@example.example\"\n" +
            "delete - удаляет контакты по email. Формат ввода \"delete someEmail@example.example\"\n" +
            "help - выводит список комманд и информацию о приложении. Формат ввода \"help\"\n" +
            "exit - выход. Формат ввода \"exit\"\n";
    private final String ERROR_MESSAGE = "Не верный формат ввода, воспользуйтесь коммандой \"help\"";
    private final ContactService contactService;
    private final PersonManager personManager;

    @Autowired
    public ApplicationInterface(ContactService contactService, Writer writer, PersonManager personManager) {
        this.personManager = personManager;
        this.contactService = contactService;
    }

    public void start() {

        System.out.println(HELP_MESSAGE);
        Scanner scanner = new Scanner(System.in);
        while (true) {

            String[] input = scanner.nextLine().split(" ", 2);

            if (input[0].equals("help") || input[0].equals("exit") || input[0].equals("list") || input.length > 1) {
                switch (input[0]) {
                    default          -> System.out.println(ERROR_MESSAGE);
                    case    "list"   -> System.out.println(contactService.getAllContacts());
                    case    "exit"   -> {return;}
                    case    "help"   -> System.out.println(HELP_MESSAGE);
                    case    "delete" -> contactService.removeContact(contactService.getContactByEmail(input[1]));
                    case    "add"    -> contactService.addContact(personManager.createPerson(input[1]));
                }
            } else {
                System.out.println(ERROR_MESSAGE);
            }
        }
    }
}
