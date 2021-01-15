package mmcommandhandler.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TestJobParameter {

    ENV("environment");

    private final String value;
}
