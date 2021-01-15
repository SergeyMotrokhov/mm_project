package mmcommandhandler.service.impl;

import mmcommandhandler.exceptions.CommandExecutionException;
import mmcommandhandler.exceptions.IncorrectArgumentException;
import mmcommandhandler.model.MattermostCommandArgs;
import mmcommandhandler.service.JenkinsCommandService;
import mmcommandhandler.utils.*;
import org.springframework.http.MediaType;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class JenkinsCommandServiceImpl implements JenkinsCommandService {

    /**
     * Method sends to Jenkins server (Test or Maintenance) POST request
     *
     * @param args Arguments stores Jenkins server, job name, environment
     * @throws CommandExecutionException Exception can be thrown if Jenkins server arg is not
     * presented of something went wrong while sending request to Jenkins server
     */
    @Override
    public void executeCommand(MattermostCommandArgs args) throws CommandExecutionException {
        String address;
        String userId;
        String apiToken;

        if (args.getJenkinsServer().equals(JenkinsServer.TEST.getValue())) {
            address = PropertyReader.getValue("jenkins.test.url");
            userId = PropertyReader.getValue("jenkins.test.user_id");
            apiToken = PropertyReader.getValue("jenkins.test.api_token");
        } else if (args.getJenkinsServer().equals(JenkinsServer.MAINTENANCE.getValue())) {
            address = PropertyReader.getValue("jenkins.maintenance.url");
            userId = PropertyReader.getValue("jenkins.maintenance.user_id");
            apiToken = PropertyReader.getValue("jenkins.maintenance.api_token");
        } else throw new IncorrectArgumentException("Unknown jenkins server");

        String url = String.format("http://%s/job/%s/buildWithParameters", address, args.getJobName());
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(TestJobParameter.ENV.getValue(), args.getEnvironment());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(userId, apiToken));
        restTemplate.getInterceptors().add(new HeaderRequestInterceptor(HttpHeader.ACCEPT.getValue(), MediaType.ALL_VALUE));

        try {
            restTemplate.postForObject(uriBuilder.toUriString(), null, String.class);
        } catch (RestClientException e) {
            throw new CommandExecutionException(String.format("Error during sending request to Jenkins: %s", e.getLocalizedMessage()));
        }
    }
}
