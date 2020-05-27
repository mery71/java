package com.lacnt.clock.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.lacnt.clock.model.Category;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@Service
public class CategoryServiceImp extends JdbcDaoSupport implements CategoryService {
	@Autowired
	DataSource datasource;

	@PostConstruct
	private void intialize() {
		setDataSource(datasource);
	}

	@Override
	public List<Category> getAllCategory() {
		String sql = "SELECT * FROM category where isdelete=0";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql);

		List<Category> result = new ArrayList<Category>();
		for (Map<String, Object> row : rows) {
			Category cate = new Category();
			cate.setId_category((Integer) row.get("id_category"));
			cate.setName((String) row.get("name"));
			cate.setIsdelete((Integer) row.get("isdelete"));
			result.add(cate);
		}
		return result;

	}

	@Override
	public void addCategory(Category category) {
		String sql = "INSERT into category (name) values (?)";
		getJdbcTemplate().update(sql, new Object[] { category.getName() });
	}

	@Override
	public void updateCategory(Category category) {
		String sql = "UPDATE category SET name = ? where id_category= ?";
		getJdbcTemplate().update(sql, new Object[] { category.getName(), category.getId_category()});
	}

	@Override
	public Category findCategoryById(int id_category) {
		String sql = "SELECT *FROM category where id_category=?";
		return (Category) getJdbcTemplate().queryForObject(sql, new Object[] { id_category },
				new RowMapper<Category>() {

					@Override
					public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
						Category cate = new Category();
						cate.setId_category(rs.getInt("id_category"));
						cate.setName(rs.getString("name"));
						return cate;
					}

				});
	}

	@Override
	public void deleteCategory(int id_category) {
		String sql = "UPDATE product SET isdelete = 1 where id_category= ?";
		String sql1 = "UPDATE category SET isdelete = 1 where id_category= ?";
		getJdbcTemplate().update(sql, id_category);
		getJdbcTemplate().update(sql1, id_category);

	}
}
