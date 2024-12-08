package FinalProject;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
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
		String[] stock = {"종목명"};
		Object[][] stockData = {
				{createButton("AAPL")},{createButton("MCST")},
				{createButton("AMZN")},{createButton("TSLA")},
				{createButton("NVDA")},{createButton("GOOGL")},
				{createButton("META")},{createButton("TSMC")}
				,{createButton("QQQ")},{createButton("QLD")},
				{createButton("TQQQ")},{createButton("AMD")},
				{createButton("SSO")},{createButton("SPY")},
				{createButton("AVGO")},{createButton("QCOM")},
				{createButton("ADBE")},{createButton("CPNG")}
				,{createButton("TSLL")},{createButton("TSLZ")},
				{createButton("TSLT")},{createButton("SOXL")},
				{createButton("NVDL")}
		};
		
		JTable table = new JTable(stockData, stock) 
		{
            @Override
            public boolean isCellEditable(int row, int column) 
            {
                return false; // 셀 편집 방지
            }
        };
        
		table.setFillsViewportHeight(true);
		table.setRowHeight(30);
		table.getColumn("종목명").setCellRenderer(new ButtonCellRenderer());
		table.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane(table);
        LeftPanel.add(scrollPane, BorderLayout.CENTER);
		
		sejong = new JLabel();
		name = new JLabel("세종 증권");
		name.setForeground(Color.white);
		Font f = new Font("맑은 고딕",Font.BOLD,20);
		
		ImageIcon icon = new ImageIcon("sejong.png");
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
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	//버튼 생성 메소드
	 public static JButton createButton(String label) 
	 {
	        JButton button = new JButton(label);
	        button.setBackground(new Color(213,214,210));
	        button.addActionListener(e -> System.out.println(label + " 버튼 클릭됨!"));
	        return button;
	 }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainFrame();
	}

}
// 버튼 렌더러 클래스 
class ButtonCellRenderer implements TableCellRenderer
{
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) 
    {
        if (value instanceof JButton)
        {
            return (JButton) value; // 버튼 렌더링
        }
        return new JLabel(value != null ? value.toString() : "");
    }
}