package com.example.restorationmicroservice.Services;

import com.example.restorationmicroservice.Entity.Meal;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import com.lowagie.text.Document;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {
    public void generateToMeal(List<Meal> mealList, HttpServletResponse response) throws DocumentException, IOException {
        // Creating the Object of Document
        Document document = new Document(PageSize.A4);
        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, response.getOutputStream());
        // Opening the created document to change it
        document.open();
        // Creating font
        // Setting font style and size
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);
        // Creating paragraph
        Paragraph paragraph1 = new Paragraph("List of meals", fontTiltle);
        // Aligning the paragraph in the document
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        // Adding the created paragraph in the document
        document.add(paragraph1);
        // Creating a table of the 4 columns
        PdfPTable table = new PdfPTable(4);
        // Setting width of the table, its columns and spacing
        table.setWidthPercentage(100f);
        table.setWidths(new int[] {3,3,3,3});
        table.setSpacingBefore(5);
        // Create Table Cells for the table header
        PdfPCell cell = new PdfPCell();
        // Setting the background color and padding of the table cell
        cell.setBackgroundColor(CMYKColor.orange);
        cell.setPadding(5);
        // Creating font
        // Setting font style and size

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);

        // Adding Cell to table
        cell.setPhrase(new Phrase("name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("datestart", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("typeCategory", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("voteCount", font));
        table.addCell(cell);

        for (Meal meal: mealList) {
            table.addCell(String.valueOf(meal.getName()));
            table.addCell(String.valueOf(meal.getDatestart()));
            table.addCell(String.valueOf(meal.getTypeCategory()));
            table.addCell(String.valueOf(meal.getVoteCount()));
        }

        // Adding the created table to the document
        document.add(table);
        // Closing the document
         document.close();
    }


}
