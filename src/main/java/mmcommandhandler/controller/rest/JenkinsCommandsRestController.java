package mmcommandhandler.controller.rest;

import mmcommandhandler.exceptions.CommandExecutionException;
import mmcommandhandler.model.MattermostRequest;
import mmcommandhandler.service.JenkinsCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1")
public class JenkinsCommandsRestController {

    private final JenkinsCommandService jenkinsCommandService;

    @Autowired
    public JenkinsCommandsRestController(JenkinsCommandService jenkinsCommandService) {
        this.jenkinsCommandService = jenkinsCommandService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> mattermostSlashCommandHandler(
            @RequestParam Map<String, String> req) {

        MattermostRequest request = MattermostRequest.fromRequestMap(req);

        if (!request.getToken().isValid()) {
            return ResponseEntity.badRequest().body("Not valid token");
        }

        try {
            jenkinsCommandService.executeCommand(request.getArgs());
        } catch (CommandExecutionException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("Command sent");
    }

}
