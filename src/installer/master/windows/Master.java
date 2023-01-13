package installer.master.windows;

import installer.utils.ZipArchiveWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class Master extends JFrame
{
    public Master()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panel1);
        setMinimumSize(new Dimension(400, 300));
        setVisible(true);

        files = new HashSet<>();
        chooseFiles = new ChooseFiles(files);
        chooseFiles.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(true);
                chooseFiles.setVisible(false);
            }
        });

        filesChooseButton.addActionListener(e -> chooseFiles());
        создатьУстановщикButton.addActionListener(e -> createBin());
    }

    private void chooseFiles()
    {
        setVisible(false);
        chooseFiles.setVisible(true);
    }

    private void createBin() {
        try (FileOutputStream stream = new FileOutputStream("1.bin"))
        {
            ZipArchiveWriter writer = new ZipArchiveWriter(stream);
            for (var file : files)
                    writer.ArchivingFile(file);
            writer.close();
        }
        catch (IOException exception)
        {
            JOptionPane.showMessageDialog(this,
                    exception.getMessage() + "\n" + exception.getStackTrace(),
                    "error",
                    JOptionPane.WARNING_MESSAGE);
            System.out.println("error" + exception.getMessage() + exception.getStackTrace());
        }
    }

    private ChooseFiles chooseFiles;

    private Set<String> files;
    private JPanel panel1;
    private JButton filesChooseButton;
    private JButton создатьУстановщикButton;
    private JTextField textField1;
    private JButton выбратьButton;
}
