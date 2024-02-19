package tr.ogu.edu.school.schoolapp.service;

import java.io.File;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import tr.ogu.edu.school.schoolapp.exception.FileNotCreatedException;
import tr.ogu.edu.school.schoolapp.exception.ReportTemplateNotFoundException;
import tr.ogu.edu.school.schoolapp.util.FileUtil;

@Slf4j
@Service
@AllArgsConstructor
public class PdfGeneratorService {

	private FileUtil fileUtil;

	public String generatePdf(String fileName, Map<String, Object> parameters, String templatePath) {
		File templateFile = new File(templatePath);
		if (!templateFile.exists()) {
			throw new ReportTemplateNotFoundException();
		}
		String documentFilePath = fileUtil.getFileFullPath(fileName, "pdf");
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(templatePath);
			log.info("Jasper Report şablonu derlendi.");
			JasperPrint jprint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			JasperExportManager.exportReportToPdfFile(jprint, documentFilePath);
			File f = new File(documentFilePath);
			log.info("{} kodlu doküman {} dosya yoluna kaydedildi.", fileName, f.getAbsolutePath());
			return f.getAbsolutePath();
		} catch (JRException e) {
			throw new FileNotCreatedException(e);
		}

	}

}