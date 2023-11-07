public class LowerCaseCharacterMissing extends PasswordException{
    @Override
    public String toString() {
        return "Password must have at least 1 lowercase character";
    }
}
