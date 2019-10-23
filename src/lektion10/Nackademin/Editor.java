package lektion10.Nackademin;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;
import java.io.*;

import static java.awt.BorderLayout.*;

public class Editor extends JFrame {
    JPanel areaPanel = new JPanel();
    JPanel topPanel = new JPanel();
    JTextArea tArea = new JTextArea(20, 60);
    JScrollPane area = new JScrollPane(tArea);
    JLabel fileName = new JLabel("Filename:");
    JTextField inFileName = new JTextField(20);
    JButton bOpen = new JButton("open");
    JButton bSave = new JButton("save");
    JButton bPrint = new JButton("print");
    JButton bExit = new JButton("exit");

    // test comments in branch


    Editor() {

        System.out.println("trying branch change again");
        add(areaPanel);
        areaPanel.setLayout(new BorderLayout());
        areaPanel.add(area, CENTER);
        tArea.setLineWrap(true);
        bOpen.addActionListener(l -> {
            JFileChooser choose = new JFileChooser();
            choose.showOpenDialog(null);
            inFileName.setText(choose.getSelectedFile().toString());
            try {
                BufferedReader buffer = new BufferedReader(new FileReader(inFileName.getText()));
                tArea.read(buffer, null);
            }catch(IOException e) {
                e.printStackTrace();
            }
        });
        bSave.addActionListener(l -> {
            JFileChooser choose = new JFileChooser();
            choose.showSaveDialog(null);
            File files = new File(String.valueOf(choose.getSelectedFile()));
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(files)));
                tArea.write(out);
            } catch(IOException e) {
                e.printStackTrace();
            }
        });
        bPrint.addActionListener(l -> {
            try {
                tArea.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        });
        bExit.addActionListener(l -> {
            System.exit(0);
        });

        topPanel.add(fileName);
        topPanel.add(inFileName);
        topPanel.add(bOpen);
        topPanel.add(bSave);
        topPanel.add(bPrint);
        topPanel.add(bExit);
        areaPanel.add(topPanel, NORTH);
        tArea.setFont(new Font("MONOSPACED", Font.PLAIN, 12));
        pack();
        this.setLocation(500, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
