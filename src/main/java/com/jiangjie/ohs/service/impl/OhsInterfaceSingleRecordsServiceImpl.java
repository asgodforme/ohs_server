package com.jiangjie.ohs.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiangjie.ohs.entity.OhsInterfaceSingleRecords;
import com.jiangjie.ohs.entity.autoTest.OhsInterfaceConfig;
import com.jiangjie.ohs.repository.OhsInterfaceConfigRepository;
import com.jiangjie.ohs.repository.OhsInterfaceSingleRecordsRepository;
import com.jiangjie.ohs.service.OhsInterfaceSingleRecordsService;

@Service
public class OhsInterfaceSingleRecordsServiceImpl implements OhsInterfaceSingleRecordsService {
	
	@Autowired
	private OhsInterfaceSingleRecordsRepository ohsInterfaceSingleRecordsRepository;
	
	@Autowired
	private OhsInterfaceConfigRepository ohsInterfaceConfigRepository;
	
	private static final Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}");

	@Override
	public OhsInterfaceSingleRecords saveInterfaceSingleRecords(Map<String, Object> requestParam) {
		OhsInterfaceSingleRecords ohsInterfaceSingleRecords = new OhsInterfaceSingleRecords();
		ohsInterfaceSingleRecords.setInterfaceId(Integer.parseInt((String) requestParam.get("id")));
		ohsInterfaceSingleRecords.setCreateDate(new Timestamp(new Date().getTime()));
		ohsInterfaceSingleRecords.setCreateUser((String) requestParam.get("tokenName"));
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> map: requestParam.entrySet()) {
			if (!"id".equals(map.getKey())) {
				sb.append(map.getKey()).append(":").append(map.getValue()).append(",");
			}
		}
		ohsInterfaceSingleRecords.setRequestParameters(sb.toString());
		Optional<OhsInterfaceConfig> ohsInterfaceConfigOpt = ohsInterfaceConfigRepository.findById(Integer.parseInt((String) requestParam.get("id")));
		OhsInterfaceConfig ohsInterfaceConfig = null;
		if (ohsInterfaceConfigOpt.isPresent()) {
			ohsInterfaceConfig = ohsInterfaceConfigOpt.get();
		}
		String requestTemplate = ohsInterfaceConfig.getRequestTemplate();
		Matcher matcher = pattern.matcher(requestTemplate);
		StringBuffer newSb = new StringBuffer();
		int lastIndex = 0;
		while (matcher.find()) {
			String keyNme = matcher.group(1);
			String value = requestParam.get(keyNme) + "";
			matcher.appendReplacement(newSb, value);
			lastIndex = matcher.end();
			
		}
		ohsInterfaceSingleRecords.setRequestData(newSb.append(requestTemplate.substring(lastIndex)).toString());
		ohsInterfaceSingleRecordsRepository.save(ohsInterfaceSingleRecords);
		return ohsInterfaceSingleRecords;
	}

}
