package tr.ogu.edu.school.schoolapp.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import tr.ogu.edu.school.schoolapp.util.FileUtil;

class PdfGeneratorServiceTest {

	private FileUtil fileUtil;

	private PdfGeneratorService pdfGeneratorService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		fileUtil = new FileUtil();
		pdfGeneratorService = new PdfGeneratorService(fileUtil);
	}

	@Test
	void whenCreatePdfTicket_thenShouldReturnPdfSavedFilePath() throws IOException {
		String templatePath = "jasper-report-templates/ticket.jrxml"; // Şablon dosyasının bulunduğu yer
		String docCode = UUID.randomUUID().toString(); // rastgele üretilen bir code

		// PDF bilet için gerekli parametreler
		Map<String, Object> map = new HashMap<>();
		map.put("verification_code", docCode); // required
		map.put("seat_line", "F"); // required
		map.put("seat_no", "25"); // required
		map.put("act_date", "8 Mart"); // required
		map.put("act_hour", "20:00"); // required
		map.put("hall_name", "Zübeyde Hanım Kültür Merkezi"); // required
		map.put("hall_address", "Cumhuriye, Dinçel Sk. No:5, Tepebaşı/Eskişehir"); // required
		map.put("image1_path", "dummy_path.png"); // optional
		map.put("image2_path", "dummy_path.png"); // optional
		map.put("image3_path", "dummy_path.png"); // optional

		pdfGeneratorService.generatePdf(docCode, map, templatePath);
	}

}
