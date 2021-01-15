package mmcommandhandler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MattermostCommandArgs {

    private String jenkinsServer;
    private String jobName;
    private String environment;

    /**
     * Method to map arguments string from Mattermost to POJO
     * e.g. of string 'jenkins=test job=eip_canonical_service env=test'
     *
     * @param args arguments string from Mattermost
     * @return POJO with arguments
     */
    public static MattermostCommandArgs fromRequest(String args) {
        if (args.trim().isEmpty()) return new MattermostCommandArgs();
        String[] arguments = args.trim().toLowerCase().split(" ");
        String jenkinsServer = arguments[0].replace("jenkins=", "");
        String job = arguments[1].replace("job=", "");
        String environment = arguments[2].replace("env=", "");
        return new MattermostCommandArgs(jenkinsServer, job, environment);
    }

}
