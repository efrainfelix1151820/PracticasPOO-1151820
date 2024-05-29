import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ChatClient {
    private JFrame frame = new JFrame("Chat Client");
    private JTextField serverAddressField = new JTextField("localhost");
    private JTextField serverPortField = new JTextField("8087");
    private JTextField usernameField = new JTextField(10);
    private JTextArea messageArea = new JTextArea(20, 40);
    private JTextField messageField = new JTextField(40);
    private JButton sendButton = new JButton("Enviar");

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String username;

    public ChatClient() {
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Server Address:"));
        topPanel.add(serverAddressField);
        topPanel.add(new JLabel("Port:"));
        topPanel.add(serverPortField);
        topPanel.add(new JLabel("Username:"));
        topPanel.add(usernameField);
        JButton connectButton = new JButton("Connect");
        topPanel.add(connectButton);
        frame.add(topPanel, BorderLayout.NORTH);

        messageArea.setEditable(false);
        frame.add(new JScrollPane(messageArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(messageField);
        bottomPanel.add(sendButton);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        sendButton.setEnabled(false);

        connectButton.addActionListener(e -> new Thread(this::connectToServer).start());
        sendButton.addActionListener(e -> sendMessage());
        messageField.addActionListener(e -> sendMessage());

        frame.pack();
        frame.setVisible(true);
    }

    private void connectToServer() {
        String serverAddress = serverAddressField.getText();
        int port = Integer.parseInt(serverPortField.getText());
        username = usernameField.getText();
        
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Username cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            socket = new Socket(serverAddress, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            new Thread(new IncomingReader()).start();

            out.println("Hola");
            if ("Hola".equals(in.readLine())) {
                out.println("Usuario:" + username);
                sendButton.setEnabled(true);
                messageArea.append("Connected to the chat server as " + username + "\n");
            } else {
                messageArea.append("Failed to connect to the chat server\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            messageArea.append("Failed to connect to the chat server\n");
        }
    }

    private void sendMessage() {
        String message = messageField.getText();
        if (message.isEmpty()) return;

        if (message.equals("Adios")) {
            out.println("Adios");
            disconnect();
        } else {
            out.println("Enviar" + username + ": " + message);
        }
        messageField.setText("");
    }

    private void disconnect() {
        try {
            if (socket != null) {
                socket.close();
            }
            sendButton.setEnabled(false);
            messageArea.append("Disconnected from the chat server\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class IncomingReader implements Runnable {
        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    messageArea.append(message + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                sendButton.setEnabled(false);
                messageArea.append("Disconnected from the chat server\n");
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatClient::new);
    }
}
