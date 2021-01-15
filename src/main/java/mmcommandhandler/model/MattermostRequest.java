package mmcommandhandler.model;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * Request handled from Mattermost client application
 */
@Data
@Builder(setterPrefix = "set")
public class MattermostRequest {

    private String channelId;
    private String channelName;
    private String command;
    private String responseUrl;
    private String teamDomain;
    private MattermostCommandArgs args;
    private MattermostToken token;
    private String triggerId;
    private MattermostUser user;

    /**
     * Convert requset parameters from Map<Key, Value> to POJO
     *
     * @param dto plain request parameters map with pair of Strings
     * @return Request converted to POJO request
     */
    public static MattermostRequest fromRequestMap(Map<String, String> dto) {
        MattermostToken token = new MattermostToken(dto.get("token"));
        MattermostUser user = new MattermostUser(
                dto.getOrDefault("user_id", ""),
                dto.getOrDefault("user_name", ""));
        MattermostCommandArgs args = MattermostCommandArgs.fromRequest(dto.getOrDefault("text", ""));

        return MattermostRequest.builder()
                .setChannelId(dto.getOrDefault("channel_id", ""))
                .setChannelName(dto.getOrDefault("channel_name", ""))
                .setCommand(dto.getOrDefault("command", ""))
                .setResponseUrl(dto.getOrDefault("response_url", ""))
                .setTeamDomain(dto.getOrDefault("team_domain", ""))
                .setArgs(args)
                .setToken(token)
                .setTriggerId(dto.getOrDefault("trigger_id", ""))
                .setUser(user)
                .build();
    }

}
