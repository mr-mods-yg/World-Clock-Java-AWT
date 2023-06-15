package com.company;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class WorldClock extends WindowAdapter implements ItemListener {
    Frame f;
    Label lb;
    Label lb1;
    String zoneId = "Asia/Kolkata";
    WorldClock(){
        f = new Frame("World Clock");
        f.setSize(400,300);
        f.setVisible(true);
        f.setLayout(null);
        f.addWindowListener(this);
        Choice lst = new Choice();
        String arr[] = ZoneId.getAvailableZoneIds().toArray(new String[0]);
        Arrays.sort(arr);
        for (String i: arr){
            lst.add(i);
        }
        lst.addItemListener(this);
        lst.setBounds(100,60,150,100);
        f.add(lst);
        LocalTime obj1 = LocalTime.now();
        Font font = new Font("Courier", Font.PLAIN, 20);
        Font font1 = new Font("Courier", Font.PLAIN, 16);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
        // Adding Label 1
        lb = new Label(obj1.format(formatter));
        lb.setBounds(100,180,200,50);
        lb.setFont(font);
        f.add(lb);
        // Adding Label 2
        lb1 = new Label("Current Zone : "+zoneId);
        lb1.setBounds(70,230,300,30);
        lb1.setFont(font1);
        f.add(lb1);
//        Listener lis = new Listener();
    }
    @Override
    public void itemStateChanged(ItemEvent e){
        zoneId = e.getItem().toString();
    }
//    public void actionPerformed(ActionEvent e) {
//        zoneId = e.getActionCommand();
//    }
    @Override
    public void windowClosing(WindowEvent e) {
        f.dispose();
    }

    public static void main(String[] args) throws InterruptedException {
        WorldClock obj = new WorldClock();
        while(true){
            LocalTime obj1 = LocalTime.now(ZoneId.of(obj.zoneId));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
            obj.lb.setText(obj1.format(formatter));
            obj.lb1.setText("Current Zone : "+ obj.zoneId);
            obj.f.add(obj.lb);
            Thread.sleep(1000);
        }
    }
}
