package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.dstu3.model.Coverage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;

public class CoverageMap {

	private List<Map<String, String>> coverage;
	private JSONObject resource;
	private FhirContext ctx = FhirContext.forDstu3();
	private IParser parser;

	public CoverageMap(HttpEntity<String> response) throws JSONException {
		resource = getResources(response.getBody()).get(0);
		parser = ctx.newJsonParser();
		parser.setPrettyPrint(true);
		coverage = new ArrayList<Map<String, String>>();

		addCoverage();
	}

	public List<Map<String, String>> getCoverageMap() {
		return coverage;
	}

	private void addCoverage() {
		Map<String, String> coverageRecord = new HashMap<String, String>();
		Coverage coverageRes = parser.parseResource(Coverage.class, resource.toString());

		coverageRecord.put("id", coverageRes.getSubscriberId());
		coverageRecord.put("period", coverageRes.getPeriod().getStart() + " - " + coverageRes.getPeriod().getEnd());
		coverageRecord.put("status", coverageRes.getStatus().toString());
		
		coverage.add(coverageRecord);
	}

	private ArrayList<JSONObject> getResources(String response) throws JSONException {
		ArrayList<JSONObject> resources = new ArrayList<JSONObject>();

		JSONObject json = new JSONObject(response);
		JSONArray entries = json.getJSONArray("entry");
		for (int i = 0; i < entries.length(); i++) {
			JSONObject entry = entries.getJSONObject(i);
			JSONObject resource = entry.getJSONObject("resource");
			resources.add(resource);
		}

		return resources;
	}

}
