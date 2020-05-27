//package com.lacnt.clock.service;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.List;
//
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.IndexedColors;
//import org.apache.poi.ss.usermodel.Row;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.lacnt.clock.model.Product;
//@Service
//public class CreateExcel {
//	
//
//
//
//
//	private static String[] columns = { "id_product","Name product", "Name category", "Price", "Quantity"," id_category" };
//private static List<Product> product= new ArrayList<Product>();
//	
//
////	private static HSSFCellStyle createStyleForTile(HSSFWorkbook workbook) {
////		HSSFFont font = workbook.createFont();
////		font.setBold(true);
////		HSSFCellStyle style = workbook.createCellStyle();
////		style.setFont(font);
////
////		return style;
////
////	}
//	@Autowired
//	static
//	 ProductServiceImp productServiceImp;
//	public static void main(String[] args) throws IOException {
//		
//		
//// List<Product> product = ProductServiceImp.getAllProduct();
////		product.add(new Product(1,"s","s",2,2,1,1));
//		HSSFWorkbook workbook = new HSSFWorkbook();
//		HSSFSheet sheet = workbook.createSheet("Product");
//		
////	List<Product> list = productServiceImp.getAllProduct();
//		HSSFFont font = workbook.createFont();
//		font.setFontName("Times new Roman");
//		font.setBold(true);
//		font.setFontHeightInPoints((short) 14);
//		font.setColor(IndexedColors.RED.getIndex());
//
//		// Create a CellStyle with the font
//		CellStyle headerCellStyle = workbook.createCellStyle();
//		headerCellStyle.setFont(font);
//
//		// Create a Row
//		Row headerRow = sheet.createRow(0);
//
//		// Create cells
//		for (int i = 0; i < columns.length; i++) {
//			Cell cell = headerRow.createCell(i);
//			cell.setCellValue(columns[i]);
//			cell.setCellStyle(headerCellStyle);
//
//		}
//		int rowNum = 1;
//		for (Product product : list) {
//			Row row = sheet.createRow(rowNum++);
//			row.createCell(0).setCellValue(product.getId_product());
//			row.createCell(1).setCellValue(product.getName());
//
//			row.createCell(2).setCellValue(product.getName_category());
//
//			row.createCell(3).setCellValue(product.getPrice());
//			row.createCell(4).setCellValue(product.getQuantity());
//			row.createCell(5).setCellValue(product.getId_category());
//
//		}
//		for (int i = 0; i < columns.length; i++) {
//			sheet.autoSizeColumn(i);
//		}
//
//		// Write the output to a file
//		FileOutputStream fileOut = new FileOutputStream("D:/demo/product.xls");
//		workbook.write(fileOut);
//		System.out.println("Created file" + fileOut);
//		fileOut.close();
//
//		// Closing the workbook
//		workbook.close();
//
//	}
//}
