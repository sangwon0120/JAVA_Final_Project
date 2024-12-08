package FinalProject;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class ChartPanelCustom extends JPanel {
    private ChartPanel chartPanel; // JFreeChart의 ChartPanel 포함

    public ChartPanelCustom(String stockName) {
        setLayout(new BorderLayout());
        chartPanel = createChartPanel(stockName); // 초기 차트 생성
        add(chartPanel, BorderLayout.CENTER);
    }

    // 차트 갱신 메서드
    public void updateChart(String stockName) {
        // 이전 차트 제거
    	System.out.println("Updating chart for: " + stockName);
        remove(chartPanel);

        // 새로운 차트 생성 및 추가
        chartPanel = createChartPanel(stockName);
        add(chartPanel, BorderLayout.CENTER);

        // 레이아웃 및 화면 갱신
        revalidate();
        repaint();
    }

    private DefaultCategoryDataset createDataset(String stockName) 
    {
    	System.out.println("Creating dataset for: " + stockName);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        switch (stockName.trim()) {
            case "AAPL":
                dataset.addValue(150, "가격", "2023-01");
                dataset.addValue(160, "가격", "2023-02");
                dataset.addValue(170, "가격", "2023-03");
                break;
            case "TSLA":
                dataset.addValue(800, "가격", "2023-01");
                dataset.addValue(850, "가격", "2023-02");
                dataset.addValue(870, "가격", "2023-03");
                break;
            case "AMZN":
                dataset.addValue(90, "가격", "2023-01");
                dataset.addValue(95, "가격", "2023-02");
                dataset.addValue(100, "가격", "2023-03");
                break;
            case "NVDA":
                dataset.addValue(300, "가격", "2023-01");
                dataset.addValue(320, "가격", "2023-02");
                dataset.addValue(350, "가격", "2023-03");
                break;
            case "META":
                dataset.addValue(150, "가격", "2023-01");
                dataset.addValue(140, "가격", "2023-02");
                dataset.addValue(145, "가격", "2023-03");
                break;
            default:
                System.out.println("Unknown stock: " + stockName); // 디버깅용 로그
                dataset.addValue(0, "가격", "No Data");
        }

        return dataset;
    }


    // 차트 생성 메서드
    private ChartPanel createChartPanel(String stockName) {
        DefaultCategoryDataset dataset = createDataset(stockName);

        // JFreeChart 생성
        JFreeChart chart = ChartFactory.createLineChart(
                stockName + " 주가 차트", // 차트 제목
                "날짜", // X축 라벨
                "가격", // Y축 라벨
                dataset // 데이터셋
        );

        return new ChartPanel(chart); // ChartPanel 반환
    }
}




