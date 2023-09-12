package App.ui;

public class Menu {
    public String menu() {
        StringBuilder sb = new StringBuilder();
        sb.append("МЕНЮ:\n");
        sb.append("1. Добавить нового пользователя\n");
        sb.append("2. Вывести информацию о пользователе\n");
        sb.append("3. Выход\n");
        return sb.toString();
    }


}