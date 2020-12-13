package app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hl7.fhir.dstu3.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.dstu3.model.HumanName;
import org.hl7.fhir.dstu3.model.Patient;
import org.json.JSONException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
	
	private String token_value;
	
	@RequestMapping("/")
	public ModelAndView index(Authentication principal, HttpSession httpSession) {	// Principal principal

		ModelAndView mav = new ModelAndView("index");
		
		if (principal != null) {
			token_value = ((OAuth2AuthenticationDetails) principal.getDetails()).getTokenValue();
			httpSession.setAttribute("token_value", token_value);
			
			List<Map<String, String>> patients = getPatients();
			httpSession.setAttribute("patient", patients);
			
			List<Map<String, String>> coverages = getCoverages();
			httpSession.setAttribute("coverages", coverages);
		}
		
		return mav;
	}
	 

	@RequestMapping("/mhp")
	public String redirect() {
		
		return "redirect";
	}

	@GetMapping("/showLogin")
	public String showLogin() {

		return "login";
	}

	@PostMapping("/mhpLogin")
	public ModelAndView login(@RequestParam String userid, @RequestParam String password, HttpSession httpSession) {

		ModelAndView mav = new ModelAndView("index");
		
		httpSession.setAttribute("userid", userid);

		List<model.Patient> mdPatientList = new ArrayList<>();
		mdPatientList.add(getMedicalPatient());
		
		httpSession.setAttribute("mdPatientList", mdPatientList);

		return mav;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession httpSession) {

		if (httpSession != null) {
			httpSession.invalidate();
		}
		
		return "login";
	}

	
	@PostMapping("/data")
	public ModelAndView data(@RequestParam String username, @RequestParam String password, HttpSession httpSession) {

		ModelAndView mav = new ModelAndView("index");

		List<model.Patient> hsaDataList = new ArrayList<>();
		hsaDataList.add(getHSAData());
		
		httpSession.setAttribute("dataList", hsaDataList);
		
		return mav;
	}

	
	private Patient getPatient() {

		Patient patient = new Patient();

		patient.setId("123456789");

		HumanName humanName = new HumanName();
		humanName.addGiven("Jane Doe");
		List<HumanName> nameList = new ArrayList<HumanName>();
		nameList.add(humanName);

		patient.setName(nameList);
		patient.setBirthDate(new Date(System.currentTimeMillis()));
		patient.setGender(AdministrativeGender.FEMALE);

		return patient;
	}

	private model.Patient getMedicalPatient() {

		model.Patient patient = new model.Patient();
		patient.setPid(123456789);
		patient.setGender("Female");
		patient.setPdate("12/10/1945");
		patient.setPname("Jane Doe");

		return patient;
	}
	
	
	private model.Patient getHSAData() {

		model.Patient patient = new model.Patient();
		patient.setPid(987654321);
		patient.setGender("Female");
		patient.setPdate("12/10/1945");
		patient.setPname("Jane Doe");

		return patient;
	}
	
	
	public List<Map<String, String>> getPatients() throws JSONException {

		/* ModelAndView mav = new ModelAndView("patient"); */
		String patientUrl = "https://sandbox.bluebutton.cms.gov/v1/fhir/Patient/";
		HttpEntity<String> response = getResponse(patientUrl);

		PatientMap patientMap = new PatientMap(response);
		List<Map<String, String>> patientList = patientMap.getClaimMap();

		return patientList;
	}
	
	
	public List<Map<String, String>> getCoverages() throws JSONException {

		String coverageUrl = "https://sandbox.bluebutton.cms.gov/v1/fhir/Coverage/";
		HttpEntity<String> response = getResponse(coverageUrl);

		CoverageMap coverageMap = new CoverageMap(response);
		List<Map<String, String>> coverages = coverageMap.getCoverageMap();

		return coverages;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HttpEntity<String> getResponse(String URL) {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token_value);

		HttpEntity entity = new HttpEntity(headers);
		HttpEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

		return response;
	}

}
