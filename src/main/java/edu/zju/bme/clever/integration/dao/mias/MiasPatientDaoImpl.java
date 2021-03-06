package edu.zju.bme.clever.integration.dao.mias;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.zju.bme.clever.integration.entity.Patient;
import edu.zju.bme.clever.integration.entity.mapper.mias.MiasPatientRowMapper;
import edu.zju.bme.clever.integration.util.DatabaseUtil;

@Service("miasPatientDao")
@Transactional
public class MiasPatientDaoImpl implements MiasPatientDao {

    @Resource(name="jdbcTemplate")
    private NamedParameterJdbcTemplate jt;

	@Override
	public List<Patient> get(String patientId) {
		String sqlFormat = "SELECT TOP 1 * FROM {0} WHERE {1} = :patientId";
		String sql = MessageFormat.format(sqlFormat, 
				DatabaseUtil.getMiasDatabaseTableName(MiasPatientRowMapper.MASTER_PATIENT_INDEX.class.getSimpleName()),
				MiasPatientRowMapper.MASTER_PATIENT_INDEX.PATIENT_ID);	
		Map<String, Object> paramters = new HashMap<String, Object>();
		paramters.put("patientId", patientId);
		SqlParameterSource source = new MapSqlParameterSource(paramters);
		return jt.query(sql, source, new MiasPatientRowMapper());
	}

	@Override
	public List<Patient> get(int serialNo) {
		String sqlFormat = "SELECT TOP 1 * FROM {0} WHERE {1} = :serialNo";
		String sql = MessageFormat.format(sqlFormat, 
				DatabaseUtil.getMiasDatabaseTableName(MiasPatientRowMapper.MASTER_PATIENT_INDEX.class.getSimpleName()),
				MiasPatientRowMapper.MASTER_PATIENT_INDEX.SERIAL_NO);	
		Map<String, Object> paramters = new HashMap<String, Object>();
		paramters.put("serialNo", serialNo);
		SqlParameterSource source = new MapSqlParameterSource(paramters);
		return jt.query(sql, source, new MiasPatientRowMapper());
	}
	
}
