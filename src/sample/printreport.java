package sample;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

public class printreport extends JFrame {
    public void showReport () throws Exception{
        OracleDataSource ods = new OracleDataSource();
        ods.setUser("amrproj");
        ods.setPassword("123456");
        ods.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
        Connection con = ods.getConnection();
        InputStream input = new FileInputStream(new File("C:\\Users\\Msys\\IdeaProjects\\Database-Project\\src\\sample\\policy.jrxml"));
        JasperDesign jd = JRXmlLoader.load(input);
        JasperReport jr = JasperCompileManager.compileReport(jd);
        JasperPrint jp = JasperFillManager.fillReport(jr, null, con);
        JFrame frame=new JFrame("report");
        frame.getContentPane().add(new JRViewer(jp));
        frame.pack();
        frame.setSize(1000,1000);
        frame.setVisible(true);
    }

}
