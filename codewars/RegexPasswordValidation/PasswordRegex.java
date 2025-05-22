package RegexPasswordValidation;

class PasswordRegex {

    static final String REGEX = "^(?=.{6,}$)(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z0-9]+$";
}
// (?=.{6,}$)  что длина строки  6 символов
// (?=.*[a-z] строчная буква
// (?=.*[A-Z]) заглавная буква
// (?=.*\d) цифры
// [A-Za-z0-9]+  латынь
