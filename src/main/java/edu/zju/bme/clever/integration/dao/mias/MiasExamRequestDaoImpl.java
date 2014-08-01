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

import edu.zju.bme.clever.integration.entity.ExamRequest;
import edu.zju.bme.clever.integration.entity.mapper.mias.MiasExamRequestRowMapper;
import edu.zju.bme.clever.integration.util.DatabaseUtil;

@Service("miasExamRequestDao")
@Transactional
public class MiasExamRequestDaoImpl implements MiasExamRequestDao {

    @Resource(name="jdbcTemplate")
    private NamedParameterJdbcTemplate jt;

	@Override
	public List<ExamRequest> get(String examReqId) {
		String sqlFormat = "SELECT TOP 1 * FROM {0} WHERE {1} = :examReqId";
		String sql = MessageFormat.format(sqlFormat, 
				DatabaseUtil.getMiasDatabaseTableName(MiasExamRequestRowMapper.EXAM_REQUEST.class.getSimpleName()),
				MiasExamRequestRowMapper.EXAM_REQUEST.EXAM_REQ_ID);	
		Map<String, Object> paramters = new HashMap<String, Object>();
		paramters.put("examReqId", examReqId);
		SqlParameterSource source = new MapSqlParameterSource(paramters);
		return jt.query(sql, source, new MiasExamRequestRowMapper());
	}
	
}
