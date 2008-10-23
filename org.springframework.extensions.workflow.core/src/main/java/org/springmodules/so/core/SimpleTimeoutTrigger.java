package org.springmodules.so.core;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author janm
 */
public class SimpleTimeoutTrigger implements TimeoutTrigger {
    private static Pattern TIME_PATTERN = Pattern.compile("(\\d+)\\W*(\\w+)");
    private static final long MS_SECOND = 1000L;
    private static final long MS_HOUR = 3600L * 1000L;
    private static final long MS_DAY = 24L * 3600L * 1000L;

    public boolean trigger(Date enteredDate, String expression) {
        Matcher matcher = TIME_PATTERN.matcher(expression);
        long timeout = 0L;
        long now = System.currentTimeMillis();
        while (matcher.find()) {
            int value = Integer.valueOf(matcher.group(1));
            String unit = matcher.group(2);
            timeout += toMilliseconds(value, unit);
        }

        return now - enteredDate.getTime() > timeout;
    }

    private long toMilliseconds(int value, String unit) {
        if ("ms".equals(unit)) {
            return value;
        } else if ("s".equals(unit)) {
            return value * MS_SECOND;
        } else if ("h".equals(unit)) {
            return value * MS_HOUR;
        } else if ("d".equals(unit)) {
            return value * MS_DAY;
        }
        throw new IllegalArgumentException("You can only use ms, s, h and d as time units.");
    }
}
