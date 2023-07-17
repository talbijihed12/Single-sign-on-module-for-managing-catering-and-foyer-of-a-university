package com.example.reservationmicroservice.Pdf;


import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;


import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.*;
import java.util.List;

@Service
@AllArgsConstructor
public class PdfService {
    public String createPdf(String path) throws IOException, TransformerException {
        String path2 = "C:\\Users\\labid\\OneDrive\\Bureau\\paolo\\4 SAE 7\\PIFinal\\ReservationMicroService\\src\\Pdf\\file.pdf";

        Document document = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\labid\\OneDrive\\Bureau\\paolo\\4 SAE 7\\PIFinal\\ReservationMicroService\\src\\Pdf\\file.pdf"));
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }


        Image image = null;
        try {
            image = Image.getInstance(path);
        } catch (BadElementException e) {
            throw new RuntimeException(e);
        }


        document.open();

        try {
            document.add(image);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }


        document.close();

        return path2;
    }
}
