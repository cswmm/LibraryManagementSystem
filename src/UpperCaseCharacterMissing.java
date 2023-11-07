public class UpperCaseCharacterMissing extends PasswordException{
    @Override
    public String toString() {
        return "Password must have at least 1 uppercase character";
    }
}
