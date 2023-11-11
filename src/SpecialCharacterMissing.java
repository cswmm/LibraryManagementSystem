public class SpecialCharacterMissing extends PasswordException{
    public SpecialCharacterMissing() {
        super("Password must have at least 1 special character");
    }
}
