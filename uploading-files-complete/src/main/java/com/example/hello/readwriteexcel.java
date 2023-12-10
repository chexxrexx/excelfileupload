package com.example.hello;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.time.temporal.ChronoUnit;

import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import java.util.Comparator;

public class readwriteexcel {
	
	public List<Product> ReadExcel(String filePath) {
	    List<Product> products = new ArrayList<>();
	    try {
	        FileInputStream fis = new FileInputStream(filePath);
	        Workbook wb = WorkbookFactory.create(fis);
	        Sheet sheet = wb.getSheet("Sheet1");

	        int row_index = 1;
	        Row row;
	        while ((row = sheet.getRow(row_index)) != null) {
	            long RD = (long) row.getCell(0).getNumericCellValue();
	            long P1UOrder = (long) row.getCell(1).getNumericCellValue();
	            String Country = row.getCell(2).getStringCellValue();
	            long HAWB = (long) row.getCell(3).getNumericCellValue();
	            long MAWB = (long) row.getCell(4).getNumericCellValue();
	            long STT = (long) row.getCell(5).getNumericCellValue();
	            int RouteDays = (int) row.getCell(6).getNumericCellValue();
	            double ProdWgt = (double) row.getCell(7).getNumericCellValue();
	            String SmartSheet_Priority = row.getCell(8).getStringCellValue();
	            String HandedOff = row.getCell(9).getStringCellValue();
	            String NewHandOff = row.getCell(10).getStringCellValue();
	            String DBSInventory = row.getCell(11).getStringCellValue();
	            String Booked = row.getCell(12).getStringCellValue();
	            String Arrived = row.getCell(13).getStringCellValue();
	            Cell HandOffDateCell = row.getCell(14);
	            LocalDate HandOffDate = null;
	            if (HandOffDateCell.getCellType() == CellType.STRING) {
	                String HandOffDateStr = HandOffDateCell.getStringCellValue();
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy"); //parsing
	                HandOffDate = LocalDate.parse(HandOffDateStr, formatter);
	            } else if (HandOffDateCell.getCellType() == CellType.NUMERIC) {
	                HandOffDate = HandOffDateCell.getLocalDateTimeCellValue().toLocalDate();
	            }
	            Cell RefGIDateCell = row.getCell(15);
	            LocalDate RefGIDate = null;
	            if (RefGIDateCell.getCellType() == CellType.STRING) {
	                String RefGIDateStr = RefGIDateCell.getStringCellValue();
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
	                RefGIDate = LocalDate.parse(RefGIDateStr, formatter);
	            } else if (RefGIDateCell.getCellType() == CellType.NUMERIC) {
	                RefGIDate = RefGIDateCell.getLocalDateTimeCellValue().toLocalDate();
	            }
	            Cell ArrivalDateCell = row.getCell(16);
	            LocalDate ArrivalDate = null;
	            if (ArrivalDateCell.getCellType() == CellType.STRING) {
	                String ArrivalDateStr = ArrivalDateCell.getStringCellValue();
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
	                ArrivalDate = LocalDate.parse(ArrivalDateStr, formatter);
	            } else if (ArrivalDateCell.getCellType() == CellType.NUMERIC) {
	                ArrivalDate = ArrivalDateCell.getLocalDateTimeCellValue().toLocalDate();
	            }
	            Cell ETADateCell = row.getCell(17);
	            LocalDate ETADate = null;
	            if (ETADateCell.getCellType() == CellType.STRING) {
	                String ETADateStr = ETADateCell.getStringCellValue();
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
	                ETADate = LocalDate.parse(ETADateStr, formatter);
	            } else if (ETADateCell.getCellType() == CellType.NUMERIC) {
	                ETADate = ETADateCell.getLocalDateTimeCellValue().toLocalDate();
	            }
	            Cell RDDDateCell = row.getCell(18);
	            LocalDate RDDDate = null;
	            if (RDDDateCell.getCellType() == CellType.STRING) {
	                String RDDDateStr = RDDDateCell.getStringCellValue();
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
	                RDDDate = LocalDate.parse(RDDDateStr, formatter);
	            } else if (RDDDateCell.getCellType() == CellType.NUMERIC) {
	                RDDDate = RDDDateCell.getLocalDateTimeCellValue().toLocalDate();
	            }
	            Cell TargetDeliveryDateCell = row.getCell(19);
	            LocalDate TargetDeliveryDate = null;
	            if (TargetDeliveryDateCell.getCellType() == CellType.STRING) {
	                String TargetDeliveryDateStr = TargetDeliveryDateCell.getStringCellValue();
	                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy"); // parsing
	                TargetDeliveryDate = LocalDate.parse(TargetDeliveryDateStr, formatter);
	            } else if (TargetDeliveryDateCell.getCellType() == CellType.NUMERIC) {
	                TargetDeliveryDate = TargetDeliveryDateCell.getLocalDateTimeCellValue().toLocalDate();
	            }
	            
	            int daysDifference = Math.abs((int) ChronoUnit.DAYS.between(HandOffDate, TargetDeliveryDate));

	            // Check if HandOffDate and TargetDeliveryDate are less than 3 days from today
	            Product product = new Product(RD, P1UOrder, Country, HAWB, MAWB, STT, RouteDays, 
	            		ProdWgt, SmartSheet_Priority, HandedOff, NewHandOff, 
	            		DBSInventory, Booked, Arrived, HandOffDate, 
	            		RefGIDate, ArrivalDate, ETADate, RDDDate,
	            		TargetDeliveryDate, daysDifference); // creating new object
	            products.add(product); // adding product to the list of products

	            row_index++;
	        }
	        
	        Collections.sort(products, Comparator
    	            .comparing(Product::getHandOffDate).thenComparing(Product::getDaysDifference));
	        // compare and sort the products based off of dates from top priority to least

	        FileOutputStream fos = new FileOutputStream(filePath); // opening the file stream
	        wb.write(fos); // writing to the excel file
	        fis.close();
	        fos.close(); // closing the file stream
	    } 
	    catch (Exception e) {
	        if (e instanceof java.lang.IllegalStateException) {
	            throw new IllegalStateException("Wrong Variable Type Error", e);
	        } else {
	            System.out.println("ReadExcel catch block");
	            e.printStackTrace();
	        }
	    }

	    return products;
	}
	
