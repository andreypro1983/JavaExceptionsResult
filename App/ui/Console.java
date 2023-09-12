package App.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

import App.Exceptions.InputUserDataExeption;
import App.Exceptions.PhoneFormatExeption;
import App.Exceptions.SexFormatExeption;
import App.model.Service;


public class Console {
    private Menu menu;
    private boolean isWork = true;
    private Service service;
    private static final String addUserMessage = "Введите <Фамилию> <Имя> <Отчество> <Дату_рождения> <Номер_телефона> <Пол> через пробел:";

    public Console() {
        this.menu = new Menu();
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void start() {
        while (isWork) {
            print(menu.menu());
            try {
                int userInput = inputFromUser();
                switch (userInput) {
                    case 1:
                        addUser();
                        break;
                    case 2:
                        showUserInfoBySurname();
                        break;
                    case 3:
                        exit();
                        break;
                }
            } catch (InputMismatchException e) {
                print(e.getMessage() + "\n");
            } catch (FileNotFoundException e) {
                print(e.getMessage() + "\nПовторите попытку\n");
            } catch (RuntimeException e) {
                print(e.getMessage() + "\n");
            } catch (InputUserDataExeption e) {
                print(e.getMessage() + ". Вы ввели " + e.getUserCount() + " из " + e.getRightCount() + "\n"
                        + "\nПовторите попытку\n");
            } catch (DataFormatException e) {
                System.out.println(e.getMessage() + "\nПовторите попытку\n");
            } catch (PhoneFormatExeption e) {
                System.out.println(e.getMessage() + " " + e.getUserPhone() + "\nПовторите попытку\n");
            } catch (SexFormatExeption e) {
                System.out.println(e.getMessage() + ". Вы ввели " + e.getUserSex() + "\nПовторите попытку\n");
            } catch (IOException e) {
                System.out.println(e.getMessage() + "\n" + e.getStackTrace() + "\nПовторите попытку\n");
            }
        }
    }

    private void addUser() throws  InputUserDataExeption, DataFormatException,
            PhoneFormatExeption, SexFormatExeption, IOException {
        String inputData = inputFromUserInternal(addUserMessage);
        print(service.addUser(inputData) ? "Информация успешно добавлена\n"
                : "Не удалось добавить информацию\n");


    }

    private void showUserInfoBySurname() throws NoSuchElementException, IOException {
        String inputData = inputFromUserInternal("Введите Фамилию пользователя для отображения: ");
        print("\nИнформация о пользователе " + inputData + ":");
        print(service.showUserInfoBySurname(inputData));

    }

    private void exit() {
        isWork = false;
        print("\nДо свидания!\n");
    }

    private int inputFromUser() throws InputMismatchException, RuntimeException {
        int inputData = -1;
        Scanner sc = new Scanner(System.in, "Cp866");
        try {
            System.out.println("Выберите пункт меню:");
            inputData = sc.nextInt();
            if (inputData > 3 || inputData < 1) {
                throw new RuntimeException("Необходимо выбрать пункт от 1 до 3");
            }

        } catch (InputMismatchException e) {
            throw new InputMismatchException("Введеное значение не является числом");
        }
        return inputData;
    }

    public void print(String string) {
        System.out.println(string);
    }

    public String inputFromUserInternal(String message) throws NoSuchElementException {
        System.out.println(message);
        String inputData;
        Scanner sc = new Scanner(System.in, "Cp866");
        inputData = sc.nextLine();
        if (inputData.equals("")) {
            throw new NoSuchElementException("Данные не были введены");
        }
        return inputData;
    }

}
