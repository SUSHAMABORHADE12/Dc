package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.bindings.ChildRequest;
import in.ashokit.bindings.DcSummary;
import in.ashokit.service.DcService;

@RestController
public class ChildRestController {

	@Autowired
	private DcService service;

	@PostMapping("/childrens")
	public ResponseEntity<DcSummary> saveChilds(@RequestBody ChildRequest request) {
		Long caseNum = service.saveChildrens(request);

		DcSummary summary = service.getSummary(caseNum);

		return new ResponseEntity<>(summary, HttpStatus.OK);
	}
}
