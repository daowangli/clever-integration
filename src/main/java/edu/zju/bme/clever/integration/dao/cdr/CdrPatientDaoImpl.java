package edu.zju.bme.clever.integration.dao.cdr;

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
import edu.zju.bme.clever.integration.entity.mapper.cdr.CdrPatientRowMapper;
import edu.zju.bme.clever.integration.util.CdrCache;
import edu.zju.bme.clever.integration.util.DatabaseUtil;
import edu.zju.bme.clever.integration.util.RowMapperUtil;

@Service("cdrPatientDao")
@Transactional
public class CdrPatientDaoImpl implements CdrPatientDao {

    @Resource(name="jdbcTemplate")
    private NamedParameterJdbcTemplate jt;

	@Override
	public List<Patient> get(String patientId) {
		String sqlFormat = "SELECT TOP 1 * FROM {0} WHERE {1} = :patientId";
		String sql = MessageFormat.format(sqlFormat, 
				DatabaseUtil.getCdrDatabaseTableName(CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.class.getSimpleName()),
				CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.patient_id);		
		Map<String, Object> paramters = new HashMap<String, Object>();
		paramters.put("patientId", patientId);
		SqlParameterSource source = new MapSqlParameterSource(paramters);
		List<Patient> patients = jt.query(sql, source, new CdrPatientRowMapper());
		patients.forEach(p -> {
			Patient k = new Patient();
			k.setPatientId(patientId);
			CdrCache.INSTANCE.put(Patient.class, k.hashCode(), p);
		});
		return patients;
	}

	@Override
	public List<Patient> get(int serialNo) {
		String sqlFormat = "SELECT TOP 1 * FROM {0} WHERE {1} = :serialNo";
		String sql = MessageFormat.format(sqlFormat, 
				DatabaseUtil.getCdrDatabaseTableName(CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.class.getSimpleName()),
				CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.serial_no);		
		Map<String, Object> paramters = new HashMap<String, Object>();
		paramters.put("serialNo", serialNo);
		SqlParameterSource source = new MapSqlParameterSource(paramters);
		List<Patient> patients = jt.query(sql, source, new CdrPatientRowMapper());
		patients.forEach(p -> {
			Patient k = new Patient();
			k.setSerialNo(serialNo);
			CdrCache.INSTANCE.put(Patient.class, k.hashCode(), p);
		});
		return patients;
	}

	@Override
	public int getCount(String patientId) {
		String sqlFormat = "SELECT COUNT(*) FROM {0} WHERE {1} = :patientId";
		String sql = MessageFormat.format(sqlFormat, 				
				DatabaseUtil.getCdrDatabaseTableName(CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.class.getSimpleName()),
				CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.patient_id);	
		Map<String, Object> paramters = new HashMap<String, Object>();
		paramters.put("patientId", patientId);
		SqlParameterSource source = new MapSqlParameterSource(paramters);
		return jt.queryForObject(sql, source, Integer.class);
	}

	@Override
	public int save(Patient p) {
		String sql = "";
		SqlParameterSource source = new MapSqlParameterSource(this.putParameters(p));
		if (this.getCount(p.getPatientId()) <= 0) {
			String sqlFormat = "INSERT INTO {0} VALUES("
					+ ":patient_id, "
					+ ":name, "
					+ ":name_phonetic, "
					+ ":gender, "
					+ ":date_of_birth, "
					+ ":birth_place, "
					+ ":nationality, "
					+ ":ethnic_group, "
					+ ":marital_status, "
					+ ":career, "
					+ ":degree, "
					+ ":blood_type_abo, "
					+ ":blood_type_rh, "
					+ ":identity_card_no, "
					+ ":household_no, "
					+ ":passport_no, "
					+ ":military_id, "
					+ ":health_insurance_id, "
					+ ":health_card_no, "
					+ ":email, "
					+ ":mailing_address, "
					+ ":zip_code, "
					+ ":phone_no_mobile, "
					+ ":phone_no_home, "
					+ ":phone_no_business, "
					+ ":death_indicator, "
					+ ":death_time, "
					+ ":created_date, "
					+ ":created_by, "
					+ ":last_modify_date, "
					+ ":last_modified_by, "
					+ ":voided_indicator, "
					+ ":voided_date, "
					+ ":voided_by, "
					+ ":ref_patient_id, "
					+ ":inpatientno, "
					+ ":outpatientno, "
					+ ":emergencyno, "
					+ ":patient_healthcare_type_code, "
					+ ":patient_healthcare_type_name, "
					+ ":patient_healthcare_property_code, "
					+ ":patient_healthcare_property_name, "
					+ ":serial_no, "
					+ ":_uid_value)";		
			sql = MessageFormat.format(sqlFormat, 				
					DatabaseUtil.getCdrDatabaseTableName(CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.class.getSimpleName()));	
		} else {
			String sqlFormat = "UPDATE {0} SET "
					+ "{1} = :_uid_value, "
					+ "{2} = :name, "
					+ "{3} = :name_phonetic, "
					+ "{4} = :gender, "
					+ "{5} = :date_of_birth, "
					+ "{6} = :birth_place, "
					+ "{7} = :nationality, "
					+ "{8} = :ethnic_group, "
					+ "{9} = :marital_status, "
					+ "{10} = :career, "
					+ "{11} = :degree, "
					+ "{12} = :blood_type_abo, "
					+ "{13} = :blood_type_rh, "
					+ "{14} = :identity_card_no, "
					+ "{15} = :household_no, "
					+ "{16} = :passport_no, "
					+ "{17} = :military_id, "
					+ "{18} = :health_insurance_id, "
					+ "{19} = :health_card_no, "
					+ "{20} = :email, "
					+ "{21} = :mailing_address, "
					+ "{22} = :zip_code, "
					+ "{23} = :phone_no_mobile, "
					+ "{24} = :phone_no_home, "
					+ "{25} = :phone_no_business, "
					+ "{26} = :death_indicator, "
					+ "{27} = :death_time, "
					+ "{28} = :created_date, "
					+ "{29} = :created_by, "
					+ "{30} = :last_modify_date, "
					+ "{31} = :last_modified_by, "
					+ "{32} = :voided_indicator, "
					+ "{33} = :voided_date, "
					+ "{34} = :voided_by, "
					+ "{35} = :ref_patient_id, "
					+ "{36} = :inpatientno, "
					+ "{37} = :outpatientno, "
					+ "{38} = :emergencyno, "
					+ "{39} = :patient_healthcare_type_code, "
					+ "{40} = :patient_healthcare_type_name, "
					+ "{41} = :patient_healthcare_property_code, "
					+ "{42} = :patient_healthcare_property_name, "
					+ "{43} = :serial_no WHERE {44} = :patient_id";		
			sql = MessageFormat.format(sqlFormat, 				
					DatabaseUtil.getCdrDatabaseTableName(CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.class.getSimpleName()),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient._uid_value.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.name.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.name_phonetic.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.gender.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.date_of_birth.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.birth_place.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.nationality.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.ethnic_group.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.marital_status.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.career.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.degree.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.blood_type_abo.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.blood_type_rh.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.identity_card_no.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.household_no.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.passport_no.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.military_id.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.health_insurance_id.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.health_card_no.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.email.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.mailing_address.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.zip_code.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.phone_no_mobile.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.phone_no_home.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.phone_no_business.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.death_indicator.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.death_time.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.created_date.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.created_by.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.last_modify_date.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.last_modified_by.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.voided_indicator.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.voided_date.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.voided_by.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.ref_patient_id.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.inpatientno.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.outpatientno.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.emergencyno.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.patient_healthcare_type_code.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.patient_healthcare_type_name.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.patient_healthcare_property_code.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.patient_healthcare_property_name.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.serial_no.toString(),
					CdrPatientRowMapper.openEHR_DEMOGRAPHIC_PERSON_patient.patient_id.toString());	
		}
		return jt.update(sql, source);		
	}
	
