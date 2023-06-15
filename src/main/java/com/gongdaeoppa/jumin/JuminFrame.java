package com.gongdaeoppa.jumin;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFrame;

public class JuminFrame extends JFrame {

    private Dimension dimen, dimen1;
    private int xpos, ypos;

    private Choice year_ch = new Choice();
    private Choice month_ch = new Choice();
    private Choice day_ch = new Choice();
    private Choice gender_ch = new Choice();
    private Choice make_num_ch = new Choice();

    private Button btn = new Button("확인");
    private Button btn1 = new Button("지우기");

    private BorderLayout bl = new BorderLayout(2, 2);

    private Panel p = new Panel();
    private GridBagLayout gbl  = new GridBagLayout();

    private TextArea ta = new TextArea();

    private Calendar now = Calendar.getInstance();
    private String tmp_now = new SimpleDateFormat("yyyyMMdd").format(now.getTime());

    private String tmp_year, tmp_month, tmp_day, tmp_ymd;
    private int tmp_gender;


    public JuminFrame(String title){

        super(title);

        this.setSize(550, 800);

        dimen = Toolkit.getDefaultToolkit().getScreenSize();
        dimen1 = this.getSize();
        xpos = (int)(dimen.getWidth() / 2 - dimen1.getWidth() / 2);
        ypos = (int)(dimen.getHeight() / 2 - dimen1.getHeight() / 2);
        this.setLocation(xpos, ypos);

        this.setResizable(false);

        this.init();
        this.start();

        this.setVisible(true);
    }


    private void init() {

        this.setLayout(bl);


        for(int i = now.get(Calendar.YEAR); i >= 1900; i--)
            year_ch.add(String.valueOf(i));
        year_ch.select(String.valueOf(now.get(Calendar.YEAR)));

        for(int i = 1; i <= 12; i++)
            month_ch.add(String.valueOf(i));
        month_ch.select(String.valueOf(now.get(Calendar.MONTH) + 1));

        for(int i = 1; i <= 31; i++)
            day_ch.add(String.valueOf(i));
        day_ch.select(String.valueOf(now.get(Calendar.DAY_OF_MONTH)));

        gender_ch.add("남성");
        gender_ch.add("여성");

        for(int i = 500; i > 0; i -= 10)
            make_num_ch.add(String.valueOf(i));
        make_num_ch.select("20");

        p.setLayout(gbl);
        p.add(year_ch);
        p.add(month_ch);
        p.add(day_ch);
        p.add(gender_ch);
        p.add(make_num_ch);
        p.add(btn);
        p.add(btn1);
        this.add(p, BorderLayout.NORTH);

        ta.setFont(new Font("Arial Black", Font.BOLD, 50));
        ta.setEditable(false);
        this.add(ta, BorderLayout.CENTER);
    }


    private void start() {

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        btn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){

                tmp_year = year_ch.getSelectedItem().substring(2);
                tmp_month = (month_ch.getSelectedItem().length() == 2 ? month_ch.getSelectedItem() : "0" + month_ch.getSelectedItem());
                tmp_day = (day_ch.getSelectedItem().length() == 2 ? day_ch.getSelectedItem() : "0" + day_ch.getSelectedItem());

                tmp_ymd = year_ch.getSelectedItem() + "" + tmp_month + "" + tmp_day;
                if (Integer.parseInt(tmp_ymd) > Integer.parseInt(tmp_now)){
                    System.out.println(Math.random());
                }

                if (Integer.parseInt(year_ch.getSelectedItem()) >= 2000)
                    tmp_gender = (gender_ch.getSelectedItem() == "여성" ? 4 : 3);
                else
                    tmp_gender = (gender_ch.getSelectedItem() == "여성" ? 2 : 1);

                ta.setText(new JuminCheck(tmp_ymd.substring(2) + tmp_gender, Integer.parseInt(make_num_ch.getSelectedItem())).result());
            }
        });

        btn1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                ta.setText("");
            }
        });
    }

}