package mmcommandhandler.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum HttpHeader {

    ACCEPT("Accept");

    private final String value;

}
