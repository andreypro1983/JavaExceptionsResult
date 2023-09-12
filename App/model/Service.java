package App.model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.DataFormatException;
import App.Exceptions.InputUserDataExeption;
import App.Exceptions.PhoneFormatExeption;
import App.Exceptions.SexFormatExeption;
import App.ui.Console;

public class Service {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    FileHandler fileHandler;
    Console console;

    public Service(FileHandler fileHandler, Console console) {
        this.fileHandler = fileHandler;
        this.console = console;
        console.setService(this);
    }

    /**
     * 
     * @param userData Входящая информация в формате String
     * @return Возвращает экземпляр объекта User
     * @throws InputUserDataExeption Несоответствие количества введеных параметров
     * @throws DataFormatException   Некорретный формат даты рождения
     * @throws PhoneFormatExeption   Некорректный формат номера телефона
     * @throws SexFormatExeption     Некорректное значение пола
     * @throws IOException           Ошибки работы с файлом
     */

    public boolean addUser(String userData)
            throws InputUserDataExeption, DataFormatException, PhoneFormatExeption, SexFormatExeption, IOException {
        String[] userParseData = parseUserData(userData);
        CheckCountInputElementsExeptions(userParseData);
        Date userBirthday = CheckValidUserBirthday(userParseData[3]);
        Long userPhone = CheckValidUserPhone(userParseData[4]);
        Sex sex = CheckValidSex(userParseData[5]);
        if ((userBirthday != null) && (userPhone != -1) && (sex != null)) {
            return saveToFile(userParseData[0],
                    new User(userParseData[0], userParseData[1], userParseData[2], userBirthday, userPhone, sex));
        } else
            return false;
    }

    private static void CheckCountInputElementsExeptions(String[] userParseData) throws InputUserDataExeption {
        int rightCount = 6;
        switch (checkCountInputElements(userParseData, rightCount)) {
            case -1:
                throw new InputUserDataExeption("Введено меньше данных чем требовалось", userParseData.length,
                        rightCount);
            case 1:
                throw new InputUserDataExeption("Введено больше данных чем требовалось", userParseData.length,
                        rightCount);
            default:
                break;
        }
    }

    public String showUserInfoBySurname(String surname) throws IOException {
        String userInfo = loadFromFile(surname);
        return userInfo.replaceAll("><", " ").replaceAll("<", "").replaceAll(">", "");
    }

    public String loadFromFile(String surname) throws IOException {
        return fileHandler.loadFromFile(surname);
    }

    public boolean saveToFile(String fileName, User newUser) throws IOException {
        return fileHandler.saveToFile(fileName, newUser.getUserInfo(DATE_FORMAT));
    }

    private static String[] parseUserData(String userData) {
        return userData.split(" ");
    }

    private static int checkCountInputElements(String[] userData, int rightCount) {
        if (userData.length < rightCount) {
            return -1;
        } else if (userData.length > rightCount) {
            return 1;
        } else {
            return 0;
        }
    }

    private static Date CheckValidUserBirthday(String UserBirthday) throws DataFormatException {
        Date parseDate;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            sdf.setLenient(false);
            parseDate = sdf.parse(UserBirthday);
        } catch (Exception e) {
            parseDate = null;
            throw new DataFormatException("Введенная дата не соответствует формату " + DATE_FORMAT.toLowerCase());
        }
        return parseDate;
    }

    private static long CheckValidUserPhone(String userPhone) throws PhoneFormatExeption {
        long phone = -1;
        if (userPhone.matches("\\d+")) {
            try {
                phone = Long.parseLong(userPhone);
            } catch (NumberFormatException e) {
                throw new PhoneFormatExeption(
                        "Введен не корректный номер телефона.\nДопустимо только целое беззнаковое число.\nВы ввели ",
                        userPhone);
            }
        } else {
            throw new PhoneFormatExeption(
                    "Введен не корректный номер телефона.\nДопустимо только целое беззнаковое число.\nВы ввели ",
                    userPhone);
        }
        return phone;
    }

    private static Sex CheckValidSex(String userSex) throws SexFormatExeption {
        Sex sex;
        if ((userSex.toLowerCase().equals("f")) || (userSex.toLowerCase().equals("m"))) {
            if (userSex.toLowerCase().equals("f")) {
                sex = Sex.f;
            } else {
                sex = Sex.m;
            }
        } else {
            throw new SexFormatExeption("Не корректный формат пола. Допустимы только " + Sex.f + " или " + Sex.m,
                    userSex);
        }
        return sex;
    }
}
