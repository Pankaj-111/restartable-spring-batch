/**
 * 
 */
package com.magicbricks.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.magicbricks.batch.model.Tpownerbatchdata;

@Component
public class OwnerDataRowMapper implements RowMapper<Tpownerbatchdata> {

	@Override
	public Tpownerbatchdata mapRow(final ResultSet resultSet, int arg1) throws SQLException {

		if (resultSet == null) {
			return null;
		}

		final Tpownerbatchdata user = new Tpownerbatchdata();
		user.setId(resultSet.getInt("obdrfnum"));
		user.setPropid(resultSet.getLong("propid"));
		user.setEmail(resultSet.getString("email"));
		user.setCreatedate(resultSet.getDate("createdate"));
		user.setMobile(resultSet.getLong("mobile"));
		user.setFname(resultSet.getString("fname"));
		user.setPtype(resultSet.getShort("ptype"));
		user.setCategory(resultSet.getString("category"));
		user.setOid(resultSet.getLong("oid"));
		user.setCity(resultSet.getInt("city"));
		user.setLocality(resultSet.getInt("locality"));
		if (resultSet.getString("bedroom") != null) {
			user.setBedroom(resultSet.getInt("bedroom"));
		}
		user.setMobile(resultSet.getLong("mobile"));
		user.setPmtsource(resultSet.getString("PMT_SOURCE"));
		return user;
	}
}
