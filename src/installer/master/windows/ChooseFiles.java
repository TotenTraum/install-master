package installer.master.windows;

import javax.swing.*;
import java.awt.event.*;
import java.nio.file.Files;
import java.util.*;

public class ChooseFiles extends JDialog {
    private JPanel contentPane;
    private JButton buttonAdd;
    private JList list1;
    private Set<String> files = null;

    public ChooseFiles(Set<String> _files) {
        files = _files;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonAdd);
        chooser = new JFileChooser();
        setSize(300, 300);

        buttonAdd.addActionListener(e -> onAdd());

        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() % 2 == 0)
                    onRemove();
            }
        });
    }

    private void onAdd() {
        chooser.setVisible(true);
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        var result = chooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            List<String> stringList = new ArrayList<>();
            for (var file: chooser.getSelectedFiles()) {
                stringList.add(file.getAbsolutePath());
            }
            files.addAll(stringList);
            list1.setListData(files.stream().toArray());
        }
    }

    private void onRemove()
    {
        var val = list1.getSelectedValue();
        files.remove(val);
        list1.setListData(files.toArray());
        repaint();
    }

    JFileChooser chooser;
}
