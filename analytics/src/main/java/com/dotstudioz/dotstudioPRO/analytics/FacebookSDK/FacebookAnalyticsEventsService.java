package com.dotstudioz.dotstudioPRO.analytics.FacebookSDK;

import android.os.Bundle;

import com.facebook.appevents.AppEventsLogger;

public class FacebookAnalyticsEventsService {

    private String ADVERTISING_ID_CLIENT = "";
    private String CLIENT_TOKEN = "";
    private String userEmailId = "";
    private String EVENT_FIRST_FRAME = "";

    private FacebookAnalyticsEventsService() { }

    private static FacebookAnalyticsEventsService mInstance = new FacebookAnalyticsEventsService();
    public static synchronized FacebookAnalyticsEventsService getInstance() {
        if(mInstance == null)
            mInstance = new FacebookAnalyticsEventsService();
        return mInstance;
    }

    public void initialize(String advID, String clientToken, String userEmail, String firstFrame) {
        ADVERTISING_ID_CLIENT = advID;
        CLIENT_TOKEN = clientToken;
        userEmailId = userEmail;
        EVENT_FIRST_FRAME = firstFrame;
    }

    public void saveAnalyticsEvent(AppEventsLogger logger, String eventType, String videoId) {
        try {
            Bundle params = new Bundle();
            params.putString("video_id", videoId);
            if (CLIENT_TOKEN != null && CLIENT_TOKEN.length() > 0 &&
                    userEmailId != null && userEmailId.length() > 0) {
                params.putString("user_id", userEmailId);
            }

            if (eventType.equalsIgnoreCase(EVENT_FIRST_FRAME)) {
                logger.logEvent("Viewed Content", params);
                return;
            }

            logger.logEvent(eventType, params);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
