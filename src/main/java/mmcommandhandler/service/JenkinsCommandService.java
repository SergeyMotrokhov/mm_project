package mmcommandhandler.service;


import mmcommandhandler.exceptions.CommandExecutionException;
import mmcommandhandler.model.MattermostCommandArgs;

public interface JenkinsCommandService {
    void executeCommand(MattermostCommandArgs args) throws CommandExecutionException;
}
