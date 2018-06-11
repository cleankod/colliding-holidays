package cleankod.bs.holiday.application.error;

import java.util.concurrent.ThreadLocalRandom;

class ErrorIdGenerator {
    private static final int MIN_LENGTH = 16;
    private static final char PAD_CHAR = '0';

    static String generate() {
        long errorId = ThreadLocalRandom.current().nextLong();
        String errorIdString = Long.toHexString(errorId);
        return padLeft(errorIdString);
    }

    private static String padLeft(String errorId) {
        return String.format("%1$" + MIN_LENGTH + "s", errorId)
                .replace(' ', PAD_CHAR);
    }
}
