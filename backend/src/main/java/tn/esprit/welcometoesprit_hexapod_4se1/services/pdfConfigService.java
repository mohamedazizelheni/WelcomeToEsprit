package tn.esprit.welcometoesprit_hexapod_4se1.services;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.chart.Pie3DChartBuilder;
import net.sf.dynamicreports.report.builder.chart.PieChartBuilder;
import net.sf.dynamicreports.report.builder.chart.XyAreaChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.ImageBuilder;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.welcometoesprit_hexapod_4se1.entities.Ads;
import tn.esprit.welcometoesprit_hexapod_4se1.repositories.AdsRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

@Service
public class pdfConfigService {

    @Autowired
    AdsRepository adsRepository;

    String path = "C:\\Users\\AMENI\\Desktop\\pdf";
    public void print( String namepdf ) throws DRException, JRException {
        List<Ads> adsList = new ArrayList<>();
        adsList = (List<Ads>) adsRepository.findAll();
        Map<String,Long> map = new HashMap<>();
        for(Ads ads: adsList){
            map.put(ads.getSocity(),adsRepository.countBySocity(ads.getSocity()));
        }
        JasperReportBuilder reportBuilder = DynamicReports.report();
        StyleBuilder chartStyle = DynamicReports.stl.style()
                .setLeftPadding(2)
                .setTopPadding(12);
        FontBuilder boldFont = stl.fontArialBold().setFontSize(25);
        TextColumnBuilder<String> itemColumn = col.column("Item", "item", type.stringType());
        TextColumnBuilder<BigDecimal> unitPriceColumn = col.column("Unit price", "unitprice", type.bigDecimalType());
        /***************************** pie chart ************************************/
        PieChartBuilder pieChartBuilder = cht.pieChart()
                .setTitle("ADS  REPORT")
                .setTitleFont(boldFont)
                .setStyle(chartStyle)
                .setKey(itemColumn)
                .series(
                        cht.serie(unitPriceColumn));
        JasperReportBuilder subReport4 = DynamicReports.report();
        subReport4.setDataSource(createSubDataSource2(map));
        subReport4.addSummary(pieChartBuilder);
        ComponentBuilder<?, ?> subReport4Component = createSubReportComponent(subReport4);
        reportBuilder.addSummary(subReport4Component);
        DynamicReports.stl.style()
                .setLeftPadding(2)
                .setTopPadding(2);
        stl.fontArialBold().setFontSize(12);
        TextColumnBuilder<Integer> xColumn = col.column("X", "x", type.integerType());
        TextColumnBuilder<Integer> y1Column = col.column("Y1", "y1", type.integerType());
        TextColumnBuilder<Integer> y2Column = col.column("Y2", "y2", type.integerType());
        XyAreaChartBuilder xyAreaChartBuilder = cht.xyAreaChart()
                .setTitle("XY")
                .setTitleFont(boldFont)
                .setStyle(chartStyle)
                .setXValue(xColumn)
                .series(
                        cht.xySerie(y1Column), cht.xySerie(y2Column))
                .setXAxisFormat(
                        cht.axisFormat().setLabel("X"))
                .setYAxisFormat(
                        cht.axisFormat().setLabel("Y"));

        JasperReportBuilder subReport3 = DynamicReports.report();
        subReport3.setDataSource(createDataSourceXYAreaChartReport());
        ComponentBuilder<?, ?> subReport3Component = createSubReportComponent(subReport3);
        /******************************* image from front end  **********************************/
        byte[] decodedBytes = Base64.getDecoder().decode("");
        InputStream inputStream = new ByteArrayInputStream(decodedBytes);
        /***************************** image image from desktop ***************************************/
       /* ImageBuilder image = cmp.image("C:\\Users\\AMENI\\Desktop\\pdf\\esprit.JPG")
                .setFixedDimension(200,300)
                .setStyle(stl.style().setLeftPadding(20));
        reportBuilder.addColumnHeader(image);*/
        /**************************************************************************/
        reportBuilder.addSummary(subReport3Component);
        reportBuilder.build();
        JasperReportBuilder jasperReportBuilder = reportBuilder;
        jasperReportBuilder.build();
        jasperReportBuilder.toJasperPrint();
        JasperPrint jasperPrint = jasperReportBuilder.toJasperPrint();
        JasperExportManager.exportReportToPdfFile(jasperPrint, path + "//"+namepdf);
    }
    public void coutSociety(){
        List<Ads> adsList = new ArrayList<>();
        adsList = (List<Ads>) adsRepository.findAll();
        Map<String,Long> map = new HashMap<>();
        for(Ads ads: adsList){
            map.put(ads.getSocity(),adsRepository.countBySocity(ads.getSocity()));
        }


    }
    private static DRDataSource createSubDataSource2(Map<String,Long> map) {
        DRDataSource dataSource = new DRDataSource("item", "quantity", "unitprice");

        map.entrySet().stream()
                // ... some other Stream processings
                .forEach(e -> dataSource.add(e.getKey(), e.getValue(), new BigDecimal(500)) );
        return dataSource;
    }


    private static ComponentBuilder<?, ?> createSubReportComponent(JasperReportBuilder subReport) {
        SubreportBuilder subreport = DynamicReports.cmp.subreport(subReport);
        subreport.setDataSource(subReport.getDataSource());
        return subreport;
    }


    private JRDataSource createDataSourceXYAreaChartReport() {
        DRDataSource dataSource = new DRDataSource("x", "y1", "y2");
        for (int i = 0; i < 20; i++) {
            dataSource.add(i, (int) (Math.random() * 10), (int) (Math.random() * 10));
        }
        return dataSource;
    }
}
