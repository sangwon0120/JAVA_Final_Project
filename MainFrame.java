package FinalProject;


import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

class ButtonCellEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private boolean isPushed;
    private ChartPanelCustom chart;

    public ButtonCellEditor(ChartPanelCustom chart) {
        super(new JCheckBox());
        this.chart = chart; // ChartPanelCustom 객체 저장
        button = new JButton();
        button.setOpaque(true);

        // 버튼 클릭 이벤트 처리
        button.addActionListener(e -> {
            System.out.println("Button clicked: " + label); // 디버그용 출력
            if (label != null) {
                chart.updateChart(label); // 차트 갱신
                System.out.println(label + " 차트가 업데이트됩니다."); // 디버그용 출력
            }
            fireEditingStopped();
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value instanceof JButton) {
            button = (JButton) value;
            label = button.getText(); // 버튼 텍스트를 label에 저장
            System.out.println("Editing cell: " + label); // 디버깅 출력
        }
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        isPushed = false;
        return button;
    }
}


class ButtonCellRenderer extends JButton implements TableCellRenderer {
    public ButtonCellRenderer() {
        setOpaque(true); // 버튼을 불투명하게 설정
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof JButton) {
            JButton button = (JButton) value;
            setText(button.getText()); // 버튼 텍스트 설정
            setBackground(button.getBackground()); // 버튼 배경색 복사
        } else {
            setText(value != null ? value.toString() : ""); // 기본 텍스트 설정
        }

        // 셀 선택 여부에 따라 배경색 변경
        if (isSelected) {
            setBackground(Color.LIGHT_GRAY);
        }
        return this;
    }
}

public class MainFrame extends JFrame{
	JPanel TopPanel,LeftPanel,Portpolio,BottomPanel;
	JLabel sejong,name;
	JButton Buy,Sell;
	ChartPanelCustom chart;
	public MainFrame()
	{
		setSize(1000,800);
		setTitle("세종 증권");
		setLayout(new BorderLayout());
		//------------------------
		//위쪽패널
		//-----------------------
		TopPanel = new JPanel();
		TopPanel.setBackground(new Color(195,0,47));
		TopPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		TopPanel.setPreferredSize(new Dimension(1000,80));
		//--------------------------
        //차트 패널
        //-------------------------
        chart = new ChartPanelCustom("AAPL");
		//---------------------
		//좌측패널
		//---------------------
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
		
		JTable table = new JTable(stockData, stock) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return column == 0; // 첫 번째 열만 클릭 가능
		    }
		};

		table.setFillsViewportHeight(true);
		table.setRowHeight(30);
		table.getColumn("종목명").setCellRenderer(new ButtonCellRenderer());
		table.getColumn("종목명").setCellEditor(new ButtonCellEditor(chart)); // chart 전달


		
		
		JScrollPane scrollPane = new JScrollPane(table);
        LeftPanel.add(scrollPane, BorderLayout.CENTER);
        
		//--------------------------
        //포트폴리오
        //-------------------------
        Portpolio = new JPanel();
        Portpolio.setPreferredSize(new Dimension(250,0));
        
        String[] PortpolioData = {"종목명","평단가","보유수","수익률"};
        Object[][] ppfactor = {
        		{"1","2","3","4"},{"","","","",""},{"","","","",""}
        		,{"","","","",""},{"","","","",""},{"","","","",""}
        };
        JTable pptable = new JTable(ppfactor,PortpolioData);
        pptable.setRowHeight(30);
        pptable.setBackground(new Color(81,98,111));
        pptable.setForeground(Color.white);
        pptable.getTableHeader().setFont(new Font("맑은 고딕",Font.BOLD,15));  
        pptable.setFont(new Font("맑은 고딕",Font.PLAIN,14));
        
        
        JScrollPane PPscrollPane = new JScrollPane(pptable);
        PPscrollPane.setPreferredSize(new Dimension(230, 300)); 
        Portpolio.setLayout(new BorderLayout());
        Portpolio.add(PPscrollPane, BorderLayout.CENTER);
        //----------------------------------
        //아래 패널
        //--------------------------------
        BottomPanel = new JPanel();
        BottomPanel.setBackground(new Color(213,214,210));
        BottomPanel.setPreferredSize(new Dimension(0,200));
        chart.add(BottomPanel,BorderLayout.SOUTH);
        Buy = new JButton("매수");
        Sell = new JButton("매도");
        
        BottomPanel.add(Buy);
        BottomPanel.add(Sell);
        
        
        
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
		
		
		add(chart,BorderLayout.CENTER);
		add(Portpolio,BorderLayout.EAST);
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
	        return button;
	 }
	// 버튼 클릭 리스너
	 private ActionListener createStockButtonListener(String stockName) 
	 {
		 return e -> chart.updateChart(stockName); // 차트 업데이트 호출
	 }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainFrame();
	}

}

