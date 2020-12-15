package xyz.hardliner.paperRockScissors.service.io;

import java.util.Scanner;

public class ConsoleHandler implements TextInterface {

    private final Scanner scanner;

    public ConsoleHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String requestInput() {
        return scanner.nextLine();
    }

    @Override
    public String requestInputWithPrompt(String prompt) {
        sendOutput(prompt);
        return requestInput();
    }

    @Override
    public void sendOutput(String str) {
        System.out.println(str);
    }
}
