import java.io.*;
import java.util.*;

class Node implements Comparable<Node> {
    char ch;
    int freq;
    Node left, right;

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
        left = right = null;
    }

    public int compareTo(Node node) {
        return Integer.compare(this.freq, node.freq);
    }
}

public class Huffman {
    public static void main(String[] args) throws IOException {
        // Read the text file into a string
        String str = readFile("huftes/cek.txt");

        // Build the Huffman tree
        Node root = buildHuffmanTree(str);

        // Generate the Huffman codes
        printHuffmanCodes(root, "");

        // Encode the string using the Huffman codes
        byte[] encodedData = encodeString(str, root);

        // Write the encoded data to a compressed file
        writeFile("huftes/put.bin", encodedData);

        System.out.println("Compression complete!");
    }

    // Method to read a text file into a string
    public static String readFile(String filename) throws IOException {
        File file = new File(filename);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        return new String(data, "UTF-8");
    }

    // Method to write data to a binary file
    public static void writeFile(String filename, byte[] data) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        fos.write(data);
        fos.close();
    }

    // Method to build the Huffman tree
    public static Node buildHuffmanTree(String str) {
        // Calculate the frequency of each character
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : str.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        // Create a priority queue to store the nodes
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        // Build the Huffman tree
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node('\0', left.freq + right.freq);
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }

        return pq.poll();
    }

    // Method to generate the Huffman codes
    public static void printHuffmanCodes(Node root, String code) {
        if (root == null) {
            return;
        }

        if (root.ch != '\0') {
            System.out.println(root.ch + ": " + code);
        }

        printHuffmanCodes(root.left, code + "0");
        printHuffmanCodes(root.right, code + "1");
    }

    // Method to encode a string using the Huffman codes
    public static byte[] encodeString(String str, Node root) {
        StringBuilder encodedStr = new StringBuilder();
        for (char ch : str.toCharArray()) {
            StringBuilder code = new StringBuilder();
            encode(root, ch, code);
            encodedStr.append(code);
        }

        // Convert the encoded string to a byte array
        byte[] data = new byte[(encodedStr.length() + 7) / 8];
        int index = 0;
        for (int i = 0; i < encodedStr.length(); i += 8) {
            int byteVal = 0;
            for (int j = 0; j < 8 && i + j < encodedStr.length(); j++) {
                byteVal |= (encodedStr.charAt(i + j) - '0') << (7 - j);
            }
            data[index++] = (byte) byteVal;
        }

        return data;
    }

    // Helper method to encode a character using the Huffman codes
    public static void encode(Node root, char ch, StringBuilder code) {
        if (root == null) {
            return;
        }

        if (root.ch == ch) {
            return;
        }

        code.append("0");
        encode(root.left, ch, code);
        if (code.length() > 0) {
            code.deleteCharAt(code.length() - 1);
        }

        code.append("1");
        encode(root.right, ch, code);
        if (code.length() > 0) {
            code.deleteCharAt(code.length() - 1);
        }
    }
}