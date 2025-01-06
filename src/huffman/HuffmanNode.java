package huffman;

// Класс, представляющий узел в дереве Хаффмана
public class HuffmanNode {
    // Символ, который хранится в узле
    char data;

    // Частота появления символа
    int frequency;

    // Левый и правый дочерние узлы
    HuffmanNode left, right;

    // Конструктор для инициализации узла
    HuffmanNode(char data, int frequency) {
        this.data = data; // Устанавливаем символ
        this.frequency = frequency; // Устанавливаем частоту
        left = right = null; // Инициализируем дочерние узлы как null
    }
}
