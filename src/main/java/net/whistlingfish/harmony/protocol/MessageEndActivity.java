package net.whistlingfish.harmony.protocol;

import java.util.Map;

import org.jivesoftware.smack.packet.IQ;

import com.google.common.collect.ImmutableMap;

import static net.whistlingfish.harmony.Jackson.OBJECT_MAPPER;

public class MessageEndActivity {
    public static final String MIME_TYPE = "vnd.logitech.harmony/vnd.logitech.harmony.engine?stopactivity";
    public static final String MIME_TYPE2 = "harmony.engine?stopActivity";

    /*
     * Request
     */
    public static class EndActivityRequest extends IrCommand {
        private int activityId;

        public EndActivityRequest(int activityId) {
            super(MIME_TYPE);
            this.activityId = activityId;
        }

        @Override
        protected Map<String, Object> getChildElementPairs() {
            return ImmutableMap.<String, Object> builder() //
                    .put("activityId", activityId)
                    .put("timestamp", generateTimestamp())
                    .build();
        }
    }

    /*
     * Reply (unused)
     */
    public static class EndActivityReply extends OAPacket {
        public EndActivityReply() {
            super(MIME_TYPE);
        }

        @Override
        protected Map<String, Object> getChildElementPairs() {
            return ImmutableMap.<String, Object> builder() //
                    .build();
        }
    }

    /*
     * Parser
     */
    public static class EndActivityReplyParser extends OAReplyParser {
        @Override
        public IQ parseReplyContents(String statusCode, String errorString, String contents) {
            return OBJECT_MAPPER.convertValue(parseKeyValuePairs(statusCode, errorString, contents),
            		EndActivityReply.class);
        }
        
        @Override
        public boolean validResponseCode(String code){
        	//sometimes the End activity will return a 401 if a device is not setup correctly
        	return super.validResponseCode(code) || code.equals("401");
        }
    }

}
