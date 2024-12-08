package FinalProject;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class ChartPanelCustom extends JPanel 
{
    private ChartPanel chartPanel; // JFreeChart의 ChartPanel 포함

    public ChartPanelCustom(String stockName) 
    {
        setLayout(new BorderLayout());
        chartPanel = createChartPanel(stockName); // 초기 차트 생성
        add(chartPanel, BorderLayout.CENTER);
    }

    // 차트 갱신 메서드
    public void updateChart(String stockName) 
    {
        remove(chartPanel); // 기존 차트 제거
        chartPanel = createChartPanel(stockName.trim());
        add(chartPanel, BorderLayout.CENTER);

        revalidate(); // 레이아웃 갱신
        repaint(); // 화면 갱신
        System.out.println("Chart updated for: " + stockName.trim()); // 갱신 확인
    }

    private DefaultCategoryDataset createDataset(String stockName) 
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        switch (stockName.trim()) 
        {
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
    private ChartPanel createChartPanel(String stockName) 
    {
        DefaultCategoryDataset dataset = createDataset(stockName);

        // JFreeChart 생성
        JFreeChart chart = ChartFactory.createLineChart(
                stockName + " 주가 차트", // 차트 제목
                "날짜", // X축 라벨
                "가격", // Y축 라벨
                dataset // 데이터셋
        );
     // 폰트 설정
        Font titleFont = new Font("맑은 고딕", Font.BOLD, 18); // 제목 폰트
        Font axisFont = new Font("맑은 고딕", Font.PLAIN, 14); // 축 제목 폰트
        Font tickFont = new Font("맑은 고딕", Font.PLAIN, 12); // 축 값 폰트

        // 차트 제목에 폰트 적용
        chart.getTitle().setFont(titleFont);

        // X축, Y축에 폰트 적용
        chart.getCategoryPlot().getDomainAxis().setLabelFont(axisFont); // X축 제목
        chart.getCategoryPlot().getDomainAxis().setTickLabelFont(tickFont); // X축 값
        chart.getCategoryPlot().getRangeAxis().setLabelFont(axisFont); // Y축 제목
        chart.getCategoryPlot().getRangeAxis().setTickLabelFont(tickFont); // Y축 값
        

        return new ChartPanel(chart); // ChartPanel 반환
    }
}




