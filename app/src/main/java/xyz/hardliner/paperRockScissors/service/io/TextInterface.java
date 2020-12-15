package xyz.hardliner.paperRockScissors.service.io;

public interface TextInterface {

    String requestInput();

    String requestInputWithPrompt(String prompt);

    void sendOutput(String str);

}
