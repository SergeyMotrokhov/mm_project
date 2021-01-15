package mmcommandhandler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import mmcommandhandler.utils.PropertyReader;

@Data
@AllArgsConstructor
public class MattermostToken {

    private String token;

    /**
     * Compare token that came with request and token from Mattermost app
     *
     * @return true if equals else false
     */
    public boolean isValid() {
        return this.token.equals(PropertyReader.getValue("mattermost.token"));
    }

}
