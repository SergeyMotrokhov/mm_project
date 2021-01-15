package mmcommandhandler.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JenkinsServer {
    TEST("test"),
    MAINTENANCE("exp");

    private final String value;
}
