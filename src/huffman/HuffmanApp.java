package huffman;// Кодирование Хаффмана

import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Scanner;


// Класс для демонстрации работы
public class HuffmanApp {
    // Главная функция
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Сообщение, которое нужно закодировать
        String message = scanner.nextLine();
        HuffmanNode root = createTree(message);
        HashMap<Character, String> map = new HashMap<>();
        buildEncodingMap(root, new StringBuilder(), map);
        // Выводим коды Хаффмана для каждого символа
        System.out.println("Huffman codes:");
        System.out.println(map);
        // Кодируем сообщение
        String encodedMessage = encodeMessage(message, map);
        System.out.println("Encoded message:");
        System.out.println(encodedMessage);
        // Декодируем сообщение
        HashMap<String, Character> decodingMap = buildDecodingMap(map);
        String decodedMessage = decodeWithMap(encodedMessage, decodingMap);
        System.out.println("Decoded message:");
        System.out.println(decodedMessage);
    }

    private static HuffmanNode createTree(String message) {
        // Создаём карту (Map) для подсчёта частоты каждого символа
        HashMap<Character, Integer> frequencyMap = new HashMap<>();

        for (char c : message.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Создаём очередь с приоритетом для построения дерева Хаффмана
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>((a, b) -> a.frequency - b.frequency);

        // Создаём узел Хаффмана для каждого символа и добавляем его в приоритетную очередь
        for (char c : frequencyMap.keySet()) {
            priorityQueue.add(new HuffmanNode(c, frequencyMap.get(c)));
        }

        // Строим дерево Хаффмана
        while (priorityQueue.size() > 1) {
            // Извлекаем два узла с наименьшей частотой
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            // Создаём новый внутренний узел с этими двумя узлами в качестве дочерних
            // и добавляем его обратно в очередь
            HuffmanNode newNode = new HuffmanNode('$', left.frequency + right.frequency);

            newNode.left = left;
            newNode.right = right;
            priorityQueue.add(newNode);
        }

        // Оставшийся узел — это корень дерева Хаффмана
        return priorityQueue.poll();
    }


    // Метод для построения карты кодирования (рекурсивный обход дерева)
    private static void buildEncodingMap(HuffmanNode root, StringBuilder code, HashMap<Character, String> map) {
        if (root == null) return;

        // Если узел листовой, добавляем символ и его код в карту
        if (root.data != '$') {
            map.put(root.data, code.toString());
        }

        // Обходим левое поддерево, добавляя '0' к коду
        if (root.left != null) {
            buildEncodingMap(root.left, code.append('0'), map);
            code.deleteCharAt(code.length() - 1); // Удаляем последний символ после возврата
        }

        // Обходим правое поддерево, добавляя '1' к коду
        if (root.right != null) {
            buildEncodingMap(root.right, code.append('1'), map);
            code.deleteCharAt(code.length() - 1); // Удаляем последний символ после возврата
        }
    }

    // Метод для кодирования сообщения
    private static String encodeMessage(String message, HashMap<Character, String> map) {
        StringBuilder encodedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            encodedMessage.append(map.get(c)); // Получаем код символа из карты
        }
        return encodedMessage.toString(); // Возвращаем строку с закодированным сообщением
    }

    // Метод для создания обратного словаря из карты кодирования
    private static HashMap<String, Character> buildDecodingMap(HashMap<Character, String> encodingMap) {
        HashMap<String, Character> decodingMap = new HashMap<>();
        for (Character c : encodingMap.keySet()) {
            decodingMap.put(encodingMap.get(c), c);
        }
        return decodingMap;
    }

    // Метод для декодирования сообщения
    private static String decodeWithMap(String encodedMessage, HashMap<String, Character> decodingMap) {
        StringBuilder decodedMessage = new StringBuilder();
        StringBuilder currentCode = new StringBuilder();

        for (char bit : encodedMessage.toCharArray()) {
            currentCode.append(bit);
            if (decodingMap.containsKey(currentCode.toString())) {
                decodedMessage.append(decodingMap.get(currentCode.toString()));
                currentCode.setLength(0); // Сбрасываем текущий код
            }
        }
        return decodedMessage.toString();
    }
}
