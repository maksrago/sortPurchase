//Maksymilian M. Rago
//CISC 3115
//Scott Dexter
//Project 2
//compile command: javac ProjectFour.java ProjectUtils.java RealEstateSale.java
//run command: java ProjectFour

import java.util.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.*;
import javax.swing.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.*;
import java.awt.*;
import java.util.Locale;
import javax.swing.*;
import java.util.Arrays;

public class ProjectFour extends JFrame {
    static ArrayList<RealEstateSale> list = new ArrayList<RealEstateSale>();
    double total = 0;

    public ProjectFour() {
        initComponents();
    }

    private void initComponents() {
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        final JList list1 = new JList(); //List that will display all of the elements in list
        DefaultListModel listModel = new DefaultListModel();
        SimpleDateFormat dateBeautify = new SimpleDateFormat("MMMM d, yyyy"); //Will format the date to look nicer in the JList

        //Prints all of the elements into the JList
        for (int i = 0; i < list.size(); i++) {
            listModel.addElement(list.get(i).getCountry() + " | $" + list.get(i).getPrice() + " | " + dateBeautify.format(list.get(i).getDate()));
            total = total + list.get(i).getPrice(); //Finds the sum of all of the elements
        }

        list1.setModel(listModel);

        DecimalFormat changeFormat = new DecimalFormat("#0.00");
        //JTextField totalDisplay = new JTextField("Total: $" + Double.parseDouble(changeFormat.format(total)));

        double dateCutTotal = 0;
        JTextField totalDisplay = new JTextField("Total: " + Double.parseDouble(changeFormat.format(total)), 8);

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Date firstDate = new Date("2001/10/20");
        Date lastDate = new Date("2018/12/12");

        final SpinnerDateModel model = new SpinnerDateModel(firstDate, firstDate, lastDate, 1);
        final SpinnerDateModel model2 = new SpinnerDateModel(firstDate, firstDate, lastDate, 1);

        //Creates two JSpinners, first one acts as the start date, the second acts as the end date
        JSpinner jSpinner1 = new JSpinner(model);
        JSpinner jSpinner2 = new JSpinner(model2);

        jSpinner1.setValue(new Date("2001/10/20"));
        jSpinner2.setValue(new Date("2018/12/12"));

        //Isolates the totals from a date selection
        JButton button1 = new JButton("Sort Dates");
        button1.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e) {
                Date firstParse = (Date)jSpinner1.getValue();
                Date secondParse = (Date)jSpinner2.getValue();

                System.out.println(firstParse + " " + secondParse);
                double dateCutTotal = 0;
                
                System.out.println("First: " + firstParse);
                System.out.println("Second: " + secondParse + "\n");

                DecimalFormat changeFormat = new DecimalFormat("#0.00");
                for (int i = 0; i < list.size(); i++) {
                    Date parsedDate = list.get(i).getDate();

                    System.out.println("Parsed: " + parsedDate);

                    if (firstParse.compareTo(parsedDate) == -1 && secondParse.compareTo(parsedDate) == 1) {
                        System.out.println("Taken: " + parsedDate);
                        dateCutTotal = dateCutTotal + list.get(i).getPrice();
                        totalDisplay.setText("Total: " + changeFormat.format(dateCutTotal));
                    } 
                }
                System.out.println(changeFormat.format(dateCutTotal));
            }
        });

        add(list1, java.awt.BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.PAGE_END);
        add(topPanel, BorderLayout.PAGE_START);

        //topPanel.add(totalDisplay);
        topPanel.add(totalDisplay);

        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(jSpinner1);
        bottomPanel.add(jSpinner2);
        bottomPanel.add(button1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(600, 450);
        setResizable(false);
        setTitle("Project 2 | Maksymilian Rago");

        Font f = new Font(Font.DIALOG, Font.PLAIN, 13);
        list1.setFont(f);

    }

    private JButton button1;
    private JSpinner jSpinner1;
    private JList list1;
    public static void main(String[] args) {

        //Sample data to be passed, and tested
        list.add(new RealEstateSale("PL", 122.80, new Date("2001/11/01")));
        list.add(new RealEstateSale("CA", 599.81, new Date("2002/05/02")));
        list.add(new RealEstateSale("DE", 566.42, new Date("2003/06/13")));
        list.add(new RealEstateSale("NO", 887.52, new Date("2005/08/02")));
        list.add(new RealEstateSale("GB", 54.30, new Date("2006/10/20")));
        list.add(new RealEstateSale("ES", 88.11, new Date("2007/11/10")));
        list.add(new RealEstateSale("FR", 10.00, new Date("2008/05/06")));
        list.add(new RealEstateSale("CZ", 18.15, new Date("2009/01/13")));
        list.add(new RealEstateSale("RU", 48.78, new Date("2010/03/19")));
        list.add(new RealEstateSale("TR", 1000.87, new Date("2011/02/01")));
        list.add(new RealEstateSale("ES", 880.11, new Date("2012/11/10")));
        list.add(new RealEstateSale("FR", 101.00, new Date("2013/05/06")));
        list.add(new RealEstateSale("CZ", 188.18, new Date("2014/01/13")));
        list.add(new RealEstateSale("RU", 481.77, new Date("2015/03/19")));
        list.add(new RealEstateSale("TR", 1120.87, new Date("2016/02/01")));
        list.add(new RealEstateSale("NO", 87.52, new Date("2017/08/02")));
        list.add(new RealEstateSale("GB", 4.30, new Date("2018/10/20")));

        //initializes GUI
        ProjectFour start = new ProjectFour();
    }
}
