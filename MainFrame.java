package FinalProject;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

class ButtonCellEditor extends DefaultCellEditor {
    private JButton button;
    private String label;
    private ChartPanelCustom chart;

    public ButtonCellEditor(ChartPanelCustom chart) {
        super(new JCheckBox());
        this.chart = chart;

        // 버튼 생성 및 기본 동작 설정
        button = new JButton();
        button.setOpaque(true);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (value instanceof JButton) {
            button = (JButton) value;
            label = button.getText();
            System.out.println("Editing cell: " + label); // 디버깅용 출력
        }

        // ActionListener 강제 호출
        button.addActionListener(e -> {
            System.out.println("Button clicked: " + label); // 디버깅용 출력
            if (label != null) {
                chart.updateChart(label.trim());
                System.out.println(label.trim() + " 차트가 업데이트되었습니다.");
            }
        });

        return button;
    }

    @Override
    public Object getCellEditorValue() {
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


//------------------------------------------------------------
//메인프레임
//-----------------------------------------------------------
public class MainFrame extends JFrame{
	JPanel TopPanel,LeftPanel,Portpolio,BottomPanel;
	JLabel sejong,name;
	JButton Buy,Sell;
	ChartPanelCustom chart;
	DefaultTableModel ppModel;
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
		table.getColumn("종목명").setCellEditor(new ButtonCellEditor(chart)); // ChartPanelCustom 전달
		table.getColumn("종목명").setCellRenderer(new ButtonCellRenderer());
		



		
		
		JScrollPane scrollPane = new JScrollPane(table);
        LeftPanel.add(scrollPane, BorderLayout.CENTER);
        
		//--------------------------
        //포트폴리오
        //-------------------------
        Portpolio = new JPanel();
        Portpolio.setPreferredSize(new Dimension(250,0));
        
        String[] PortpolioData = {"종목명","평단가","보유수","수익률"};
        ppModel = new DefaultTableModel(PortpolioData,0);
        JTable pptable = new JTable(ppModel);
        
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
        BottomPanel.setBackground(new Color(255,234,155));
        BottomPanel.setPreferredSize(new Dimension(0,200));
        chart.add(BottomPanel,BorderLayout.SOUTH);
        
        MyListener BuySell = new MyListener(table,ppModel);
        Buy = new JButton("매수");
        Sell = new JButton("매도");
        Buy.addActionListener(BuySell);
        Sell.addActionListener(BuySell);
        
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
	class MyListener implements ActionListener {
	    JTable stockTable;
	    DefaultTableModel ppModel;

	    public MyListener(JTable stockTable, DefaultTableModel ppModel) {
	        this.stockTable = stockTable;
	        this.ppModel = ppModel;
	    }

	    public String getSelectedStock(JTable stockTable) {
	        int selectedRow = stockTable.getSelectedRow();
	        if (selectedRow != -1) {
	            Object value = stockTable.getValueAt(selectedRow, 0);
	            if (value instanceof JButton) {
	                return ((JButton) value).getText(); // 버튼의 텍스트 반환
	            } else {
	                return value.toString();
	            }
	        }
	        return null; // 선택되지 않음
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        String selectedStock = getSelectedStock(stockTable);

	        if (selectedStock != null) {
	            String input = JOptionPane.showInputDialog(null, 
	                (e.getSource() == Buy ? "얼마나 매수할까요?:" : "얼마나 매도할까요?:"), 
	                (e.getSource() == Buy ? "매수" : "매도"), 
	                JOptionPane.PLAIN_MESSAGE);

	            if (input != null) { // 사용자가 입력을 취소하지 않았을 때
	                try {
	                    int quantity = Integer.parseInt(input.trim());
	                    if (quantity <= 0) {
	                        JOptionPane.showMessageDialog(null, "유효한 수량을 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
	                        return;
	                    }

	                    if (e.getSource() == Buy) { // Buy 버튼 동작
	                        boolean stockExists = false;

	                        for (int i = 0; i < ppModel.getRowCount(); i++) {
	                            if (ppModel.getValueAt(i, 0).equals(selectedStock)) {
	                                int currentCount = Integer.parseInt(ppModel.getValueAt(i, 2).toString());
	                                ppModel.setValueAt(currentCount + quantity, i, 2); // 수량 증가
	                                stockExists = true;
	                                break;
	                            }
	                        }

	                        if (!stockExists) {
	                            ppModel.addRow(new Object[]{selectedStock, "100", String.valueOf(quantity), "0%"}); // 새로운 종목 추가
	                        }
	                    } else if (e.getSource() == Sell) { // Sell 버튼 동작
	                        boolean stockFound = false;

	                        for (int i = 0; i < ppModel.getRowCount(); i++) {
	                            if (ppModel.getValueAt(i, 0).equals(selectedStock)) {
	                                int currentCount = Integer.parseInt(ppModel.getValueAt(i, 2).toString());
	                                if (currentCount > quantity) {
	                                    ppModel.setValueAt(currentCount - quantity, i, 2); // 수량 감소
	                                } else if (currentCount == quantity) {
	                                    ppModel.removeRow(i); // 수량 0이면 삭제
	                                } else {
	                                    JOptionPane.showMessageDialog(null, "보유 수량이 매도하려는 수량보다 많습니다", "오류", JOptionPane.ERROR_MESSAGE);
	                                    return;
	                                }
	                                stockFound = true;
	                                break;
	                            }
	                        }

	                        if (!stockFound) {
	                            JOptionPane.showMessageDialog(null, "포트폴리오에 해당 종목이 없습니다!", "오류", JOptionPane.ERROR_MESSAGE);
	                        }
	                    }
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(null, "숫자를 입력하세요!", "오류", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "종목을 선택하세요!", "알림", JOptionPane.WARNING_MESSAGE);
	        }
	    }
	}



	//LeftPanel의 테이블에서 선택된 행의 종목정보를 가져오는 메소드
	public String getSelectedStock(JTable stockTable) {
	    int selectedRow = stockTable.getSelectedRow();
	    if (selectedRow != -1) {
	        return stockTable.getValueAt(selectedRow, 0).toString(); // 종목명 반환
	    }
	    return null; // 선택되지 않음
	}

	//버튼 생성 메소드
	public static JButton createButton(String label) {
	    JButton button = new JButton(label);
	    button.setBackground(new Color(213, 214, 210));
	    button.addActionListener(e -> {
	        System.out.println("Button created and clicked: " + label); // 디버깅 출력
	    });
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