	private Map<String, Object> putParameters(Patient p) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("patient_id", p.getPatientId());
		parameters.put("name", p.getName());
		parameters.put("name_phonetic", p.getNamePhonetic());
		parameters.put("gender", p.getGender());
		parameters.put("date_of_birth", RowMapperUtil.getDateTimeString(p.getDateOfBirth()));
		parameters.put("birth_place", p.getBirthPlace());
		parameters.put("nationality", p.getNationality());
		parameters.put("ethnic_group", p.getEthnicGroup());
		parameters.put("marital_status", p.getMaritalStatus());
		parameters.put("career", p.getCareer());
		parameters.put("degree", p.getDegree());
		parameters.put("blood_type_abo", p.getBloodTypeAbo());
		parameters.put("blood_type_rh", p.getBloodTypeRh());
		parameters.put("identity_card_no", p.getIdentityCardNo());
		parameters.put("household_no", p.getHouseholdNo());
		parameters.put("passport_no", p.getPassportId());
		parameters.put("military_id", p.getMilitaryId());
		parameters.put("health_insurance_id", p.getHealthInsuranceId());
		parameters.put("health_card_no", p.getHealthCardNo());
		parameters.put("email", p.getEmail());
		parameters.put("mailing_address", p.getMailingAddress());
		parameters.put("zip_code", p.getZipCode());
		parameters.put("phone_no_mobile", p.getPhoneNoMobile());
		parameters.put("phone_no_home", p.getPhoneNoHome());
		parameters.put("phone_no_business", p.getPhoneNoBusiness());
		parameters.put("death_indicator", p.getDeathIndicator());
		parameters.put("death_time", RowMapperUtil.getDateTimeString(p.getDeathTime()));
		parameters.put("created_date", RowMapperUtil.getDateTimeString(p.getCreatedDate()));
		parameters.put("created_by", p.getCreatedBy());
		parameters.put("last_modify_date", RowMapperUtil.getDateTimeString(p.getLastModifyDate()));
		parameters.put("last_modified_by", p.getLastModifiedBy());
		parameters.put("voided_indicator", p.getVoidedIndicator());
		parameters.put("voided_date", RowMapperUtil.getDateTimeString(p.getVoidedDate()));
		parameters.put("voided_by", p.getVoidedBy());
		parameters.put("ref_patient_id", p.getRefPatientId());
		parameters.put("inpatientno", p.getInpatientNo());
		parameters.put("outpatientno", p.getOutpatientNo());
		parameters.put("emergencyno", p.getEmergencyNo());
		parameters.put("patient_healthcare_type_code", p.getPatientHealthcareTypeCode());
		parameters.put("patient_healthcare_type_name", p.getPatientHealthcareTypeName());
		parameters.put("patient_healthcare_property_code", p.getPatientHealthcarePropertyCode());
		parameters.put("patient_healthcare_property_name", p.getPatientHealthcarePropertyName());
		parameters.put("serial_no", p.getSerialNo());
		parameters.put("_uid_value", p.get_uid_value());
		return parameters;
	}
	
}
