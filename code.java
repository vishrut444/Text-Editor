import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

//TextEditor implements ActionListener interface
public class TextEditor implements ActionListener {
    //Declaring properties of text editor

    JFrame frame;//to create application window

    JMenuBar menuBar;//to create menu bar

    JMenu file,edit;//to add menu in menuBar file and edit

    //to create menu items

    //for file menu items
    JMenuItem newFile, openFile, saveFile;

    //for edit menu items
    JMenuItem cut, copy, paste, selectAll, close;

    //to write text
    JTextArea textArea;

    //constructor
    TextEditor(){
        //Initialize a frame
        frame = new JFrame();

        //Initialize menuBar
        menuBar = new JMenuBar();

        //Initializing text area
        textArea = new JTextArea();

        //Initialize  menu in menuBar
        file = new JMenu("File");//passing name of the menu
        edit = new JMenu("Edit");

        //Initialize File menu items
        newFile = new JMenuItem("New Window");//passing the text to show on the screen
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //adding action listener to File menu items
        newFile.addActionListener(this);//this listens to newFile menu item when clicked
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //add menu items to menu File
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialize Edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //adding action listener to Edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //adding edit menu items to Edit file
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //adding menu's to menu bar before adding menuBar to frame
        menuBar.add(file);
        menuBar.add(edit);

        //set menu bar to frame
        frame.setJMenuBar(menuBar);

        //adding text area to the frame
        //frame.add(textArea);

        //create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        //create scroll pane to make text area scrollable
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to the panel
        panel.add(scrollPane);
        //add panel to pane
        frame.add(panel);

        //set dimensions of frame
        //1st val and 2nd val tells in which corner will the frame be open (0,0) -> top left corner
        //3rd & 4th value stes width and height of frame
        frame.setBounds(100,100,500,500);
        frame.setTitle("Text Editor by Vishrut");//for setting title
        frame.setVisible(true);//frame will be not hidden
        frame.setLayout(null);
    }

    @Override
    //we hae passed actionEvent as parameter tp actionPerformed so what action to perform
    public void actionPerformed(ActionEvent actionEvent){
        //mapping actionEvent to actions
        if(actionEvent.getSource()==cut){
            //perform cut operation
            textArea.cut();//inbuilt cut() method in JTextArea
        }
        if(actionEvent.getSource()==copy){
            //perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            //perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            //perform selectAll operation
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            //perform close operation
            System.exit(0);//to exit from application
        }

        //for open new file
        if(actionEvent.getSource()==openFile){
            //open file chooser
            JFileChooser fileChooser = new JFileChooser("C:");//provide path here C drive
            int chooseOption = fileChooser.showOpenDialog(null);
            //if we clicked on open button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //getting the selected file
                File file = fileChooser.getSelectedFile();
                //get te path of selected file
                String filePath = file.getPath();
                //try and catch for exception handeling
                try{
                    //Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialize buffer reader to read contents of the file
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    //read content of file line by line
                    while((intermediate=bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //set the output string to text area
                    textArea.setText(output);
                }
                catch(FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

        //for save file
        if(actionEvent.getSource()==saveFile){
            //Initialize file picker
            JFileChooser fileChooser = new JFileChooser("C:");//path to save
            //get choose options from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);//it shows save button on file picker menue
            //check if we clicked on sae button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //create new file with choosen directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try {
                    FileWriter fileWriter = new FileWriter(file);
                    //Intitialize buffer writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

        //for new file
        if(actionEvent.getSource()==newFile){
            //create new text edior
            TextEditor newTextEditor = new TextEditor();
        }

    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();

    }
}
