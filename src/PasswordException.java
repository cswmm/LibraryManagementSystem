public class PasswordException extends Exception{
    public PasswordException(String message) {
        super(message);
    }
}

class Minimum8CharactersRequired extends PasswordException{
    public Minimum8CharactersRequired() {
        super("Password must be at least 8 characters");
    }

}

class LowerCaseCharacterMissing extends PasswordException{
    public LowerCaseCharacterMissing() {
        super("Password must have at least 1 lowercase character");
    }

}

class SpecialCharacterMissing extends PasswordException{
    public SpecialCharacterMissing() {
        super("Password must have at least 1 special character");
    }
}

class UpperCaseCharacterMissing extends PasswordException{
    public UpperCaseCharacterMissing() {
        super("Password must have at least 1 uppercase character");
    }
}

class NumberCharacterMissing extends PasswordException{
    public NumberCharacterMissing() {
        super("Password must have at least 1 number");
    }

}