	public void updateExcelSheet(List<Product> products, String filePath) {
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
		    try (FileInputStream fis = new FileInputStream(filePath);
		         Workbook wb = WorkbookFactory.create(fis)) {

		        Sheet sheet = wb.getSheet("Sheet1");
		        Drawing<?> drawing = sheet.createDrawingPatriarch();
                CreationHelper factory = wb.getCreationHelper();
                
                Collections.sort(products, Comparator
        	            .comparing(Product::getDaysDifference).thenComparing(product -> product.getHandOffDate()));

		        // Write the sorted products to the sheet
		        int rowIndex = 1;
		        for (Product product : products) {
		            Row row = sheet.getRow(rowIndex);
		            if (row == null) {
		                row = sheet.createRow(rowIndex);
		            }
		            row.createCell(0).setCellValue(product.getRD());
		            row.createCell(1).setCellValue(product.getP1UOrder());
		            row.createCell(2).setCellValue(product.getCountry());
		            row.createCell(3).setCellValue(product.getHAWB());
		            row.createCell(4).setCellValue(product.getMAWB());
		            row.createCell(5).setCellValue(product.getSTT());
		            row.createCell(6).setCellValue(product.getRouteDays());
		            String formattedSTT = decimalFormat.format(product.getProdWgt());
		            row.createCell(7).setCellValue(formattedSTT);
		            row.createCell(8).setCellValue(product.getSmartSheet_Priority());
		            row.createCell(9).setCellValue(product.getHandedOff());
		            row.createCell(10).setCellValue(product.getNewHandOff());
		            row.createCell(11).setCellValue(product.getDBSInventory());
		            row.createCell(12).setCellValue(product.getBooked());
		            row.createCell(13).setCellValue(product.getArrived());
		            row.createCell(14).setCellValue(product.getHandOffDate().format(DateTimeFormatter.ofPattern("MM/dd/yy")));
		            row.createCell(15).setCellValue(product.getRefGIDate().format(DateTimeFormatter.ofPattern("MM/dd/yy")));
		            if (product.getArrivalDate()!=null) {
		            	row.createCell(16).setCellValue(product.getArrivalDate().format(DateTimeFormatter.ofPattern("MM/dd/yy")));
		            }
		            if (product.getETADate()!=null) {
		            row.createCell(17).setCellValue(product.getETADate().format(DateTimeFormatter.ofPattern("MM/dd/yy")));
		            }
		            row.createCell(18).setCellValue(product.getRDD().format(DateTimeFormatter.ofPattern("MM/dd/yy")));
		            row.createCell(19).setCellValue(product.getTargetDeliveryDate().format(DateTimeFormatter.ofPattern("MM/dd/yy")));


		            int daysDifference = product.getDaysDifference();
		            if (daysDifference < 4) {
		            	Comment expressAirComment = drawing.createCellComment(factory.createClientAnchor());
		                RichTextString expressAirText = factory.createRichTextString("Explore using express air product");
		                expressAirComment.setString(expressAirText);
		                row.getCell(0).setCellComment(expressAirComment);
		            }
		            if (daysDifference >= 4 && daysDifference < 7) {
		            	Comment airStandardComment = drawing.createCellComment(factory.createClientAnchor());
		                RichTextString airStandardText = factory.createRichTextString("Explore using air standard product");
		                airStandardComment.setString(airStandardText);
		                row.getCell(0).setCellComment(airStandardComment);
		            }
		            
		            if (7 <= daysDifference && daysDifference < 10) {
		            	Comment airDeferredComment = drawing.createCellComment(factory.createClientAnchor());
		                RichTextString airDeferredText = factory.createRichTextString("Explore using air deferred product");
		                airDeferredComment.setString(airDeferredText);
		                row.getCell(0).setCellComment(airDeferredComment);
		            }
		            
		            if (10 <= daysDifference && daysDifference < 15) {
		            	Comment seaAirComment = drawing.createCellComment(factory.createClientAnchor());
		            	RichTextString seaAirText = factory.createRichTextString("Explore using sea-air product");
		            	seaAirComment.setString(seaAirText);
		            	row.getCell(0).setCellComment(seaAirComment);
		            }
		            
		            if (daysDifference >= 15) {
		            	Comment OceanComment = drawing.createCellComment(factory.createClientAnchor());
		            	RichTextString OceanText = factory.createRichTextString("Explore using ocean");
		            	OceanComment.setString(OceanText);
		            	row.getCell(0).setCellComment(OceanComment);
		            }

		            rowIndex++;
		        }

		        try (FileOutputStream fos = new FileOutputStream(filePath)) {
		            wb.write(fos);
		        }
		    } catch (Exception e) {
		        System.out.println("Error updating Excel sheet");
		        e.printStackTrace();
		    }
	}
}

