package dgtic.core.util;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import dgtic.core.model.entity.Reporte;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import java.awt.*;
import java.util.List;
import java.util.Map;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;

@Component
public class ReportesPdf extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model,
                                    Document document,
                                    PdfWriter writer,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        response.setHeader("Content-Disposition", "inline; filename=reportes.pdf");

        List<Reporte> datos = (List<Reporte>) model.get("datos");

        Font tituloFont =
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Paragraph titulo = new Paragraph(
                "SCGR - Sistema de Control y Generación de Reportes",
                tituloFont
        );
        titulo.setAlignment(Element.ALIGN_CENTER);
        titulo.setSpacingAfter(15f);
        document.add(titulo);

        PdfPTable tabla = new PdfPTable(6);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(10f);

        PdfPCell celda = new PdfPCell(new Phrase("ID"));
        celda.setBackgroundColor(new Color(167, 218, 255));
        celda.setPadding(8f);
        tabla.addCell(celda);

        PdfPCell celda2 = new PdfPCell(new Phrase("Nombre"));
        celda2.setBackgroundColor(new Color(167, 218, 255));
        celda2.setPadding(8f);
        tabla.addCell(celda2);

        PdfPCell celda3 = new PdfPCell(new Phrase("Descripción"));
        celda3.setBackgroundColor(new Color(167, 218, 255));
        celda3.setPadding(8f);
        tabla.addCell(celda3);

        PdfPCell celda4 = new PdfPCell(new Phrase("Usuario"));
        celda4.setBackgroundColor(new Color(167, 218, 255));
        celda4.setPadding(8f);
        tabla.addCell(celda4);

        PdfPCell celda5 = new PdfPCell(new Phrase("Estatus"));
        celda5.setBackgroundColor(new Color(167, 218, 255));
        celda5.setPadding(8f);
        tabla.addCell(celda5);

        PdfPCell celda6 = new PdfPCell(new Phrase("Tipo"));
        celda6.setBackgroundColor(new Color(167, 218, 255));
        celda6.setPadding(8f);
        tabla.addCell(celda6);

        for (Reporte r : datos) {
            tabla.addCell(String.valueOf(r.getIdReporte()));
            tabla.addCell(r.getNombre());
            tabla.addCell(r.getDescripcion());
            tabla.addCell(r.getUsuario().getNombreCompleto());
            tabla.addCell(r.getEstatusReporte().getNombre());
            tabla.addCell(r.getTipoReporte().getNombre());
        }

        document.add(tabla);
    }
}