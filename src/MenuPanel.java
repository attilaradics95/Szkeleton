import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    Game game;
    public MenuPanel(Game game){
        this.game = game;

        JPanel panel = new JPanel();

        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);

        panel.setLayout(new FlowLayout());

        JPanel map_list = new JPanel();
        map_list.setMinimumSize(new Dimension(250, 10));
        JPanel images = new JPanel();

        panel.add(map_list);
        panel.add(images);

        images.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Jelmagyarázat"), BorderFactory.createEmptyBorder(10,10,10,10)));
        images.setLayout(new BorderLayout());

        JPanel images_center = new JPanel();

        images.add(images_center, BorderLayout.CENTER);

        map_list.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Válassz pályát!"), BorderFactory.createEmptyBorder(10,200,10,200)));

        ButtonGroup mapgroup = new ButtonGroup();
        JRadioButton map1 = new JRadioButton("Map 1");
        JRadioButton map2 = new JRadioButton("Map 2");
        mapgroup.add(map1);
        mapgroup.add(map2);

        map_list.setLayout(new BorderLayout());
        JPanel rb = new JPanel();
        rb.setLayout(new GridLayout(1, 0, 100, 100));
        map_list.add(rb, BorderLayout.CENTER);
        rb.add(map1);
        rb.add(map2);

        JLabel itile, iobstacle, itarget, ihole, iswitch, ihoney, ioil, ibox, iworker, iselectedworker;

        itile = new JLabel(new ImageIcon("img/tile.jpg"));
        iobstacle = new JLabel(new ImageIcon("img/obstacle.jpg"));
        itarget = new JLabel(new ImageIcon("img/target.jpg"));
        ihole = new JLabel(new ImageIcon("img/hole.jpg"));
        iswitch = new JLabel(new ImageIcon("img/switch.jpg"));
        ihoney = new JLabel(new ImageIcon("img/honey.jpg"));
        ioil = new JLabel(new ImageIcon("img/oil.jpg"));
        ibox = new JLabel(new ImageIcon("img/box.jpg"));
        iworker = new JLabel(new ImageIcon("img/worker.jpg"));
        iselectedworker = new JLabel(new ImageIcon("img/selectedworker.jpg"));

        JLabel ttile, tobstacle, ttarget, thole, tswitch, thoney, toil, tbox, tworker, tselectedworker;

        ttile = new JLabel("Sima mező");
        tobstacle = new JLabel("Obtacle");
        ttarget = new JLabel("Célmező");
        thole = new JLabel("Lyuk");
        tswitch = new JLabel("Kapcsoló");
        thoney = new JLabel("Mézes mező");
        toil = new JLabel("Olajos mező");
        tbox = new JLabel("Láda");
        tworker = new JLabel("Munkás");
        tselectedworker = new JLabel("Aktuálisan kiválasztott munkás");

        images_center.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        images_center.setLayout(new GridLayout(0, 4, 1, 1));
        images_center.add(itile);
        images_center.add(ttile);
        images_center.add(ihoney);
        images_center.add(thoney);

        images_center.add(iobstacle);
        images_center.add(tobstacle);
        images_center.add(ioil);
        images_center.add(toil);

        images_center.add(itarget);
        images_center.add(ttarget);
        images_center.add(ibox);
        images_center.add(tbox);

        images_center.add(ihole);
        images_center.add(thole);
        images_center.add(iworker);
        images_center.add(tworker);

        images_center.add(iswitch);
        images_center.add(tswitch);
        images_center.add(iselectedworker);
        images_center.add(tselectedworker);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 0, 1, 50));
        this.add(buttons, BorderLayout.SOUTH);

        JButton start = new JButton("Játék indítása");
        JButton exit = new JButton("Kilépés");

        buttons.add(start);
        buttons.add(exit);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameWindow window = (GameWindow) SwingUtilities.getWindowAncestor(start);

                //Ha a felhasználó nem választott ki pályát, akkor értesítjük róla
                if(!map1.isSelected() && !map2.isSelected()) {
                    NoMapIsSelected noMapIsSelected = new NoMapIsSelected();
                    noMapIsSelected.setVisible(true);
                }

                //Pálya betöltése
                if(map1.isSelected()){

                    game.loadMap("map1.txt");
                    GamePanel gamePanel = new GamePanel(game.getTiles(), game.getVisitors());
                    window.setMainpanel(gamePanel);
                }
                if(map2.isSelected()){
                    game.loadMap("map2.txt");
                    GamePanel gamePanel = new GamePanel(game.getTiles(), game.getVisitors());
                    window.setMainpanel(gamePanel);
                }
            }
        });
    }
}
