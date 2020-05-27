package com.lacnt.clock.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import com.lacnt.clock.model.Product;

@Service
public class ProductServiceImp extends JdbcDaoSupport implements ProductService {
	@Autowired
	DataSource datasource;

	@PostConstruct
	private void intialize() {
		setDataSource(datasource);
	}

	@Override
	public List<Product> getAllProduct() {
		String sql = "select product.id_product, product.id_category,product.name, category.name  as category,product.price, product.quantity from product inner join category on product.id_category = category.id_category where product.isdelete=0";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		List<Product> result = new ArrayList<Product>();
		for (Map<String, Object> row : rows) {
			Product product = new Product();
			product.setId_product((Integer) row.get("id_product"));
			product.setName((String) row.get("name"));
			product.setName_category((String) row.get("category"));
			product.setPrice((Integer) row.get("price"));
			product.setQuantity((Integer) row.get("quantity"));
//			product.setIsdelete((Integer) row.get("isdelete"));
			product.setId_category((Integer) row.get("id_category"));
			result.add(product);
		}
		return result;

	}

	@Override
	public Product findProductById(int id_product) {
		String sql = "SELECT *FROM product where id_product=?";
		return (Product) getJdbcTemplate().queryForObject(sql, new Object[] { id_product }, new RowMapper<Product>() {

			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product();
				product.setId_product(rs.getInt("id_product"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setIsdelete(rs.getInt("isdelete"));
				product.setId_category(rs.getInt("id_category"));
				return product;
			}

		});
	}

	@Override
	public void updateProduct(Product product) {
		String sql = "UPDATE product SET name = ?,price=?,quantity=?,id_category=? where id_product= ?";
		getJdbcTemplate().update(sql, new Object[] { product.getName(), product.getPrice(), product.getQuantity(),
				product.getId_category(), product.getId_product() });

	}

	@Override
	public void deleteProduct(int id_product) {
		String sql = "UPDATE  product SET isdelete = 1 where id_product=?";
		getJdbcTemplate().update(sql, id_product);

	}

	public static void main(String[] args) {
		// System.out.println("size " +getProduct().size());
	}

	@Override
	public void addProduct(Product product) {
		String sql = "INSERT into product (name,price,quantity,id_category) values (?,?,?,?)";
		getJdbcTemplate().update(sql, new Object[] { product.getName(), product.getPrice(), product.getQuantity(),
				product.getId_category() });

	}

	@Override
	public ByteArrayInputStream exportAllData() throws IOException {
		String[] columns = { "Name product", "Name category", "Price", "Quantity" };

		Workbook workbook = new HSSFWorkbook();
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		HSSFSheet sheet = (HSSFSheet) workbook.createSheet("products");
//		Sheet sheet=(Sheet) workbook.createSheet("product");
//		HSSFFont font = (HSSFFont) workbook.createFont();
//		font.setFontName("Times new Roman");
//		font.setBold(true);
//		font.setFontHeightInPoints((short) 14);
//		font.setColor(IndexedColors.RED.getIndex());
		Row row = sheet.createRow(0);
		for (int i = 0; i < columns.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columns[i]);
//				cell.setCellStyle(headerCellStyle);

		}

		List<Product> product = this.getAllProduct();
		int rowNum = 1;
		for (Product products : product) {
			row = sheet.createRow(rowNum++);
//				row.createCell(0).setCellValue(products.getId_product());
			row.createCell(0).setCellValue(products.getName());

			row.createCell(1).setCellValue(products.getName_category());

			row.createCell(2).setCellValue(products.getPrice());
			row.createCell(3).setCellValue(products.getQuantity());
//				row.createCell(4).setCellValue(products.getId_category());

		}
		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}

//			// Write the output to a file
//		FileOutputStream fileOut = new FileOutputStream("D:/demo/products.xls");
		workbook.write(stream);
		workbook.close();

		return new ByteArrayInputStream(stream.toByteArray());

	}

}
