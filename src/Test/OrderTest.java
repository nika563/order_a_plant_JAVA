package Test;
import Order.Order;

import java.io.*;
import java.util.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class OrderTest {
    @Test // test - Сповіщення "Ви хочете, щоб вибрати тип служби? Enter YES or NO:"
    public void testTextEnter() throws IOException {
        // Перечисляем возможные входные данные и ожидаемые результаты для каждого случая
        Map<String, String> testCases = Map.of(
                "123", "Do you want to remove a type of service? Enter YES or NO: ",
                "abc", "Do you want to remove a type of service? Enter YES or NO: ",
                "<./@*()", "Do you want to remove a type of service? Enter YES or NO: "
        );

        for (Map.Entry<String, String> testCase : testCases.entrySet()) {
            String input = testCase.getKey();
            String expectedOutput = testCase.getValue();

            // Налаштовуємо введення для поточного тестового випадку
            InputStream in = new ByteArrayInputStream((input + "\n").getBytes());
            System.setIn(in);

            // Перехоплення виведення консолі
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            // Виклик тестованого методу
            Order order = new Order();
            order.removeTypeService();

            // Перевірка виведення на консоль
            String output = outContent.toString();
            assertTrue(output.contains(expectedOutput), "Expected message missing for input: " + input);
        }
    }
    @Test //test - Повідомлення "Enter name of the service you want to delete (or type 'no' to cancel"):"
    public void testTextDelete() throws IOException {
        // Перечисляем возможные входные данные и ожидаемые результаты для каждого случая
        Map<String, String> testCases = Map.of(
                "123", "Enter the name of the service you want to delete (or type 'no' to cancel): ",
                "abc", "Enter the name of the service you want to delete (or type 'no' to cancel): ",
                "<./@*()", "Enter the name of the service you want to delete (or type 'no' to cancel): "
        );

        for (Map.Entry<String, String> testCase : testCases.entrySet()) {
            String input = testCase.getKey();
            String expectedOutput = testCase.getValue();

            // Налаштовуємо введення для поточного тестового випадку
            InputStream in = new ByteArrayInputStream((input + "\n").getBytes());
            System.setIn(in);

            // Перехоплення виведення консолі
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            // Виклик тестованого методу
            Order order = new Order();
            order.testHandleServiceRemoval();

            // Перевірка виведення на консоль
            String output = outContent.toString();
            assertTrue(output.contains(expectedOutput), "Expected message missing for input: " + input);
        }
    }
    @Test //test - Вибрана послуга не існує
    public void testRemoveNotExistTypeService() throws IOException {
        String input = "nonexistentservice\nno\n";  // Вводим несуществующую услугу
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Order order = new Order();
        order.testHandleServiceRemoval();

        String output = outContent.toString();
        assertTrue(output.contains("Service not found."), "Expected 'Service not found.' message was not found.");
    }
    @Test // test - Видалити послугу
    public void testDeleteTypeService() throws IOException {
        // Загружаем список услуг, из которого будем удалять услугу
        Order order = new Order();
        List<String> services = order.forTestLoadServices();
        services.add("service1");

        // Проверяем, что услуга была добавлена
        assertTrue(services.contains("service1"), "Service 'service1' should be added.");

        // Входные данные: имя услуги для удаления
        String input = "service1\nno\n";  // Удаляем "service1"
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Перехват вывода консоли
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Создаем объект Order и вызываем метод удаления услуги
        Scanner scanner = new Scanner(System.in);
        order.testHandleService(services, scanner);

        // Проверяем, что услуга была удалена
        assertFalse(services.contains("service1"), "Service 'service1' should be removed.");

        // Проверяем, что в выводе содержится сообщение о том, что услуга успешно удалена
        String output = outContent.toString();
        assertTrue(output.contains("Service removed successfully."), "Expected 'Service removed successfully.' message was not found.");
    }
}
