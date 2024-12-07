package FinalProject;

import java.awt.*;

import javax.swing.*;
public class MainFrame extends JFrame{
	JPanel TopPanel,LeftPanel;
	JLabel sejong,name;
	public MainFrame()
	{
		setSize(1000,800);
		setTitle("세종 증권");
		
		TopPanel = new JPanel();
		TopPanel.setBackground(new Color(195,0,47));
		TopPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		TopPanel.setPreferredSize(new Dimension(1000,80));
		
		LeftPanel = new JPanel();
		LeftPanel.setBackground(Color.WHITE);
		LeftPanel.setLayout(new BoxLayout(LeftPanel,BoxLayout.Y_AXIS));
		LeftPanel.setPreferredSize(new Dimension(200,0));
		
		
		sejong = new JLabel();
		name = new JLabel("세종 증권");
		name.setForeground(Color.white);
		Font f = new Font("맑은 고딕",Font.BOLD,20);
		
		ImageIcon icon = new ImageIcon("sejong.jpg");
		Image img = icon.getImage();
		Image resizedImg = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		ImageIcon resizedIcon = new ImageIcon(resizedImg);
		sejong.setIcon(resizedIcon);
		
		name.setFont(f);
		
		TopPanel.add(sejong);
		TopPanel.add(name);
		
		
		add(LeftPanel,BorderLayout.WEST);
		add(TopPanel,BorderLayout.NORTH);
		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainFrame();
	}

}
