package com.negociandola.cuponera.firebase;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.negociandola.cuponera.Application;

/**
 * Created by rsandoval on 14/09/2016.
 */
public class FirebaseAnalyticsNegociandola {

    private static FirebaseAnalyticsNegociandola sInstance;
    private FirebaseAnalytics mFirebaseAnalytics;

    public FirebaseAnalyticsNegociandola(Context context) {
        super();
        mFirebaseAnalytics = ((Application) context.getApplicationContext()).getFirebaseAnalytics();
    }

    public static FirebaseAnalyticsNegociandola newInstance(Context context) {
        if (sInstance == null) {
            sInstance = new FirebaseAnalyticsNegociandola(context);
        }
        return sInstance;
    }

    public void screenEvent(String screen) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, screen);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, ConstantsFirebaseAnalytics.Params.PAGINA_VISTA);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }


    public void trackEvent(String contentType, String itemId) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, itemId);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, contentType);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    public void searchEvent(String searchTerm) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SEARCH_TERM, searchTerm);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SEARCH, bundle);
    }

    public void shareEvent(String contentType, String itemId) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, itemId);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, contentType);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SHARE, bundle);
    }



}
